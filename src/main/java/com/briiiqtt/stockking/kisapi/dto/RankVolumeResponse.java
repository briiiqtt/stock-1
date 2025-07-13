package com.briiiqtt.stockking.kisapi.dto;

import com.briiiqtt.stockking.util.StringToDoubleDeserializer;
import com.briiiqtt.stockking.util.StringToFloatDeserializer;
import com.briiiqtt.stockking.util.StringToIntDeserializer;
import com.briiiqtt.stockking.util.StringToLongDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RankVolumeResponse extends KisApiResponse {

    @JsonProperty("output")
    private List<Output> data;

    public static class Output {
        @JsonProperty("hts_kor_isnm")
        private String nameKor;

        @JsonProperty("mksc_shrn_iscd")
        private String issueCode;

        @JsonProperty("data_rank")
        @JsonDeserialize(using = StringToIntDeserializer.class)
        private int rank;

        @JsonProperty("stck_prpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceCurrent;

        @JsonProperty("prdy_vrss")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceDiffFromYesterday;

        @JsonProperty("prdy_ctrt")
        @JsonDeserialize(using = StringToFloatDeserializer.class)
        private float priceDiffRateFromYesterday;

        @JsonProperty("acml_vol")
        @JsonDeserialize(using = StringToLongDeserializer.class)
        private long tradeCountAccumulate;

        @JsonProperty("acml_tr_pbmn")
        @JsonDeserialize(using = StringToLongDeserializer.class)
        private long tradePriceAccumulate;
    }
}