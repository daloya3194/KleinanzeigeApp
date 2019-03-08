package entities;

public class Nachricht {

    private int id;
    private String text;
    private String absender;
    private String empfaenger;

    public Nachricht() {
    }

    public Nachricht(int id, String text, String absender, String empfaenger) {
        this.id = id;
        this.text = text;
        this.absender = absender;
        this.empfaenger = empfaenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAbsender() {
        return absender;
    }

    public void setAbsender(String absender) {
        this.absender = absender;
    }

    public String getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(String empfaenger) {
        this.empfaenger = empfaenger;
    }
}
