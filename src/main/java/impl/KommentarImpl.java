package impl;

import entities.Anzeige;
import entities.Benutzer;
import entities.Kommentar;
import request.KommentReqest;
import utils.DBUtil;
import utils.KommentarDao;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class KommentarImpl extends BaseService implements KommentarDao {

    public KommentarImpl(){
        super();
    }

    @Override
    public Kommentar addKommentar(int id, String benutzername, String text) {

        Kommentar kommentar = new Kommentar();
        int ins = 0;
        String res = "";

//        Random random = new Random();
//        int rand = random.nextInt(1000);
//        int id = (int) (new Date().getTime()/1000) + rand;
//
//        Timestamp datum = new Timestamp(System.currentTimeMillis());

        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO Kommentar (id,text,erstellungsdatum) VALUES (?,?,?)");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Kommentar (text, username, anzeigeId) VALUES (?, ?,?)");
//            ps.setInt(1, id);
            ps.setString(1, text);
            ps.setString(2, benutzername);
            ps.setInt(3, id);
//            ps.setTimestamp(3, datum);
            ins = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(ins == 1){
            try {
                connection.commit();
            } catch (SQLException e){
                e.printStackTrace();
            }
            kommentar.setText(text);
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            kommentar = null;

        }
        return kommentar;
    }

    @Override
    public List<Kommentar> getAllKommentar() throws StoreException {

        List<Kommentar> kommentarList = new ArrayList<>();

//        try {
//            PreparedStatement ps = connection.prepareStatement("select k.*, b.*, a.* from Kommentar k\n" +
//                    "left join  Benutzer b on k.username = b.benutzername\n" +
//                    "left join Anzeige a on a.id = k.anzeigeId;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Kommentar k = new Kommentar();
//                k.setId(rs.getInt("id"));
//                k.setText(rs.getString("text"));
//                k.setErstellungsdatum(rs.getTimestamp("erstellungsdatum"));
//                Benutzer b = new Benutzer();
//                // Todo create user object
//                b.setBenutzername(rs.getString("benutzername"));
//                b.setName(rs.getString("name"));
//                b.setEintrittsdatum(rs.getTimestamp("eintrittsdatum"));
//                k.setBenutzer(b);
//            /*    Anzeige a = new Anzeige();
//                // Todo create anzeige object
//                a.setTitel(rs.getString("titel"));
//                a.setText(rs.getString(12));
//                k.setAnzeige(a);*/
//                kommentarList.add(k);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Kommentar");
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

    @Override
    public List<Kommentar> getKommentarByUser(Benutzer benutzer) {
        return null;
    }

    @Override
    public String deleteKommentar(Kommentar kommentar) {
        int del = 0;
        String res = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Kommentar WHERE id=?");
            preparedStatement.setInt(1, kommentar.getId());
            del = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(del == 1){
            try {
                connection.commit();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res = "DELETE OK";
        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            res = "DELETE NOT OK";

        }
        return res;
    }
}
