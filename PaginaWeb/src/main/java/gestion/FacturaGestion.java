package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Factura;

public class FacturaGestion {

    private static final String SELECT_FACTURAS = "SELECT * FROM INVOICE";
    private static final String SELECT_FACTURA = "SELECT * FROM INVOICE WHERE ID_INVOICE=?";
    private static final String INSERT_FACTURA = "INSERT INTO INVOICE (USER_ID, SALE_TOTAL, DISCOUNT_ID, PAYMENT_TYPE_ID, PAYMENT_STATE_ID) VALUES(?,?,?,?,?)";
    private static final String UPDATE_FACTURA = "UPDATE INVOICE SET USER_ID=?,SALE_TOTAL=?,DISCOUNT_ID=?,PAYMENT_TYPE_ID=?,PAYMENT_STATE_ID=? WHERE ID_INVOICE=?";

//------------ SENTENCIAS SELECT DE ID'S FORANEAS -------------------
    private static final String SELECT_USER = "SELECT USER_ID FROM USER_WEB";
    private static final String SELECT_DISCOUNT = "SELECT DISCOUNT_ID FROM DISCOUNT";
    private static final String SELECT_PAY_TYPE = "SELECT PAYMENT_TYPE_ID FROM PAYMENT_TYPE";
    private static final String SELECT_PAY_STATE = "SELECT PAYMENT_STATE_ID FROM PAYMENT_STATE";

    public static ArrayList<Factura> getFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SELECT_FACTURAS);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Factura(
                        datos.getInt(1),
                        datos.getInt(2),
                        datos.getDate(3),
                        datos.getFloat(4),
                        datos.getInt(5),
                        datos.getInt(6),
                        datos.getInt(7)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public static Factura getFactura(String idFactura) {
        Factura factura = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_FACTURA);
            consulta.setString(1, idFactura);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                factura = new Factura(
                        datos.getInt(1),
                        datos.getInt(2),
                        datos.getDate(3),
                        datos.getFloat(4),
                        datos.getInt(5),
                        datos.getInt(6),
                        datos.getInt(7)
                );
            }

        } catch (Exception ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;
    }

    public static boolean insertarFactura(Factura factura) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(INSERT_FACTURA);
            sentencia.setInt(1, factura.getUSER_ID());
            sentencia.setFloat(2, factura.getSALE_TOTAL());
            sentencia.setInt(3, factura.getDISCOUNT_ID());
            sentencia.setInt(4, factura.getPAYMENT_TYPE_ID());
            sentencia.setInt(5, factura.getPAYMENT_STATE_ID());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean editFactura(Factura factura) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(UPDATE_FACTURA);
            sentencia.setInt(1, factura.getUSER_ID());
            sentencia.setFloat(2, factura.getSALE_TOTAL());
            sentencia.setInt(3, factura.getDISCOUNT_ID());
            sentencia.setInt(4, factura.getPAYMENT_TYPE_ID());
            sentencia.setInt(5, factura.getPAYMENT_STATE_ID());
            sentencia.setInt(6, factura.getID_INVOICE());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    //USER
    public static ArrayList<String> getUser() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_USER);
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

    //DISCOUNT
    public static ArrayList<String> getDisc() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_DISCOUNT);
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

    //PAY_TYPE
    public static ArrayList<String> getPayType() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PAY_TYPE);
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

    //PAY_STATE
    public static ArrayList<String> getPayState() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PAY_STATE);
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
