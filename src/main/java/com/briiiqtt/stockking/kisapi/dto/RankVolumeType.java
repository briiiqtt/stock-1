package com.briiiqtt.stockking.kisapi.dto;

public enum RankVolumeType {
    AVERAGE_TRADE_VOLUME("0"),                  // 평균거래량
    TRADE_VOLUME_INCREMENT_RATE("1"),           // 거래증가율
    AVERAGE_TRADE_TURNOVER_RATE("2"),           // 평균거래회전율
    TRADE_AMOUNT_RANK("3"),                     // 거래금액순
    AVERAGE_TRADE_AMOUNT_TURNOVER_RATE("4");    // 평균거래금액회전율

    private final String value;
    RankVolumeType(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}