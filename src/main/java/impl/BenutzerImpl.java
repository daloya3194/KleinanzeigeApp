package impl;

import entities.Anzeige;
import entities.Benutzer;
import entities.Kommentar;
import request.LoginRequest;
import request.RegisterRequest;
import utils.BenutzerDao;
import utils.DBUtil;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BenutzerImpl extends BaseService implements BenutzerDao {

    public BenutzerImpl(){
        super();
    }

    @Override
    public Benutzer login(String benutzername, String password) {
        Benutzer benutzer = new Benutzer();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT benutzername,password FROM Benutzer WHERE benutzername=? AND password=?");
            preparedStatement.setString(1, benutzername);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                String benutzername1 = rs.getString("benutzername");
                String password1 = rs.getString("password");
                benutzer.setBenutzername(benutzername1);
//                benutzer.setPassword(password1);
                benutzer.generateToken();
                return benutzer;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Benutzer> getAllBenutzer() throws StoreException {

          String eintrittsdatum;
          DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

        List<Benutzer> benutzerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Benutzer");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Benutzer b = new Benutzer();
                b.setBenutzername(rs.getString("benutzername"));
                b.setName(rs.getString("name"));
                b.setEintrittsdatum(rs.getTimestamp("eintrittsdatum"));
                benutzerList.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return benutzerList;
    }

    @Override
    public Benutzer getBenutzerByBenutzername(String benutzername) {
        Benutzer benutzer = new Benutzer();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Benutzer WHERE benutzername=?");
            ps.setString(1, benutzername);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                benutzer.setBenutzername(rs.getString("benutzername"));
                benutzer.setName(rs.getString("name"));
                benutzer.setEintrittsdatum(rs.getTimestamp("eintrittsdatum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return benutzer;
    }

    @Override
    public List<Anzeige> getBenutzerAnzeige(String benutzername) throws StoreException {
        List<Anzeige> anzeiges = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Anzeige WHERE ersteller=?");
            ps.setString(1, benutzername);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Anzeige a = new Anzeige();
                a.setId(rs.getInt("id"));
                a.setTitel(rs.getString("titel"));
                a.setText(rs.getString("text"));
                a.setPreis(rs.getDouble("preis"));
                a.setErstellungsdatum(rs.getTimestamp("erstellungsdatum"));
                a.setErsteller(rs.getString("ersteller"));
                a.setStatus(rs.getString("status"));
                a.setKategorie(rs.getString("kategorie"));
                anzeiges.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anzeiges;
    }

    @Override
    public Benutzer addBenutzer(String benutzername, String name, String password) {
        int insered = 0;
        Benutzer res = new Benutzer();
//        Timestamp datum = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Benutzer (benutzername,name,password) VALUES (?,?,?)");
            preparedStatement.setString(1, benutzername);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            insered = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (insered == 1){
            try {
                connection.commit();
            } catch (SQLException e){
                e.printStackTrace();
            }
        res.setBenutzername(benutzername);

        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
        res = null;
        }

        return res;

    }

    @Override
    public String deleteBenutzer(Benutzer benutzer) {
        int deleteB = 0;
        String res = "";

//        try {
//            PreparedStatement ps3 = connection.prepareStatement("DELETE FROM Kauft WHERE ersteller=?");
//            ps3.setString(1, benutzer.getBenutzername());
//            ps3.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Anzeige WHERE ersteller=?");
//            ps2.setString(1, benutzer.getBenutzername());
//            ps2.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Benutzer WHERE benutzername=?");
            ps.setString(1, benutzer.getBenutzername());
            deleteB = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (deleteB == 1){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res = "DELETE B OK";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res = "DELETE B NICHT OK";
        }
        return res;
    }

    @Override
    public void deleteBenutzerByBenutzerName() {

    }

    @Override
    public List<Benutzer> getBenutzerKommentare(String benutzername) {

        List<Benutzer> benutzerList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT b.*, k.* FROM Benutzer b, Kommentar k WHERE b.benutzername=k.username AND b.benutzername=?");
            ps.setString(1, benutzername);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Benutzer b = new Benutzer();
                b.setBenutzername(rs.getString("benutzername"));
                b.setName(rs.getString("name"));
                b.setEintrittsdatum(rs.getTimestamp("eintrittsdatum"));
                Kommentar k = new Kommentar();
                k.setId(rs.getInt(5));
                k.setText(rs.getString(6));
                k.setErstellungsdatum(rs.getTimestamp(7));
                k.setUsername(rs.getString(8));
                k.setAnzeigeId(rs.getInt(9));
                b.setKommentar(k);
                benutzerList.add(b);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return benutzerList;
    }
}
