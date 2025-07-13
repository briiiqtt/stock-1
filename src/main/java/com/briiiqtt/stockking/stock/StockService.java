package com.briiiqtt.stockking.stock;

import com.briiiqtt.stockking.kisapi.KisApiService;
import com.briiiqtt.stockking.stock.entity.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final PriceRepository priceRepository;
    private final StockCodeManager stockCodeManager;
    private final KisApiService kisApiService;

    private final AtomicInteger stockCodeIndex = new AtomicInteger(0);

    public Price saveOne(Price price) {
        return priceRepository.save(price);
    }

    public void fetchAndRepositStockPrice() throws IOException, WebClientResponseException {
        int idx = stockCodeIndex.get();
        if (idx == stockCodeManager.getStockCodes().size()) {
            stockCodeIndex.set(0);
            idx = 0;
        }
        var s = stockCodeManager.getStockCodes().get(idx);
        stockCodeIndex.set(++idx);

        var resp = kisApiService.inquirePrice(s.getIssueCode());
        var data = resp.getData();
        Price price = Price.builder()
                .issueCode(data.getIssueCode())
                .stockStatusCode(data.getStockStatusCode())
                .priceCurrent(data.getPriceCurrent())
                .priceDiffRateFromYesterday(data.getPriceDiffRateFromYesterday())
                .tradeCountAccumulate(data.getTradeCountAccumulate())
                .priceHighest(data.getPriceHighest())
                .priceLowest(data.getPriceLowest())
                .priceMaximum(data.getPriceMaximum())
                .priceMinimum(data.getPriceMinimum())
                .priceYesterday(data.getPriceYesterday())
                .build();

        priceRepository.save(price);
        return;
    }

}
