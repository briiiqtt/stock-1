package com.briiiqtt.stockking.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCode {

    private String issueCode;
    private String standardCode;
    private String nameKor;

    private String market;
    // 1~5
    private String groupCode; // 그룹코드
    private String marketCapitalization; // 시가총액
    private String sector; // 지수업종 대분류
    private String industry; // 지수업종 중분류
    private String subIndustry; // 지수업종 소분류

    // 6~10
//        private String 제조업;
//        private String 저유동성;
//        private String 지배구조지수종목;
//        private String KOSPI200섹터업종;
//        private String KOSPI100;
//
//        // 11~15
//        private String KOSPI50;
//        private String KRX;
//        private String ETP;
//        private String ELW발행;
//        private String KRX100;
//
//        // 16~20
//        private String KRX자동차;
//        private String KRX반도체;
//        private String KRX바이오;
//        private String KRX은행;
//        private String SPAC;
//
//        // 21~25
//        private String KRX에너지화학;
//        private String KRX철강;
//        private String 단기과열;
//        private String KRX미디어통신;
//        private String KRX건설;
//
//        // 26~30
//        private String Non1;
//        private String KRX증권;
//        private String KRX선박;
//        private String KRX섹터_보험;
//        private String KRX섹터_운송;
//
//        // 31~35
//        private String SRI;
//        private String 기준가;
//        private String 매매수량단위;
//        private String 시간외수량단위;
//        private String 거래정지;
//
//        // 36~40
//        private String 정리매매;
//        private String 관리종목;
//        private String 시장경고;
//        private String 경고예고;
//        private String 불성실공시;
//
//        // 41~45
//        private String 우회상장;
//        private String 락구분;
//        private String 액면변경;
//        private String 증자구분;
//        private String 증거금비율;
//
//        // 46~50
//        private String 신용가능;
//        private String 신용기간;
//        private String 전일거래량;
//        private String 액면가;
//        private String 상장일자;
//
//        // 51~55
//        private String 상장주수;
//        private String 자본금;
//        private String 결산월;
//        private String 공모가;
//        private String 우선주;
//
//        // 56~60
//        private String 공매도과열;
//        private String 이상급등;
//        private String KRX300;
//        private String KOSPI;
//        private String 매출액;
//
//        // 61~65
//        private String 영업이익;
//        private String 경상이익;
//        private String 당기순이익;
//        private String ROE;
//        private String 기준년월;
//
//        // 66~70
//        private String 시가총액;
//        private String 그룹사코드;
//        private String 회사신용한도초과;
//        private String 담보대출가능;
//        private String 대주가능;
}
