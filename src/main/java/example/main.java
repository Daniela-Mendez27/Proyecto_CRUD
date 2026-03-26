package example;

import model.Employe;
import repository.EmployeRepository;
import repository.Repository;
import util.DatabaseConnection;

import java.lang.invoke.StringConcatFactory;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {


        /// AQUI SE CIERRAN LOS RECURSOS POR AutoClose
        try (Connection myConn = DatabaseConnection.getInstance()) {


            Repository<Employe> repository = new EmployeRepository();

            repository.findAll().forEach(System.out::println);

        }

    }
}


