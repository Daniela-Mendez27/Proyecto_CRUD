package example;

import util.DatabaseConnection;

import java.lang.invoke.StringConcatFactory;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {


        /// AQUI SE CIERRAN LOS RECURSOS POR AutoClose
        try (Connection myConn = DatabaseConnection.getInstance();
               Statement myStamt = myConn.createStatement();
               ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees");)
        {
            while (myRes.next()) {
                System.out.println(myRes.getString("first_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }

    }
}

