package com.briiiqtt.stockking.util;

import com.briiiqtt.stockking.config.KisApiConfig;
import com.briiiqtt.stockking.kisapi.KisApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class WebClientConfig {

    @Bean
    @Qualifier("kisApiClient")
    public WebClient kisClient(
            @Autowired KisApiConfig kisApiConfig
    ) {
        return WebClient.builder()
                .baseUrl("https://openapi.koreainvestment.com:9443")
                .defaultHeaders(headers -> {
                    headers.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
                    headers.set("appkey", kisApiConfig.getAppKey());
                    headers.set("appsecret", kisApiConfig.getAppSecret());
                })
                .build();
    }

    @Bean
    @Qualifier("httpbinClient")
    public WebClient httpbinClient() {
        return WebClient.builder()
                .baseUrl("https://httpbin.org")
                .defaultHeaders(headers -> {
                    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
    }
}