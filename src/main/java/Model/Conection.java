
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
        Connection con;
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String user = "HR";
    String password = "12345";
    public Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            System.out.println("Data Base Conection Error!!");
            System.out.println("The exception raised is:" + e);
        }
        
        return con;
    }

}
