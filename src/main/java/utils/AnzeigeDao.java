package utils;

import entities.Anzeige;
import entities.Kommentar;
import request.AnzeigeRequest;

import java.util.List;

public interface AnzeigeDao {

    public List<Anzeige> getAllAnzeige();

    public Anzeige addAnzeige(String titel, String text, Double preis, String ersteller, String kategorie);

    public List<Anzeige> searchByTitel(String titel);

    public Anzeige updateAnzeige(int id, String titel, String text, Double preis, String kategorie);

    public  Anzeige deleteAnzeigeById(int id);

    public Anzeige kaufAnzeige(int id, String benutzername);

    public List<Anzeige> getAnzeigeMitKommentare(int id);

    public Anzeige getAnzeigeById (int id);

    public List<Kommentar> getAnzeigeKommentare (int id);
}
