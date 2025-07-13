package com.briiiqtt.stockking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HttpbinService {
    private final WebClient httpbinClient;

    public HttpbinService(@Autowired @Qualifier("httpbinClient") WebClient httpbinClient) {
        this.httpbinClient = httpbinClient;
    }

    public String get() {
        return httpbinClient.get()
                .uri("/get")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String post() {
        return httpbinClient.post()
                .uri("/post")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String delay(int second) {
        return httpbinClient.post()
                .uri("/delay/" + second)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
