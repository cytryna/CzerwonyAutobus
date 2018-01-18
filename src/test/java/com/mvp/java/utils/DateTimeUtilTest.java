package com.mvp.java.utils;

import com.google.api.client.util.DateTime;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

// TODO: 18.01.18 Przepisać testy na JUnit 5 lub Spoka
/**
 * Created by rwichrowski on 18.01.18.
 */
public class DateTimeUtilTest {

    private String RFC3339_PATTERN_STRING = "^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?";
    private Pattern RFC3339_PATTERN = Pattern.compile(RFC3339_PATTERN_STRING);
    @Test
    public void should_return_now_in_match_pattern() {
        //given
        DateTime now = DateTimeUtil.now();
        //when

        //then
        assertTrue(now.toString().matches(RFC3339_PATTERN_STRING));
    }

    @Test
    public void should_return_today_at_10_am() {
        //given
        DateTime today = DateTimeUtil.today("10");
        //when

        //then
//        System.err.println(today.toString());
//        System.err.println(today.getValue());
//        System.err.println(new Date().getTime());
//      TODO: 18.01.18 Dokończyc ten test
        assertTrue(today.toString().matches(RFC3339_PATTERN_STRING));
//        today.getValue();
    }

}