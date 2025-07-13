package com.briiiqtt.stockking.kisapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class KisApiResponse {
    @JsonProperty("rt_cd")
    private String returnCode;

    @JsonProperty("msg_cd")
    private String messageCode;

    @JsonProperty("msg1")
    private String message;
}
