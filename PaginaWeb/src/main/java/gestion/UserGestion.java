//package gestion;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import model.Conexion;
//import model.user;
//
//public class UserGestion {
//
//    private static final String SQL_SELECT_USERS = "SELECT idUsuario,idRol,contrasena,nombreUsuario FROM usuario";
//    private static final String SQL_SELECT_USER = "SELECT idUsuario,idRol,contrasena,nombreUsuario FROM usuario WHERE idUsuario=?";
//    private static final String SQL_INSERT_USERS = "INSERT INTO usuario (idRol,contrasena,nombreUsuario) VALUES(?,md5(?),?)";
//    private static final String SQL_UPDATE_USERS = "UPDATE usuario SET nombreUsuario=?,idRol=? WHERE idUsuario=?";
//    private static final String SQL_DELETE_USERS = "DELETE FROM usuario WHERE idUsuario=?";
//
//    
//    public static ArrayList<String> getRol() {
//        ArrayList<String> lista = new ArrayList<>();
//
//        try {
//            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_USERS);
//            ResultSet datos = consulta.executeQuery();
//
//            while (datos != null && datos.next()) {
//                lista.add(new String(datos.getString(2)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public static ArrayList<user> getUsers() {
//        ArrayList<user> lista = new ArrayList<>();
//
//        try {
//            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_USERS);
//            ResultSet datos = consulta.executeQuery();
//
//            while (datos != null && datos.next()) {
//                lista.add(new user(datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4)));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public static user getUser(String idUsuario) {
//        user user = null;
//        try {
//            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_USER);
//            consulta.setString(1, idUsuario);
//            ResultSet datos = consulta.executeQuery();
//
//            while (datos != null && datos.next()) {
//                user = new user(datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return user;
//    }
//
//    public static boolean insertUser(user user) {
//        try {
//            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_USERS);
//            sentencia.setString(1, user.getIdRol());
//            sentencia.setString(2, user.getContasena());
//            sentencia.setString(3, user.getNombreUsuario());
//
//            return sentencia.executeUpdate() > 0;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public static boolean updateUser(user user) {
//        try {
//            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_USERS);
//            sentencia.setString(1, user.getNombreUsuario());
//            sentencia.setString(2, user.getIdRol());
//            sentencia.setString(3, user.getIdUsuario());
//
//            return sentencia.executeUpdate() > 0;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public static boolean deleteUser(user user) {
//        try {
//            PreparedStatement sentencia = Conexion.getConexion().
//                    prepareStatement(SQL_DELETE_USERS);
//            sentencia.setString(1, user.getIdUsuario());
//
//            return sentencia.executeUpdate() > 0;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserGestion.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//}
