package utils;

import entities.Anzeige;
import request.AnzeigeRequest;

import java.util.List;

public interface AnzeigeDao {

    public List<Anzeige> getAllAnzeige();

    public String addAnzeige(AnzeigeRequest anzeige);

    public List<Anzeige> searchByTitel(String titel);

    public String updateAnzeige(Anzeige anzeige);

    public  String deleteAnzeigeById(Anzeige anzeige);

    public String kaufAnzeige(Anzeige anzeige);

    public List<Anzeige> getAnzeigeMitKommentare(int id);
}
