package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
import com.diligentia.czerwony.model.Recipe;
import com.diligentia.czerwony.repository.RecipeRepository;
import com.diligentia.czerwony.repository.SystemRepository;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.mvp.java.services.MissionsService;
import com.mvp.java.utils.DateTimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ConsoleTabController {

    @FXML private TextArea missionOverviewText;
    @FXML private ListView<String> missionsList;
    
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
        missionOverviewText.clear();
        final String selectedItem = missionsList.getSelectionModel().getSelectedItem();
        missionOverviewText.positionCaret(0);
        missionOverviewText.appendText(getInfo(selectedItem));
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
            missionInfo = recipeRepository.findOne(Long.valueOf(1)).getDescription();////TODO-rwichrowski zminić model listy @FXML private ListView<String> missionsList;
//            missionInfo = service.getMissionInfo(selectedItem);

            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem + "\n");
        } catch (Exception exception) {
            exception.printStackTrace (stackTraceWriter);
            getLog().appendText(stackTraceWriter.toString() + "\n");
        }
        
        return missionInfo;
    }

    public TextArea getMissionOverviewText() {
        return missionOverviewText;
    }

    public ListView<String> getMissionsList() {
        return missionsList;
    }
    
    private TextArea getLog(){
        return tabManager.getVisualLog();
    }

}
