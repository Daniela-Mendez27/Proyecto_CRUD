//esta clase se encarga de nuestra logíca para la conexión de la BD

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String url="jdbc:mysql://localhost:3306/project";
    private static String user="root";
    private static String pass="2731";
    private static Connection myCoon;

public static Connection getInstance() throws SQLException {
    if(myCoon == null){
      myCoon = DriverManager.getConnection(url,user,pass);

    }
    return myCoon;
}

}
