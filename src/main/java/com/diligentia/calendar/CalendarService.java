package com.diligentia.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class CalendarService {

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "Google Calendar API Java Quickstart";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/calendar-java-quickstart");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/calendar-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    public void sendEventToCalendar() {
        try {
        com.google.api.services.calendar.Calendar service = getCalendarService();

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

        DateTime startDateTime = new DateTime("2018-01-17T09:00:00+01:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2018-01-17T10:00:00+01:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);


        String calendarId = "mjhh5d18jc604m7ttujod1j9m4@group.calendar.google.com";
            event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static com.google.api.services.calendar.Calendar getCalendarService() throws IOException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                Quickstart.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

}
