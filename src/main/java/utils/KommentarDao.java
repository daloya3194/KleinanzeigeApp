package utils;

import entities.Benutzer;
import entities.Kommentar;
import request.KommentReqest;

import java.util.List;

public interface KommentarDao {

    public String addKommentar (KommentReqest kommentar);
    public List<Kommentar> getAllKommentar();
    public List<Kommentar> getKommentarByUser(Benutzer benutzer);
    public String deleteKommentar (Kommentar kommentar);

}
