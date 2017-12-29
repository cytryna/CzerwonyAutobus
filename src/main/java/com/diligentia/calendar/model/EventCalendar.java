package com.diligentia.calendar.model;

public class EventCalendar {

    private final String BEGIN = "BEGIN:VEVENT";
    private String DTSTART = "DTSTART:20171230T050000Z";
    private String DTEND = "DTEND:20171230T060000Z";
    private String DTSTAMP = "DTSTAMP:20171228T060911Z";
    private String UID = "UID:2eeo6tklv18d0dkectro1c3nqi@google.com ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED;X-NUM-GUE STS=0:mailto:mjhh5d18jc604m7ttujod1j9m4@group.calendar.google.com";
    private String CREATED = "CREATED:20171201T201215Z";
//    private String DESCRIPTION = "DESCRIPTION:<p>Składniki:</p><ul><li>1 szklanka suszonych grzybów</li><li>1 duża cebula</li><li>3 łyżki masła</li><li>Łyżka mąki</li><li>Sól</li><li>Pieprz biały</li><li>Majeranek</li></ul><p></p><p>Wykonanie:<br>Grzyby moczyłam kilka godzin (najlepiej zostawić na całą noc). Grzyby odcedziłam\, zalałam niewielką ilością wody i wstawiłam do gotowania. Obrałam i pokroiłam cebulę w kostkę i zeszkliłam na maśle. Obgotowane grzyby dodałam do przysmażonej cebuli. Chwilę razem smażyłam. Następnie podlałam wodą\, w której gotowały się grzyby. Na drugiej patelni na łyżce masła przesmażyłam mąkę i zrobiłam zasmażkę. Zasmażkę rozprowadziłam wywarem z grzybów energicznie mieszając\, aby nie zrobiły się grudki. Tak przygotowany sos doprawiłam do smaku solą\, pieprzem i majerankiem.</p>";
    private String DESCRIPTION = "<p>Składniki:</p><ul><li>1 szklanka suszonych grzybów</li><li>1" +
        " duża cebula</li><li>3 łyżki masła</li><li>Łyżka mąki</li><li>Sól</li><li>P" +
        "ieprz biały</li><li>Majeranek</li></ul><p></p><p>Wykonanie:<br>Grzyby moczy" +
        "łam kilka godzin (najlepiej zostawić na całą noc). Grzyby odcedziłam\\, zala" +
        "łam niewielką ilością wody i wstawiłam do gotowania. Obrałam i pokroiłam ce" +
        "bulę w kostkę i zeszkliłam na maśle. Obgotowane grzyby dodałam do przysmażo" +
        "nej cebuli. Chwilę razem smażyłam. Następnie podlałam wodą\\, w której gotow" +
        "ały się grzyby. Na drugiej patelni na łyżce masła przesmażyłam mąkę i zrobi" +
        "łam zasmażkę. Zasmażkę rozprowadziłam wywarem z grzybów energicznie mieszaj" +
        "ąc\\, aby nie zrobiły się grudki. Tak przygotowany sos doprawiłam do smaku s" +
        "olą\\, pieprzem i majerankiem.</p>";
    private String LAST_MODIFIED = "LAST-MODIFIED:20171217T120659Z";
    private String LOCATION = "LOCATION:";
    private String SEQUENCE = "SEQUENCE:21";
    private String STATUS = "STATUS:CONFIRMED";
    private String SUMMARY = "SUMMARY:Radek  Sos grzybowy";
    private String TRANSP = "TRANSP:OPAQUE";
    private final String END = "END:VEVENT";


    @Override
    public String toString() {
//        builder.append(System.lineSeparator());
        return "EventCalendar{" +
                "BEGIN='" + BEGIN + '\'' +
                ", DTSTART='" + DTSTART + '\'' +
                ", DTEND='" + DTEND + '\'' +
                ", DTSTAMP='" + DTSTAMP + '\'' +
                ", UID='" + UID + '\'' +
                ", CREATED='" + CREATED + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", LAST_MODIFIED='" + LAST_MODIFIED + '\'' +
                ", LOCATION='" + LOCATION + '\'' +
                ", SEQUENCE='" + SEQUENCE + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", SUMMARY='" + SUMMARY + '\'' +
                ", TRANSP='" + TRANSP + '\'' +
                ", END='" + END + '\'' +
                '}';
    }

    public String generateIcs() {
        return toString();
    }
}
