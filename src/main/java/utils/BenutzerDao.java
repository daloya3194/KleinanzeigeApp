package utils;

import entities.Benutzer;
import request.LoginRequest;
import request.RegisterRequest;

import java.util.List;

public interface BenutzerDao {

    public String login (LoginRequest benutzer);

    public List<Benutzer> getAllBenutzer();

    public Benutzer getBenutzerByBenutzername(String benutzername);

    public String addBenutzer(RegisterRequest benutzer);

    public String deleteBenutzer(Benutzer benutzer);

    public void deleteBenutzerByBenutzerName();

    public List<Benutzer> getBenutzerKommentare(String benutzername);
}
