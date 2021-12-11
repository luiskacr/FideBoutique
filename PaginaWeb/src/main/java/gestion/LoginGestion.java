package gestion;

import model.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user;

public class LoginGestion {

    private static final String SQL_VALIDA = "SELECT USER_NAME,PASSWORD,USER_ROL FROM USER_AUTH WHERE USER_NAME=? and PASSWORD=?";

    public static user valida(String USER_NAME, String PASSWORD) {

        user usuario = null;

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SQL_VALIDA);
            ps.setString(1, USER_NAME);
            ps.setString(2, PASSWORD);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new user();
                //asignar a usuario los parametros de las variables de la clase model
                usuario.setUSER_NAME(rs.getString(1));
                usuario.setPASSWORD(rs.getString(2));
                usuario.setUSER_ROL(rs.getString(3));
            }//if

        } catch (Exception ex) {

            Logger.getLogger(LoginGestion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex);

        }

        return usuario;

    }//metodo usuario
}
