package com.mvp.java.utils;

import com.google.api.client.util.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Created by rwichrowski on 17.01.18.
 */
public class DateTimeUtil {
    private Pattern RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");

    public static DateTime now() {
        System.err.println("ddddddddddd:"+ ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return new DateTime(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
