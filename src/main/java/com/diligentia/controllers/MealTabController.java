package com.diligentia.controllers;

import com.diligentia.model.Meal;
import com.diligentia.repository.MealRepository;
import com.diligentia.services.CalendarService;
import com.diligentia.model.CalendarEventBuilder;
import com.diligentia.utils.LocalDateStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MealTabController implements Initializable {

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField mealNameTextField;


    @Autowired
    private MealRepository repository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        Meal meal = new Meal();
        meal.setName(mealNameTextField.getText());
        meal.setDescription(descriptionTextArea.getText());
        // TODO: 26.01.18 Dodac Kategorię posiłku
        repository.save(meal);
        System.err.println("Meal " + meal.getName() + " is saved");
    }


}
