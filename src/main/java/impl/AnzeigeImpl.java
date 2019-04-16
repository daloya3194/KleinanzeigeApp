package impl;

import entities.Anzeige;
import entities.Kommentar;
import request.AnzeigeRequest;
import utils.AnzeigeDao;
import utils.StoreException;

import java.sql.*;
import java.util.*;

public class AnzeigeImpl extends BaseService implements AnzeigeDao {


    public AnzeigeImpl (){
        super();
    }


    @Override
    public List<Anzeige> getAllAnzeige() throws StoreException {

        List<Anzeige> anzeigeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Anzeige");
            ResultSet rs = preparedStatement.executeQuery();
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
                anzeigeList.add(a);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return anzeigeList;
    }

    @Override
    public Anzeige addAnzeige(String titel, String text, Double preis, String ersteller, String kategorie) {
        int insered = 0;
        int insered2 = 0;
        Anzeige res = new Anzeige();

//        Random random = new Random();
//        int rand = random.nextInt(1000);
//        int id = (int) (new Date().getTime()/1000) + rand;

        String status = "aktiv";
//        Timestamp datum = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Anzeige (titel,text,preis,ersteller,status,kategorie) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, titel);
            preparedStatement.setString(2, text);
            preparedStatement.setDouble(3, preis);
            preparedStatement.setString(4, ersteller);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, kategorie);
            insered = preparedStatement.executeUpdate();
//            connection.commit();

        } catch (SQLException e){
            e.printStackTrace();
        }

//        try {
//            PreparedStatement preparedStatement1 = connection.prepareStatement("select id from Anzeige where erstellungsdatum = (select max(erstellungsdatum) from Anzeige)");
//            ResultSet id = preparedStatement1.executeQuery();
//            while (id.next()){
//                int idi = id.getInt("id");
//                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO HatKategorie (anzeigeID,kategorie) VALUES (?,?)");
//                preparedStatement2.setInt(1, idi);
//                preparedStatement2.setString(2, anzeige.getKategorie());
//                insered2 = preparedStatement2.executeUpdate();
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        if(insered == 1){
            try {
                connection.commit();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res.setTitel(titel);
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res.setTitel(null);

        }
        return res;

    }


//    @Override
//    public int getAllAnzeigeByUserNameVerkauft(String benutzername) {
//                int nberanzeigeI = 0;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("select count(anzeigeid) as nberanzeige from (select anzeigeID from Kauf where benutzername = ?)");
//            preparedStatement.setString(1, benutzername);
//            ResultSet nberanzeige = preparedStatement.executeQuery();
//            while (nberanzeige.next()){
//                nberanzeigeI = nberanzeige.getInt("nberanzeige");
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return nberanzeigeI;
//    }



    @Override
    public List<Anzeige> searchByTitel(String titel) {

        List<Anzeige> anzeigeList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Anzeige WHERE titel LIKE ?");
            ps.setString(1, "%" + titel + "%");
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
                anzeigeList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anzeigeList;
    }


    @Override

    // To update a Anzeige
    public Anzeige updateAnzeige(int id, String titel, String text, Double preis, String kategorie) {

        Anzeige anzeige = new Anzeige();
        int update = 0;

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Anzeige SET titel=?, text=?, preis=?, kategorie=? WHERE id=?");
            ps.setString(1, titel);
            ps.setString(2, text);
            ps.setDouble(3, preis);
            ps.setString(4, kategorie);
            ps.setInt(5, id);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (update == 1){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            anzeige.setTitel(titel);
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            anzeige = null;
        }
        return anzeige;
    }

    @Override
    public Anzeige deleteAnzeigeById(int id) {

        String result = "deleteOK";
        int delete = 0;
        Anzeige anzeige = new Anzeige();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Anzeige WHERE id=?");
            preparedStatement.setInt(1, id);
            delete = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Kauft WHERE anzeigeID=?");
//            preparedStatement.setInt(1, anzeige.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        if (delete == 1){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        anzeige.setTitel(result);
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        anzeige = null;
        }
        return anzeige;
    }

    @Override
    public Anzeige kaufAnzeige(int id, String benutzername) {

        Anzeige anzeige = new Anzeige();
        String result = "kaufOk";
        int ps1 = 0;
        int ps2 = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Kauft (benutzername,anzeigeID) VALUES (?,?)");
            preparedStatement.setString(1, benutzername);
            preparedStatement.setInt(2, id);
            ps1 = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Anzeige SET status='verkauft' WHERE id=?");
            ps.setInt(1, id);
            ps2 = ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(ps1 == 1 && ps2 == 1){
            try {
                connection.commit();
            } catch (SQLException e){
                e.printStackTrace();
            }
            anzeige.setTitel(result);
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            anzeige = null;

        }
        return anzeige;
    }

    @Override
    public List<Anzeige> getAnzeigeMitKommentare(int id) {

        List<Anzeige> anzeigeList = new ArrayList<>();


        try {
            PreparedStatement ps = connection.prepareStatement("SELECT a.*, k.* FROM Anzeige a, Kommentar k WHERE a.id=k.anzeigeID AND a.id=?");
            ps.setInt(1, id);
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
                Kommentar k = new Kommentar();
                k.setId(rs.getInt(9));
                k.setText(rs.getString(10));
                k.setErstellungsdatum(rs.getTimestamp(11));
                k.setUsername(rs.getString(12));
                k.setAnzeigeId(rs.getInt(13));
                a.setKommentar(k);
                anzeigeList.add(a);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return anzeigeList;
    }

    @Override
    public Anzeige getAnzeigeById(int id) {
        Anzeige a = new Anzeige();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Anzeige WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                a.setId(rs.getInt("id"));
                a.setTitel(rs.getString("titel"));
                a.setText(rs.getString("text"));
                a.setPreis(rs.getDouble("preis"));
                a.setErstellungsdatum(rs.getTimestamp("erstellungsdatum"));
                a.setErsteller(rs.getString("ersteller"));
                a.setStatus(rs.getString("status"));
                a.setKategorie(rs.getString("kategorie"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public List<Kommentar> getAnzeigeKommentare(int id) {

        List<Kommentar> kommentarList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Kommentar WHERE anzeigeId=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Kommentar k = new Kommentar();
                k.setId(rs.getInt(1));
                k.setText(rs.getString(2));
                k.setErstellungsdatum(rs.getTimestamp(3));
                k.setUsername(rs.getString(4));
                k.setAnzeigeId(rs.getInt(5));
                kommentarList.add(k);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return kommentarList;
    }
}
