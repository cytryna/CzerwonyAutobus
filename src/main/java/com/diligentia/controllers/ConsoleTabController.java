package com.diligentia.controllers;

import com.diligentia.services.CalendarService;
import com.diligentia.model.Meal;
import com.diligentia.repository.RecipeRepository;
import com.diligentia.gui.AlertBox;
import com.diligentia.model.CalendarEventBuilder;
import com.diligentia.services.MissionsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.time.LocalDate;

@Component
public class ConsoleTabController {

    @FXML private TextArea recipeDescription;
    @FXML private ListView<Meal> missionsList;
    @FXML private DatePicker datePicker;

    @Autowired
    @Qualifier("stringPrintWriter")
    private PrintWriter stackTraceWriter;

    @Autowired
    private CalendarService calendarService;
    
    @Autowired
    MissionsService service;

    @Autowired
    RecipeRepository recipeRepository;

    private TabPaneManger tabManager;

    public void initialize() {
        ObservableList<Meal> missions = FXCollections.observableArrayList();
        recipeRepository.findAll().stream().forEach(meal -> {
            System.err.println(meal.getName());
            missions.add(meal);
        });

        missionsList.setItems(missions);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        recipeDescription.clear();
        final Meal selectedItem = missionsList.getSelectionModel().getSelectedItem();
        recipeDescription.positionCaret(0);
        if (selectedItem.getDescription() != null) {
            recipeDescription.appendText(selectedItem.getDescription());
            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem.getName() + "\n");
        }
//        System.err.println("        System.err.println(calendarService) = "+calendarService);
//        calendarService.sendEventToCalendar(createEvent());
    }

    @Autowired
    private void setTabManager(TabPaneManger tabManager){
        this.tabManager = tabManager;
    }
 
    public String getInfo(Meal selectedItem) {
        String missionInfo = null ;
                
        try {
            missionInfo = recipeRepository.findOne(selectedItem.getId()).getDescription();
//            missionInfo = service.getMissionInfo(selectedItem);


        } catch (Exception exception) {
            exception.printStackTrace (stackTraceWriter);
            getLog().appendText(stackTraceWriter.toString() + "\n");
        }
        
        return missionInfo;
    }

    public TextArea getRecipeDescription() {
        return recipeDescription;
    }

    public ListView<Meal> getMissionsList() {
        return missionsList;
    }
    
    private TextArea getLog(){
        return tabManager.getVisualLog();
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null || selectedDate.isBefore(LocalDate.now())) {
            AlertBox.show("Wybierz date z przyszłości");
            return;
        }
        final Meal selectedMeal = missionsList.getSelectionModel().getSelectedItem();
        calendarService.sendEventToCalendar(new CalendarEventBuilder().withSummary(selectedMeal.getName()).withDescription(selectedMeal.getDescription()).withEventDate(selectedDate).withHourStart(10).build());
    }
}
