package utils;

import entities.Benutzer;
import entities.Kommentar;
import request.KommentReqest;

import java.util.List;

public interface KommentarDao {

    public Kommentar addKommentar (int id, String text, String benutzername);
    public List<Kommentar> getAllKommentar();
    public List<Kommentar> getKommentarByUser(Benutzer benutzer);
    public String deleteKommentar (Kommentar kommentar);

}
