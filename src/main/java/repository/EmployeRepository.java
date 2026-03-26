package repository;

import model.Employe;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepository implements Repository<Employe>
{
private Connection getConnection() throws SQLException{
    return DatabaseConnection.getInstance();
}

    @Override
    public List<Employe> findAll() throws SQLException {
        List<Employe> employe = new ArrayList<>();
        try (Statement myStamt = getConnection().createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * FROM employe")) {
            while (myRes.next()) {
                // CORREGIDO: Ahora sí guardamos el empleado en la lista
                employe.add(createEmploye(myRes));
            }
        }
        return employe;
    }

    // CORREGIDO: Agregamos throws SQLException a estos tres
    @Override
    public Employe getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void save(Employe employe) throws SQLException {
    }

    @Override
    public void delete(Integer id) throws SQLException {
    }

    private Employe createEmploye(ResultSet myRes) throws SQLException {
        Employe e = new Employe();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));

        // CORREGIDO: Usar set en lugar de get
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));

        // Asegúrate que en la clase Employe se llame setSalaty (o setSalary)
        e.setSalary(myRes.getFloat("salary"));

        return e;
    }