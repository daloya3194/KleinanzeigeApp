package entities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
public class Benutzer {

    private String benutzername;
    private String name;
    private String password;
    private Timestamp eintrittsdatum;
    private Kommentar kommentar;
    private String token;

    public Benutzer() {
    }

    public Benutzer(String benutzername, String password){
        this.benutzername = benutzername;
        this.password = password;
    }

    public Benutzer(String benutzername, String name, String password, Timestamp eintrittsdatum) {
        this.benutzername = benutzername;
        this.name = name;
        this.password = password;
        this.eintrittsdatum = eintrittsdatum;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getEintrittsdatum() {
        return eintrittsdatum;
    }

    public void setEintrittsdatum(Timestamp eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Kommentar getKommentar() {
        return kommentar;
    }

    public void setKommentar(Kommentar kommentar) {
        this.kommentar = kommentar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void generateToken(){
        Algorithm algorithm = Algorithm.HMAC256("ZDKAHEOWKFASDFHIWEODJAFK");
        this.token = JWT.create()
                .withIssuer("INS2")
                .withClaim("Username", this.benutzername)
                .sign(algorithm);
    }
}
