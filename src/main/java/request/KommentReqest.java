package request;

public class KommentReqest {
    private String text;
    private int anzeigeId;
    private String username;

    public KommentReqest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnzeigeId() {
        return anzeigeId;
    }

    public void setAnzeigeId(int anzeigeId) {
        this.anzeigeId = anzeigeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
