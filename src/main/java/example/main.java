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


            System.out.println("/////LISTANDO///");
            repository.findAll().forEach(System.out::println);

            System.out.println("/////////INSERTANDO UN EMPLEADO//////////");
            Employe employe = new Employe();
            employe.setFirst_name("Alan");
            employe.setPa_surname("Rico");
            employe.setMa_surname("Sampedro");
            employe.setEmail("alans@gamil.com");
            employe.setSalary((float) 510);
            repository.save(employe);

            System.out.println("----------NUEVO EMPLEADO INSERTADO---------------");
            repository.findAll().forEach(System.out::println);
        }
        }

    }



