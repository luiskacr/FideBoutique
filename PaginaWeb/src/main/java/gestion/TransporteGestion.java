package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Transporte;

public class TransporteGestion {

    private static final String SELECT_TRANSPORTES = "SELECT * FROM transporte";
    private static final String SELECT_TRANSPORTE = "SELECT * FROM transporte WHERE idTransporte=?";
    private static final String INSERT_TRANS = "INSERT INTO transporte (nombreT,idFactura) VALUES(?,?)";
    private static final String UPDATE_TRANS = "UPDATE transporte SET nombreT=?,idFactura=? WHERE idTransporte=?";
    private static final String DELETE_TRANS = "DELETE FROM transporte WHERE idTransporte=?";
    private static final String SELECT_FACTURA = "SELECT idFactura FROM factura";

    public static ArrayList<String> getFactura() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_FACTURA);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<Transporte> getTransportes() {
        ArrayList<Transporte> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_TRANSPORTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Transporte(datos.getInt(1),datos.getString(2),datos.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Transporte getTransporte(String idTransporte) {
        Transporte transporte = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_TRANSPORTE);
            consulta.setString(1, idTransporte);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                transporte = new Transporte(datos.getInt(1), datos.getString(2), datos.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transporte;
    }

    public static boolean insertTrans(Transporte transporte) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(INSERT_TRANS);
            sentencia.setString(1, transporte.getNombreT());
            sentencia.setInt(2, transporte.getIdFactura());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean updateTrans(Transporte transporte) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(UPDATE_TRANS);

            sentencia.setString(1, transporte.getNombreT());
            sentencia.setInt(2, transporte.getIdFactura());
            sentencia.setInt(3, transporte.getIdTransporte());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean delTrans(Transporte transporte) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(DELETE_TRANS);
            sentencia.setInt(1, transporte.getIdTransporte());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TransporteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
