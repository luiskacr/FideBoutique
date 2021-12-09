package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion() {

        Connection dbConnection = null;
        String url = "jdbc:oracle:thin:@fide-oracle12.cq68yjobhyg4.us-east-2.rds.amazonaws.com:1521:ORCL";
        String user = "Muserroot";
        String password = "sfZeqK5uErr";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dbConnection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {

            System.out.println("Data Base Conection Error!!");
            System.out.println("The exception raised is:" + e);
            String error = e.toString();

        }

        return dbConnection;
    }
}
