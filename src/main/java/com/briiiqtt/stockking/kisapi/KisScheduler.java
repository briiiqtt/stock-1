package com.briiiqtt.stockking.kisapi;

import com.briiiqtt.stockking.stock.StockService;
import com.briiiqtt.stockking.util.Market;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KisScheduler {
    private final Market market;
    private volatile boolean repeat = false;
    private final StockService stockService;

    @Scheduled(fixedRate = 1000)
    public void checkMarketOpen() {
        this.repeat = market.isMarketOpen();
    }

    @Scheduled(fixedRate = 60)
    public void fetchAndRepositStockPrice() {
        if (!repeat) return;
        try {
            stockService.fetchAndRepositStockPrice();
        } catch (IOException e) {
            repeat = false;
            log.error(e.getClass().getName(), e);
        } catch (WebClientResponseException e) {
            repeat = false;
            log.error("fetchAndRepositStockPrice()" + e.getResponseBodyAsString());
        }
    }

}
