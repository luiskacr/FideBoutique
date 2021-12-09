package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Proveedores;

public class ProveedoresGestion {

    private static final String SELECT_PROVS = "SELECT * FROM proveedores";
    private static final String SELECT_PROV = "SELECT * FROM proveedores WHERE idProveedor=?";
    private static final String INSERT_PROV = "INSERT INTO proveedores (idProveedor,nombreProvedor,detalle,telefono) VALUES(?,?,?,?)";
    private static final String UPDATE_PROV = "UPDATE proveedores SET nombreProvedor=?,detalle=?,telefono=? WHERE idProveedor=?";
    private static final String DELETE_PROV = "DELETE FROM proveedores WHERE idProveedor=?";
    
    public static ArrayList<Proveedores> getProveedores() {
        ArrayList<Proveedores> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PROVS);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Proveedores(datos.getInt(1),datos.getString(2),datos.getString(3),datos.getInt(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Proveedores getProveedor(String idProveedor) {
        Proveedores proveedor = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PROV);
            consulta.setString(1, idProveedor);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                proveedor = new Proveedores(datos.getInt(1),datos.getString(2),datos.getString(3),datos.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }

    public static boolean insertProv(Proveedores proveedor) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(INSERT_PROV);
            sentencia.setInt(1, proveedor.getIdProveedor());
            sentencia.setString(2, proveedor.getNombreProvedor());
            sentencia.setString(3, proveedor.getDetalle());
            sentencia.setInt(4, proveedor.getTelefono());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean updateProv(Proveedores proveedor) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(UPDATE_PROV);

            sentencia.setString(1, proveedor.getNombreProvedor());
            sentencia.setString(2, proveedor.getDetalle());
            sentencia.setInt(3, proveedor.getTelefono());
            sentencia.setInt(4, proveedor.getIdProveedor());
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean delProv(Proveedores proveedor) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(DELETE_PROV);
            sentencia.setInt(1, proveedor.getIdProveedor());
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

