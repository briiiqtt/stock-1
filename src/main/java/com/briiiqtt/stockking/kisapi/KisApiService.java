package com.briiiqtt.stockking.kisapi;

import com.briiiqtt.stockking.config.KisApiConfig;
import com.briiiqtt.stockking.kisapi.dto.InquirePriceResponse;
import com.briiiqtt.stockking.kisapi.dto.RankVolumeType;
import com.briiiqtt.stockking.kisapi.dto.RankVolumeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KisApiService {
    private final WebClient webClient;
    private final KisApiConfig kisApiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private TokenData tokenData;
    private final String TOKEN_FILE_PATH = "cache/kisToken.json";

    public KisApiService(
            @Qualifier("kisApiClient") WebClient webClient,
            @Autowired KisApiConfig kisApiConfig
    ) {
        this.webClient = webClient;
        this.kisApiConfig = kisApiConfig;

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            loadTokenFromFile();
        } catch (IOException e) {
            log.error("loadTokenFromFile();", e);
        }
    }

    public synchronized String getAccessToken() throws IOException {
        if (tokenData == null || tokenData.getAccessToken() == null || tokenData.getAccessToken().isEmpty() || tokenData.isAccessTokenExpired()) {
            var _tokenData = issueAccessToken();
            tokenData = _tokenData;
            saveTokenToFile(_tokenData);
        }

        return tokenData.getAccessToken();
    }

    private void saveTokenToFile(TokenData tokenData) throws IOException {
        objectMapper.writeValue(Paths.get(TOKEN_FILE_PATH).toFile(), tokenData);
    }

    private void loadTokenFromFile() throws IOException {
        initTokenCacheIfNotExists();
        this.tokenData = objectMapper.readValue(Paths.get(TOKEN_FILE_PATH).toFile(), TokenData.class);
    }

    private void initTokenCacheIfNotExists() throws IOException {
        Path path = Paths.get(TOKEN_FILE_PATH);
        Files.createDirectories(path.getParent());
        if (Files.notExists(path)) {
            objectMapper.writeValue(path.toFile(), new ObjectMapper().createObjectNode());
        }
    }

    @Getter
    @Setter
    public static class TokenData {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("access_token_token_expired")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime accessTokenExpiresAt;

        public TokenData() {
        }

        public TokenData(String accessToken, LocalDateTime accessTokenExpiresAt) {
            this.accessToken = accessToken;
            this.accessTokenExpiresAt = accessTokenExpiresAt;
        }

        @JsonIgnore
        public boolean isAccessTokenExpired() {
            return accessTokenExpiresAt == null || accessTokenExpiresAt.isBefore(LocalDateTime.now());
        }

    }

    public TokenData issueAccessToken() throws WebClientResponseException {
        final String path = "/oauth2/tokenP";
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("appkey", kisApiConfig.getAppKey());
        body.put("appsecret", kisApiConfig.getAppSecret());

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build()
                )
                .bodyValue(body)
                .retrieve()
                .bodyToMono(TokenData.class)
                .block();
    }


    public RankVolumeResponse getRankVolume(RankVolumeType rankVolumeType) throws IOException, WebClientResponseException {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/volume-rank")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                        .queryParam("FID_COND_SCR_DIV_CODE", "20171")
                        .queryParam("FID_INPUT_ISCD", "0000")
                        .queryParam("FID_DIV_CLS_CODE", rankVolumeType.getValue())
                        .queryParam("FID_BLNG_CLS_CODE", "0")
                        .queryParam("FID_TRGT_CLS_CODE", "111111111")
                        .queryParam("FID_TRGT_EXLS_CLS_CODE", "1111111111")
                        .queryParam("FID_INPUT_PRICE_1", "")
                        .queryParam("FID_INPUT_PRICE_2", "")
                        .queryParam("FID_VOL_CNT", "")
                        .queryParam("FID_INPUT_DATE_1", "")
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .header("tr_id", "FHPST01710000")
                .retrieve()
                .bodyToMono(RankVolumeResponse.class)
                .block();
    }

    public InquirePriceResponse inquirePrice(String issueCode) throws IOException, WebClientResponseException {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/inquire-price")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "UN")
                        .queryParam("FID_INPUT_ISCD", issueCode)
                        .build()
                )
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .header("tr_id", "FHKST01010100")
                .retrieve()
                .bodyToMono(InquirePriceResponse.class)
                .block();
    }

}
