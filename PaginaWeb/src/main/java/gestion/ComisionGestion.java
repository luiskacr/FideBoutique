package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Comision;

public class ComisionGestion {

    private static final String SELECT_COMISIONES = "SELECT * FROM comision";
    private static final String SELECT_COMISION = "SELECT * FROM comision WHERE idComision=?";
    private static final String INSERT_COM = "INSERT INTO comision (idFactura,total,PorcentajeComison,idUsuario) VALUES(?,?,?,?)";
    private static final String UPDATE_COM = "UPDATE comision SET idFactura=?,total=?,PorcentajeComison=?,idUsuario=? WHERE idComision=?";
    private static final String DELETE_COM = "DELETE FROM comision WHERE idComision=?";

    private static final String SELECT_FACTURAS = "SELECT idFactura FROM factura";

    public static ArrayList<String> getFactura() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_FACTURAS);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static ArrayList<Comision> getComisiones() {
        ArrayList<Comision> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_COMISIONES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Comision(datos.getInt(1), datos.getInt(2), datos.getDouble(3), datos.getDouble(4), datos.getInt(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Comision getComision(String idComision) {
        Comision comision = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_COMISION);
            consulta.setString(1, idComision);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                comision = new Comision(datos.getInt(1), datos.getInt(2), datos.getDouble(3), datos.getDouble(4), datos.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comision;
    }

    //Inserta/actualiza la comisión y hace los cálculos correspondientes
    
    private static final String SELECT_TOTAL_FACTURA = "SELECT Total FROM factura WHERE idFactura=?";
    private static final String SELECT_USUARIO_FACTURA = "SELECT idUsuario FROM factura WHERE idFactura=?";

    private static double total;
    private static double totalFactura;
    private static double porcentaje;
    private static int usuario;

    public static boolean insertComision(Comision comision) {

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_USUARIO_FACTURA);
            ps.setInt(1, comision.getIdFactura());
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                usuario = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_TOTAL_FACTURA);
            ps.setInt(1, comision.getIdFactura());
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                totalFactura = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        porcentaje = comision.getPorcentaje();
        total = (porcentaje / 100) * totalFactura;

        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(INSERT_COM);

            sentencia.setInt(1, comision.getIdFactura());
            sentencia.setDouble(2, total);
            sentencia.setDouble(3, porcentaje);
            sentencia.setInt(4, usuario);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean updateComision(Comision comision) {

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_USUARIO_FACTURA);
            ps.setInt(1, comision.getIdFactura());
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                usuario = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_TOTAL_FACTURA);
            ps.setInt(1, comision.getIdFactura());
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                totalFactura = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        porcentaje = comision.getPorcentaje();
        total = (porcentaje / 100) * totalFactura;

        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(UPDATE_COM);

            sentencia.setInt(1, comision.getIdFactura());
            sentencia.setDouble(2, total);
            sentencia.setDouble(3, porcentaje);
            sentencia.setInt(4, usuario);
            sentencia.setInt(5, comision.getIdComision());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean delComision(Comision comision) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(DELETE_COM);
            sentencia.setInt(1, comision.getIdComision());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ComisionGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
