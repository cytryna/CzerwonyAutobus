package com.diligentia.controllers;

import com.diligentia.model.Meal;
import com.diligentia.services.CalendarService;
import com.diligentia.model.CalendarEventBuilder;
import com.diligentia.utils.LocalDateStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MealTabController implements Initializable {

    @FXML
    private TextArea description;

    @Autowired
    private CalendarService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public TextArea getDescription() {
        return description;
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        Meal meal = new Meal();
        System.err.println("Meal " + meal.getName() + "is saved");
    }


}
