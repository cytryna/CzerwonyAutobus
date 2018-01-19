package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
import com.mvp.java.model.CalendarEventBuilder;
import com.mvp.java.utils.LocalDateStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MealsTabController implements Initializable {

    @FXML
    private TextArea loggerTxtArea;

    @FXML
    private DatePicker datePicker;

    @Autowired
    private CalendarService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setConverter(new LocalDateStringConverter());
    }

    public TextArea getLoggerTxtArea() {
        return loggerTxtArea;
    }

    //TODO-rwichrowski Dopisac testy
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        service.sendEventToCalendar(new CalendarEventBuilder().withSummary("Kapuśniak").withEventDate(datePicker.getValue()).withHourStart(10).build());
        System.err.println("datePicker:"+datePicker.getValue());
    }


}
