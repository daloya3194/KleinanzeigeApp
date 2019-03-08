package entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
public class Benutzer {

    private String benutzername;
    private String name;
    private String password;
    private Timestamp eintrittsdatum;

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
}
