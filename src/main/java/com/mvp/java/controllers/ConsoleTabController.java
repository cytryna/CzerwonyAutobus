package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
import com.diligentia.czerwony.repository.RecipeRepository;
import com.mvp.java.gui.AlertBox;
import com.mvp.java.model.CalendarEventBuilder;
import com.mvp.java.services.MissionsService;
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
    @FXML private ListView<String> missionsList;
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
        ObservableList<String> missions = FXCollections.observableArrayList();
        recipeRepository.findAll().stream().forEach(recipe -> {
            System.err.println(recipe.getName());
            missions.add(recipe.getName());
        });

        missionsList.setItems(missions);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        recipeDescription.clear();
        final String selectedItem = missionsList.getSelectionModel().getSelectedItem();
        recipeDescription.positionCaret(0);
        recipeDescription.appendText(getInfo(selectedItem));
//        System.err.println("        System.err.println(calendarService) = "+calendarService);
//        calendarService.sendEventToCalendar(createEvent());
    }

    @Autowired
    private void setTabManager(TabPaneManger tabManager){
        this.tabManager = tabManager;
    }
 
    public String getInfo(String selectedItem) {
        String missionInfo = null ;
                
        try {
            missionInfo = recipeRepository.findOne(Long.valueOf(1)).getDescription();//TODO-rwichrowski zminić model listy @FXML private ListView<String> missionsList;
//            missionInfo = service.getMissionInfo(selectedItem);

            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem + "\n");
        } catch (Exception exception) {
            exception.printStackTrace (stackTraceWriter);
            getLog().appendText(stackTraceWriter.toString() + "\n");
        }
        
        return missionInfo;
    }

    public TextArea getRecipeDescription() {
        return recipeDescription;
    }

    public ListView<String> getMissionsList() {
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
        final String selectedRecipe = missionsList.getSelectionModel().getSelectedItem();
        calendarService.sendEventToCalendar(new CalendarEventBuilder().withSummary(selectedRecipe).withEventDate(selectedDate).withHourStart(10).build());
    }
}
