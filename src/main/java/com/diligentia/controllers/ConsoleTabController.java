package com.diligentia.controllers;

import com.diligentia.calendar.CalendarService;
import com.diligentia.model.Recipe;
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
    @FXML private ListView<Recipe> missionsList;
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
        ObservableList<Recipe> missions = FXCollections.observableArrayList();
        recipeRepository.findAll().stream().forEach(recipe -> {
            System.err.println(recipe.getName());
            missions.add(recipe);
        });

        missionsList.setItems(missions);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        recipeDescription.clear();
        final Recipe selectedItem = missionsList.getSelectionModel().getSelectedItem();
        recipeDescription.positionCaret(0);
        recipeDescription.appendText(getInfo(selectedItem));
//        System.err.println("        System.err.println(calendarService) = "+calendarService);
//        calendarService.sendEventToCalendar(createEvent());
    }

    @Autowired
    private void setTabManager(TabPaneManger tabManager){
        this.tabManager = tabManager;
    }
 
    public String getInfo(Recipe selectedItem) {
        String missionInfo = null ;
                
        try {
            missionInfo = recipeRepository.findOne(selectedItem.getId()).getDescription();
//            missionInfo = service.getMissionInfo(selectedItem);

            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem.getName() + "\n");
        } catch (Exception exception) {
            exception.printStackTrace (stackTraceWriter);
            getLog().appendText(stackTraceWriter.toString() + "\n");
        }
        
        return missionInfo;
    }

    public TextArea getRecipeDescription() {
        return recipeDescription;
    }

    public ListView<Recipe> getMissionsList() {
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
        final Recipe selectedRecipe = missionsList.getSelectionModel().getSelectedItem();
        calendarService.sendEventToCalendar(new CalendarEventBuilder().withSummary(selectedRecipe.getName()).withEventDate(selectedDate).withHourStart(10).build());
    }
}
