package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Clientes;

/**
 * @author fabry
 */
public class ClientesGestion {

    private static final String SQL_SELECT_CLIENTES = "SELECT * FROM USER_WEB";
    private static final String SQL_SELECT_CLIENTE = "SELECT * FROM USER_WEB WHERE USER_ID=?";
    private static final String SQL_INSERT_CLIENTES = "INSERT INTO USER_WEB (USER_NAME1,USER_NAME2,USER_LASTNAME1,USER_LASTNAME2,USER_ID_CARD,USER_EMAIL,USER_AUTH_ID,COUNTRY_ID,PROVINCE_ID,DISTRICT_ID,CANTON_ID,USER_ADDRESS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENTES = "UPDATE USER_WEB SET USER_NAME1=?,USER_NAME2=?,USER_LASTNAME1=?,USER_LASTNAME2=?,USER_ID_CARD=?,USER_EMAIL=?,USER_AUTH_ID=?,COUNTRY_ID=?,PROVINCE_ID=?,DISTRICT_ID=?,CANTON_ID=?,USER_ADDRESS=? WHERE USER_ID=?";
    private static final String SQL_DELETE_CLIENTES = "DELETE USER_WEB WHERE USER_ID=?";

    //------------ SENTENCIAS SELECT DE ID'S FORANEAS -------------------
    private static final String SELECT_USER_AUTH_ID = "SELECT USER_AUTH_ID FROM USER_AUTH";
    private static final String SELECT_COUNTRY_ID = "SELECT COUNTRY_ID FROM COUNTRY";
    private static final String SELECT_PROVINCE_ID = "SELECT PROVINCE_ID FROM PROVINCE";
    private static final String SELECT_DISTRICT_ID = "SELECT DISTRICT_ID FROM DISTRICT";
    private static final String SELECT_CANTON_ID = "SELECT CANTON_ID FROM CANTON";

    public static ArrayList<Clientes> getClientes() {
        ArrayList<Clientes> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Clientes(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7),
                        datos.getInt(8),
                        datos.getInt(9),
                        datos.getInt(10),
                        datos.getInt(11),
                        datos.getInt(12),
                        datos.getString(13)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Clientes getCliente(String idClientes) {
        Clientes cliente = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_CLIENTE);
            consulta.setString(1, idClientes);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                cliente = new Clientes(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7),
                        datos.getInt(8),
                        datos.getInt(9),
                        datos.getInt(10),
                        datos.getInt(11),
                        datos.getInt(12),
                        datos.getString(13)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static boolean insertarClientes(Clientes clientes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_CLIENTES);
            sentencia.setString(1, clientes.getUSER_NAME1());
            sentencia.setString(2, clientes.getUSER_NAME2());
            sentencia.setString(3, clientes.getUSER_LASTNAME1());
            sentencia.setString(4, clientes.getUSER_LASTNAME1());
            sentencia.setString(5, clientes.getUSER_ID_CARD());
            sentencia.setString(6, clientes.getUSER_EMAIL());
            sentencia.setInt(7, clientes.getUSER_AUTH_ID());
            sentencia.setInt(8, clientes.getCOUNTRY_ID());
            sentencia.setInt(9, clientes.getPROVINCE_ID());
            sentencia.setInt(10, clientes.getDISTRICT_ID());
            sentencia.setInt(11, clientes.getCANTON_ID());
            sentencia.setString(12, clientes.getUSER_ADDRESS());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean modificarClientes(Clientes clientes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_CLIENTES);

            sentencia.setString(1, clientes.getUSER_NAME1());
            sentencia.setString(2, clientes.getUSER_NAME2());
            sentencia.setString(3, clientes.getUSER_LASTNAME1());
            sentencia.setString(4, clientes.getUSER_LASTNAME1());
            sentencia.setString(5, clientes.getUSER_ID_CARD());
            sentencia.setString(6, clientes.getUSER_EMAIL());
            sentencia.setInt(7, clientes.getUSER_AUTH_ID());
            sentencia.setInt(8, clientes.getCOUNTRY_ID());
            sentencia.setInt(9, clientes.getPROVINCE_ID());
            sentencia.setInt(10, clientes.getDISTRICT_ID());
            sentencia.setInt(11, clientes.getCANTON_ID());
            sentencia.setString(12, clientes.getUSER_ADDRESS());
            sentencia.setInt(13, clientes.getUSER_ID());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminarClientes(Clientes clientes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_DELETE_CLIENTES);
            sentencia.setInt(1, clientes.getUSER_ID());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    public static ArrayList<String> getUserAuth() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_USER_AUTH_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<String> getCountry() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_COUNTRY_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static ArrayList<String> getProv() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PROVINCE_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<String> getDist() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_DISTRICT_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<String> getCant() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_CANTON_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
