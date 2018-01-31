package com.diligentia.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabPaneManger {

    private final LoggerTabController loggerTabController;

    @FXML
    TabPane tabPane;
    @Autowired
    MenuTabController menuTabController;

    @Autowired
    public TabPaneManger(LoggerTabController loggerTabController) {
        this.loggerTabController = loggerTabController;
    }

    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if ("menuTab".equals(newValue.getId())) {
               menuTabController.refreshMeals();
           }
        });

    }

    public TextArea getVisualLog() {
        return loggerTabController.getLoggerTxtArea();
    }

}
