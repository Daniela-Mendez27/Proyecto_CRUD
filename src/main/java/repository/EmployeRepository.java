package repository;

import model.Employe;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepository implements Repository<Employe> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employe> findAll() throws SQLException {
        List<Employe> employe = new ArrayList<>();
        try (Statement myStamt = getConnection().createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * FROM employe")) {
            while (myRes.next()) {
                Employe e = createEmploye(myRes);
                employe.add(e);
            }
        }
        return employe;
    }

    @Override
    public Employe getById(Integer id) throws SQLException {
        Employe employe = null;
        try (PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM employe WHERE  id = ?")) {


            myStamt.setInt(1, id);
            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    employe = createEmploye(myRes);
                }
            }
            return employe;
        }
    }


    @Override
    public void save(Employe employe) {
    }

    @Override
    public void delete(Integer id) {
    }

    private Employe createEmploye(ResultSet myRes) throws SQLException {
        Employe e = new Employe();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));

        return e;
    }

}