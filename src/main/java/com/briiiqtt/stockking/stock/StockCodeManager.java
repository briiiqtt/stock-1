package com.briiiqtt.stockking.stock;

import com.briiiqtt.stockking.stock.dto.StockCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Component
public class StockCodeManager {
    private List<StockCode> stockCodes = new ArrayList<>();

    public StockCodeManager() {
        try {
            stockCodes = getRawStockCodeDataFromFile();
        } catch (IOException e) {
            log.error(e.getClass().getName(), e);
        }
    }

    public List<StockCode> getRawStockCodeDataFromFile() throws IOException {

        String kospiPath = "cache/kospi_code.mst"; // https://new.real.download.dws.co.kr/common/master/kospi_code.mst.zip
        String kosdaqPath = "cache/kosdaq_code.mst"; // https://new.real.download.dws.co.kr/common/master/kosdaq_code.mst.zip

        List<StockCode> ret = new ArrayList<>();

        List<String> linesKospi = Files.readAllLines(Paths.get(kospiPath), Charset.forName("x-windows-949"));
        for (String line : linesKospi) {
            StockCode stockCode = parseLineKospi(line);
            ret.add(stockCode);
        }

        List<String> linesKosdaq = Files.readAllLines(Paths.get(kosdaqPath), Charset.forName("x-windows-949"));
        for (String line : linesKosdaq) {
            StockCode stockCode = parseLineKosdaq(line);
            ret.add(stockCode);
        }

        return ret;
    }

    public static StockCode parseLineKospi(String line) {
        StockCode stockCode = new StockCode();
        stockCode.setMarket("kospi");

        // part1 고정 offset
        int issueCodeEnd = 9;
        int standardCodeEnd = issueCodeEnd + 12;
        int nameKorEnd = line.length() - 228;

        stockCode.setIssueCode(line.substring(0, issueCodeEnd).trim());
        stockCode.setStandardCode(line.substring(issueCodeEnd, standardCodeEnd).trim());
        stockCode.setNameKor(line.substring(standardCodeEnd, nameKorEnd).trim());

        // part2 고정 필드
        String part2 = line.substring(line.length() - 228);
        int p = 0;

        stockCode.setGroupCode(part2.substring(p, p += 2).trim());
        stockCode.setMarketCapitalization(part2.substring(p, p += 1).trim());
        stockCode.setSector(part2.substring(p, p += 4).trim());
        stockCode.setIndustry(part2.substring(p, p += 4).trim());
        stockCode.setSubIndustry(part2.substring(p, p += 4).trim());

        return stockCode;
    }

    public static StockCode parseLineKosdaq(String line) {
        StockCode stockCode = new StockCode();
        stockCode.setMarket("kosdaq");

        // part1 고정 offset
        int issueCodeEnd = 9;
        int standardCodeEnd = issueCodeEnd + 12;
        int nameKorEnd = line.length() - 222;

        stockCode.setIssueCode(line.substring(0, issueCodeEnd).trim());
        stockCode.setStandardCode(line.substring(issueCodeEnd, standardCodeEnd).trim());
        stockCode.setNameKor(line.substring(standardCodeEnd, nameKorEnd).trim());

        // part2 고정 필드
        String part2 = line.substring(line.length() - 222);
        int p = 0;

        stockCode.setGroupCode(part2.substring(p, p += 2).trim());
        stockCode.setMarketCapitalization(part2.substring(p, p += 1).trim());
        stockCode.setSector(part2.substring(p, p += 4).trim());
        stockCode.setIndustry(part2.substring(p, p += 4).trim());
        stockCode.setSubIndustry(part2.substring(p, p += 4).trim());

        return stockCode;
    }

}
