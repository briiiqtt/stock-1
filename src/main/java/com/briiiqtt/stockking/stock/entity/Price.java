package com.briiiqtt.stockking.stock.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@Table(
        indexes = @Index(columnList = "issueCode")
)
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String issueCode;
    private String stockStatusCode;
    private double priceCurrent;
    private double priceDiffFromYesterday;
    private float priceDiffRateFromYesterday;
    private long tradeCountAccumulate;
    private double priceHighest;
    private double priceLowest;
    private double priceMaximum;
    private double priceMinimum;
    private double priceYesterday;
}