package example;

import model.Employe;
import repository.EmployeRepository;
import repository.Repository;
import util.DatabaseConnection;

import java.lang.invoke.StringConcatFactory;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {

        System.out.println("----------LISTANDO TODOS LOS EMPLEADOS-----");
        Repository<Employe> repository = new EmployeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("-------BUSCANDO POR ID-----");
        System.out.println(repository.getById(3));


        }
    }






