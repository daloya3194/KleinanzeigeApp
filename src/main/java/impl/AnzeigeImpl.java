package impl;

import entities.Anzeige;
import request.AnzeigeRequest;
import utils.AnzeigeDao;
import utils.DBUtil;
import utils.StoreException;
import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
    public String addAnzeige(AnzeigeRequest anzeige) {
        int insered = 0;
        int insered2 = 0;
        String res = "";

//        Random random = new Random();
//        int rand = random.nextInt(1000);
//        int id = (int) (new Date().getTime()/1000) + rand;

        String status = "aktiv";
//        Timestamp datum = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Anzeige (titel,text,preis,ersteller,status,kategorie) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, anzeige.getTitel());
            preparedStatement.setString(2, anzeige.getText());
            preparedStatement.setDouble(3, anzeige.getPreis());
            preparedStatement.setString(4, anzeige.getErsteller());
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, anzeige.getKategorie());
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
            res = "ADD OK";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res = "ADD NOT OK";

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
    public String updateAnzeige(Anzeige anzeige) {
        int update = 0;
        String res = "";
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Anzeige SET titel=?, text=?, preis=?, kategorie=? WHERE id=?");
            ps.setString(1, anzeige.getTitel());
            ps.setString(2, anzeige.getText());
            ps.setDouble(3, anzeige.getPreis());
            ps.setString(4, anzeige.getKategorie());
            ps.setInt(5, anzeige.getId());
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
            res = "UPDATE OK";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res = "UPDATE NICHT OK";
        }
        return res;
    }

    @Override
    public String deleteAnzeigeById(Anzeige anzeige) {
        int delete = 0;
        String res = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Anzeige WHERE id=?");
            preparedStatement.setInt(1, anzeige.getId());
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
        res = "DELETE OK";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        res = "DELETE NICHT OK";
        }
        return res;
    }

    @Override
    public String kaufAnzeige(Anzeige anzeige) {

        int ps1 = 0;
        int ps2 = 0;
        String res = "";
        String benutzername = "k.ralf";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Kauft (benutzername,anzeigeID) VALUES (?,?)");
            preparedStatement.setString(1, benutzername);
            preparedStatement.setInt(2, anzeige.getId());
            ps1 = preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Anzeige SET status='verkauft' WHERE id=?");
            ps.setInt(1, anzeige.getId());
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
            res = "ANZEIGE GEKAUFT";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res = "ANZEIGE NICHT GEKAUFT";

        }
        return res;
    }

}
