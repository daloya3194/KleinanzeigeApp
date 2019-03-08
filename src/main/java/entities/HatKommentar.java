package entities;

public class HatKommentar {

    private int kommentarID;
    private String benutzername;
    private int anzeigeID;

    public HatKommentar() {
    }

    public HatKommentar(int kommentarID, String benutzername, int anzeigeID) {
        this.kommentarID = kommentarID;
        this.benutzername = benutzername;
        this.anzeigeID = anzeigeID;
    }

    public int getKommentarID() {
        return kommentarID;
    }

    public void setKommentarID(int kommentarID) {
        this.kommentarID = kommentarID;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public int getAnzeigeID() {
        return anzeigeID;
    }

    public void setAnzeigeID(int anzeigeID) {
        this.anzeigeID = anzeigeID;
    }
}
