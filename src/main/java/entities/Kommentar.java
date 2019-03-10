package entities;

import java.sql.Timestamp;

public class Kommentar {

    private int id;
    private String text;
    private Timestamp erstellungsdatum;
    private String username;
    private int anzeigeId;
//    private Benutzer benutzer;

//    private Anzeige anzeige;

    public Kommentar(){}

    public Kommentar(int id, String text, Timestamp erstellungsdatum, String username, int anzeigeId) {
        this.id = id;
        this.text = text;
        this.erstellungsdatum = erstellungsdatum;
        this.username = username;
        this.anzeigeId = anzeigeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(Timestamp erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

//    public Benutzer getBenutzer() {
//        return benutzer;
//    }
//
//    public void setBenutzer(Benutzer benutzer) {
//        this.benutzer = benutzer;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAnzeigeId() {
        return anzeigeId;
    }

    public void setAnzeigeId(int anzeigeId) {
        this.anzeigeId = anzeigeId;
    }

    //
//    public Anzeige getAnzeige() {
//        return anzeige;
//    }
//
//    public void setAnzeige(Anzeige anzeige) {
//        this.anzeige = anzeige;
//    }
}
