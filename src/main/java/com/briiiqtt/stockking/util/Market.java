package com.briiiqtt.stockking.util;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class Market {

    public boolean isMarketOpen() {
        return true;
//        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
//        DayOfWeek d = now.getDayOfWeek();
//        if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY) return false;
//        LocalTime t = now.toLocalTime();
//        return !t.isBefore(LocalTime.of(9,0)) && !t.isAfter(LocalTime.of(15,30));
    }

}
