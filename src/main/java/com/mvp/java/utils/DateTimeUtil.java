package com.mvp.java.utils;

import com.google.api.client.util.DateTime;
import com.sun.xml.internal.bind.v2.TODO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Created by rwichrowski on 17.01.18.
 */
public class DateTimeUtil {

    private static final String TIME_SHIFT = "+01:00";
    private static final String MINUTES_AND_SECONDS = ":00:00";
    private static final String TIME = "T";

    public static DateTime now() {
        String format = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return new DateTime(format);
    }

    //TODO-rwichrowski Dodać util dodjaący 0 do ghodzin poniżej 10
    public static DateTime today(int hour) {
        String date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        StringBuilder stringBuilder = new StringBuilder(date);
        stringBuilder.append(TIME);
        stringBuilder.append(""+hour);
        stringBuilder.append(MINUTES_AND_SECONDS);
        stringBuilder.append(TIME_SHIFT);
        return new DateTime(stringBuilder.toString());
    }

    public static DateTime toDateTime(LocalDate date, int hour) {
        StringBuilder stringBuilder = new StringBuilder(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        stringBuilder.append(TIME);
        stringBuilder.append(""+hour);
        stringBuilder.append(MINUTES_AND_SECONDS);
        stringBuilder.append(TIME_SHIFT);
        return new DateTime(stringBuilder.toString());
    }
}
