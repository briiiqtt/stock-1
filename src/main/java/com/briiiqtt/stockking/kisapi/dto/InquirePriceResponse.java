package com.briiiqtt.stockking.kisapi.dto;

import com.briiiqtt.stockking.util.StringToDoubleDeserializer;
import com.briiiqtt.stockking.util.StringToFloatDeserializer;
import com.briiiqtt.stockking.util.StringToLongDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InquirePriceResponse extends KisApiResponse {

    @JsonProperty("output")
    private Output data;

    @Getter
    @Setter
    public static class Output {
        @JsonProperty("stck_shrn_iscd")
        private String issueCode;
        
        @JsonProperty("iscd_stat_cls_code")
        private String stockStatusCode;

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

        @JsonProperty("stck_hgpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceHighest;

        @JsonProperty("stck_lwpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceLowest;

        @JsonProperty("stck_mxpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceMaximum;

        @JsonProperty("stck_llpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceMinimum;

        @JsonProperty("stck_sdpr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double priceYesterday;

        @JsonProperty("per")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double PER;

        @JsonProperty("pbr")
        @JsonDeserialize(using = StringToDoubleDeserializer.class)
        private double PBR;
    }
}
