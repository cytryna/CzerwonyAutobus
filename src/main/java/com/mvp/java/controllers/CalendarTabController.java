package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.mvp.java.utils.DateTimeUtil;
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

        System.err.println("Button was Clicked");
    }

}
