package utils;

import entities.Anzeige;
import entities.Benutzer;
import request.LoginRequest;
import request.RegisterRequest;

import java.util.List;

public interface BenutzerDao {

    public Benutzer login (String benutzername, String password);

    public List<Benutzer> getAllBenutzer();

    public Benutzer getBenutzerByBenutzername(String benutzername);

    public Benutzer addBenutzer(String benutzername, String name, String password);

    public String deleteBenutzer(Benutzer benutzer);

    public void deleteBenutzerByBenutzerName();

    public List<Benutzer> getBenutzerKommentare(String benutzername);

    public List<Anzeige> getBenutzerAnzeige(String benutzername);
}
