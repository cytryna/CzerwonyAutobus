package com.diligentia.calendar.model;

import java.util.List;

public class CalendarAll {

    private final String BEGIN = "BEGIN:VCALENDAR";
    private final String PRODID = "PRODID:-//Google Inc//Google Calendar 70.9054//EN";
    private final String VERSION = "VERSION:2.0";
    private final String CALSCALE = "CALSCALE:GREGORIAN";
    private final String METHOD = "METHOD:PUBLISH";
    private String X_WR_CALNAME = "X-WR-CALNAME:posiłki";
    private final String X_WR_TIMEZONE = "X-WR-TIMEZONE:Europe/Warsaw";
    private List<EventCalendar> list;
    private final String END = "END:VCALENDAR";


    public String genereteIcs() {
        StringBuilder builder = new StringBuilder();

        builder.append(BEGIN);
        builder.append(System.lineSeparator());
        builder.append(PRODID);
        builder.append(System.lineSeparator());
        builder.append(VERSION);
        builder.append(System.lineSeparator());
        builder.append(CALSCALE);
        builder.append(System.lineSeparator());
        builder.append(METHOD);
        builder.append(System.lineSeparator());
        builder.append(X_WR_CALNAME);//TODO nazwa
        builder.append(System.lineSeparator());
        builder.append(X_WR_TIMEZONE);
        builder.append(System.lineSeparator());
        list.forEach(eventCalendar -> builder.append(eventCalendar.generateIcs()));
        builder.append(X_WR_TIMEZONE);
        builder.append(System.lineSeparator());
        builder.append(END);
        return builder.toString();
    }
}
