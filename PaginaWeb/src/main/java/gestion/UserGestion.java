package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.user;

public class UserGestion {

    private static final String SQL_SELECT_USERS = "SELECT * FROM USER_AUTH";
    private static final String SQL_SELECT_USER = "SELECT * FROM USER_AUTH WHERE USER_AUTH_ID=?";
    private static final String SQL_INSERT_USERS = "INSERT INTO USER_AUTH (USER_NAME,PASSWORD,USER_ROL) VALUES(?,?,?)";
    private static final String SQL_UPDATE_USERS = "UPDATE USER_AUTH SET USER_NAME=?,PASSWORD=?,USER_ROL=? WHERE USER_AUTH_ID=?";

    public static ArrayList<user> getUsers() {
        ArrayList<user> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_USERS);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new user(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static user getUser(String idUsuario) {
        user user = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_USER);
            consulta.setString(1, idUsuario);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                user = new user(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static boolean insertUser(user user) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_USERS);
            sentencia.setString(1, user.getUSER_NAME());
            sentencia.setString(2, user.getPASSWORD());
            sentencia.setString(3, user.getUSER_ROL());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean uptUser(user us) {

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_USERS);
            sentencia.setString(1, us.getUSER_NAME());
            sentencia.setString(2, us.getPASSWORD());
            sentencia.setString(3, us.getUSER_ROL());
            sentencia.setInt(4, us.getUSER_AUTH_ID());

            return sentencia.executeUpdate() > 0;

        } catch (Exception ex) {
            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<String> getRol() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Admin");
        lista.add("Supervisor");
        lista.add("dependent");
        lista.add("customer");

        return lista;
    }

}
