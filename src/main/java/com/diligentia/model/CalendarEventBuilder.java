package com.diligentia.model;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.diligentia.utils.DateTimeUtil;

import java.time.LocalDate;

public class CalendarEventBuilder {
    private LocalDate eventDate;
    private int hourStart;
    private String summary;
    private String description;

    //todo jak zaczyna się budowanie buildera?
    public CalendarEventBuilder withEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public CalendarEventBuilder withHourStart(int hourStart) {
        this.hourStart = hourStart;
        return this;
    }


    public CalendarEventBuilder withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public CalendarEventBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Event build() {
        Event event = new Event()
                .setSummary(summary)
                .setDescription(description);

        //TODO-rwichrowski całość przenie3ść do jakiegoś utila żeby pozbyć się pośreniego obiektu  com.google.api.client.util.DateTime
        //Najlepiej prosto z LocaDate generować EventDateTime
        EventDateTime start = new EventDateTime()
                .setDateTime(DateTimeUtil.toDateTime(eventDate, hourStart))
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(DateTimeUtil.toDateTime(eventDate, hourStart + 1))
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);
        return event;
    }
}