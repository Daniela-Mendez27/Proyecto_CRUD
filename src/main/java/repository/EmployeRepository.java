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
    public void save(Employe employe) throws SQLException {
        // Agregamos la actualización
        String sql;
        if (employe.getId() != null && employe.getId() > 0) {
            sql = "UPDATE employe SET First_name =?, Pa_surname =?, Ma_surname =?, email =?, salary =? WHERE id =? ";
        } else {
            sql = "INSERT INTO employe (first_name, pa_surname, ma_surname, email, salary) VALUES (?,?,?,?,?)";
        }
        try (PreparedStatement myStamt = getConnection().prepareStatement(sql)) {
            myStamt.setString(1, employe.getFirst_name());
            myStamt.setString(2, employe.getPa_surname());
            myStamt.setString(3, employe.getMa_surname());
            myStamt.setString(4, employe.getEmail());
            myStamt.setFloat(5, employe.getSalary());
            if (employe.getId() != null && employe.getId() > 0) {
                myStamt.setInt(6, employe.getId());

            }
            myStamt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement myStamt = getConnection().prepareStatement("DELETE FROM employe WHERE id=?")) {
            myStamt.setInt(1, id);
            myStamt.executeUpdate();
        }
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