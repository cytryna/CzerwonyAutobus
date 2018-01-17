package com.mvp.java.controllers;

import com.diligentia.calendar.CalendarService;
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
    SystemRepository systemRepository;

    private TabPaneManger tabManager;

    public void initialize() {
        ObservableList<String> missions = FXCollections.observableArrayList("Apollo", "Shuttle", "Skylab");
        missionsList.setItems(missions);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        missionOverviewText.clear();
        final String selectedItem = missionsList.getSelectionModel().getSelectedItem();
        missionOverviewText.positionCaret(0);
        missionOverviewText.appendText(getInfo(selectedItem));
        System.err.println("        System.err.println(calendarService) = "+calendarService);
        calendarService.sendEventToCalendar(createEvent());
    }

    private Event createEvent() {
        Event event = new Event()
                .setSummary("Sos grzybowy")
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

    @Autowired
    private void setTabManager(TabPaneManger tabManager){
        this.tabManager = tabManager;
    }
 
    public String getInfo(String selectedItem) {
        String missionInfo = null ;
                
        try {
            missionInfo = service.getMissionInfo(selectedItem);
            systemRepository.findAll().stream().forEach(expense -> System.err.println(expense.getName()));
            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem + "\n");
        } catch (IOException exception) {
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
