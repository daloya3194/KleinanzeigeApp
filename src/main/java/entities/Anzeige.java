package entities;

import java.awt.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

public class Anzeige {

    private int id;
    private String titel;
    private String text;
    private double preis;
    private Timestamp erstellungsdatum;
    private String ersteller;
    private String status;
    private String kategorie;
    private Image image;
    private Kommentar kommentar;

    public Anzeige() {
    }

    public Anzeige(int id, String titel, String text, double preis, Timestamp erstellungsdatum, String ersteller, String status, String kategorie) {
        this.id = id;
        this.titel = titel;
        this.text = text;
        this.preis = preis;
        this.erstellungsdatum = erstellungsdatum;
        this.ersteller = ersteller;
        this.status = status;
        this.kategorie = kategorie;
    }

//    public Anzeige(String titel, String text, double preis, String ersteller, String kategorie){
//        this.titel = titel;
//        this.text = text;
//        this.preis = preis;
//        this.ersteller = ersteller;
//        this.kategorie = kategorie;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Timestamp getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(Timestamp erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public String getErsteller() {
        return ersteller;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Kommentar getKommentar() {
        return kommentar;
    }

    public void setKommentar(Kommentar kommentar) {
        this.kommentar = kommentar;
    }
}
