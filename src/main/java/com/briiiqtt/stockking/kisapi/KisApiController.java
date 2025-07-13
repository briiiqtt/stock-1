package com.briiiqtt.stockking.kisapi;

import com.briiiqtt.stockking.kisapi.dto.RankVolumeType;
import com.briiiqtt.stockking.kisapi.dto.RankVolumeResponse;
import com.briiiqtt.stockking.stock.StockCodeManager;
import com.briiiqtt.stockking.stock.dto.StockCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/kis")
public class KisApiController {
    private final KisApiService kisApiService;
    private final StockCodeManager stockCodeManager;

    @GetMapping("/rank-volume")
    public ResponseEntity<RankVolumeResponse> getRankVolume() {
        try {
            RankVolumeResponse resp = kisApiService.getRankVolume(RankVolumeType.AVERAGE_TRADE_AMOUNT_TURNOVER_RATE);
            return ResponseEntity
                    .status(200).body(resp);

        } catch (IOException e) {
            log.error(e.getClass().getName(), e);
            return ResponseEntity
                    .status(500).body(null); // TODO: 에러 응답 DTO 구조 고민..

        } catch (WebClientResponseException e) {
            log.error("getRankVolume" + e.getResponseBodyAsString());
            return ResponseEntity
                    .status(e.getStatusCode()).body(null);
        }
    }

    @GetMapping("/init-stock-codes")
    public ResponseEntity<List<StockCode>> initStockCodes() {
//        try {
            return ResponseEntity
                    .status(200).body(stockCodeManager.getStockCodes());
//        } catch (IOException e) {
//            log.error(e.getClass().getName(), e);
//            return ResponseEntity
//                    .status(500).body(null);
//        }
    }
}
