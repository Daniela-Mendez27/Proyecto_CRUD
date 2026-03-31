package example;

import model.Employe;
import repository.EmployeRepository;
import repository.Repository;
import util.DatabaseConnection;

import java.lang.invoke.StringConcatFactory;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {
        try (Connection myConn = DatabaseConnection.getInstance()) {

            if (myConn.getAutoCommit()) {
                myConn.setAutoCommit((false));
            }
            try {
                Repository<Employe> repository = new EmployeRepository(myConn);

                System.out.println("----------INSERTAR UN NUEVO CLIENTE------");

                Employe employe = new Employe();
                employe.setFirst_name("America");
                employe.setPa_surname("Lopez");
                employe.setMa_surname("Villa");
                employe.setEmail("ame2@example.com");
                employe.setSalary(4500F);
                employe.setCurp("AMEC234Y91JOLPSDET");
                repository.save(employe);
                myConn.commit();


            } catch (SQLException e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }
            }
        }
    }






