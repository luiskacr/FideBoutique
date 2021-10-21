package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    Connection con;
    String url = "jdbc:oracle:thin:@fide-oracle12.cq68yjobhyg4.us-east-2.rds.amazonaws.com:1521:ORCL";
    String user = "Muserroot";
    String password = "sfZeqK5uErr";

    public Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Data Base Conection Error!!");
            System.out.println("The exception raised is:" + e);
        }

        return con;
    }

}
