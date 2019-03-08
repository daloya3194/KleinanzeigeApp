package entities;

import java.sql.Timestamp;

public class Kommentar {

    private int id;
    private String text;
    private Timestamp erstellungsdatum;
    private Benutzer benutzer;
//    private Anzeige anzeige;

    public Kommentar(){}

    public Kommentar(int id, String text, Timestamp erstellungsdatum){
        this.id = id;
        this.text = text;
        this.erstellungsdatum = erstellungsdatum;
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

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
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
