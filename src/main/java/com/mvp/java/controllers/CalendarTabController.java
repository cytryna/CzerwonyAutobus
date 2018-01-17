package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarTabController {

    @FXML private TextArea loggerTxtArea;

    @Autowired
    private CalendarService service;

    public TextArea getLoggerTxtArea() {
        return loggerTxtArea;
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        service.sendEventToCalendar(createEvent());
        System.err.println("Button was Clicked");
    }

    private Event createEvent() {
        Event event = new Event()
                .setSummary("Sos grzybowy z tab Calendar")
                .setDescription("Description:\n" +
                        "Składniki:\n" +
                        "\n" +
                        "1 szklanka suszonych grzybów\n" +
                        "1 duża cebula\n" +
                        "3 łyżki masła\n" +
                        "Łyżka mąki\n" +
                        "Sól\n" +
                        "Pieprz biały\n" +
                        "Majeranek\n" +
                        "Wykonanie:\n" +
                        "Grzyby moczyłam kilka godzin (najlepiej zostawić na całą noc). Grzyby odcedziłam, zalałam niewielką ilością wody i wstawiłam do gotowania. Obrałam i pokroiłam cebulę w kostkę i zeszkliłam na maśle. Obgotowane grzyby dodałam do przysmażonej cebuli. Chwilę razem smażyłam. Następnie podlałam wodą, w której gotowały się grzyby. Na drugiej patelni na łyżce masła przesmażyłam mąkę i zrobiłam zasmażkę. Zasmażkę rozprowadziłam wywarem z grzybów energicznie mieszając, aby nie zrobiły się grudki. Tak przygotowany sos doprawiłam do smaku solą, pieprzem i majerankiem.");

        DateTime startDateTime = new DateTime("2018-01-18T10:15:30+01:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2018-01-18T11:15:30+01:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);
        return event;
    }
}
