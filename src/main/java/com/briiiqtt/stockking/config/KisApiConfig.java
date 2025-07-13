package com.briiiqtt.stockking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kis-api")
public class KisApiConfig {
    private String appKey;
    private String appSecret;
}
