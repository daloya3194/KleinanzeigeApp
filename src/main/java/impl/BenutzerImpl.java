package impl;

import entities.Benutzer;
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
    public String login(LoginRequest benutzer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT benutzername,password FROM Benutzer WHERE benutzername=? AND password=?");
            preparedStatement.setString(1, benutzer.getBenutzername());
            preparedStatement.setString(2, benutzer.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return "LOGIN OK";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "LOGIN FAIL";
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
        return null;
    }

    @Override
    public String addBenutzer(RegisterRequest benutzer) {
        int insered = 0;
        String res = "";
//        Timestamp datum = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Benutzer (benutzername,name,password) VALUES (?,?,?)");
            preparedStatement.setString(1, benutzer.getBenutzername());
            preparedStatement.setString(2, benutzer.getName());
            preparedStatement.setString(3, benutzer.getPassword());
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
        res = "REGISTER OK";

        } else {
            try {
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
        res = "REGISTER NOT OK";
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
}
