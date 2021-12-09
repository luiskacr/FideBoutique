package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Importaciones;

public class ImportacionesGestion {

    private static final String SQL_SELECT_IMPORTACIONES = "SELECT * FROM IMPORTACIONES";
    private static final String SQL_SELECT_IMPORTACION = "SELECT * FROM IMPORTACIONES WHERE IdImportaciones=?";
    private static final String SQL_INSERT_IMPORTACION = "INSERT INTO IMPORTACIONES (idProveedor,idProducto,descripcion,fechaimportacion,Cantidad) values (?,?,?,?,?)";
    private static final String SQL_DELETE_IMPORTACION = "DELETE FROM IMPORTACIONES WHERE idImportaciones=?";
    private static final String SQL_UPDATE_IMPORTACION = "UPDATE IMPORTACIONES SET idProveedor=?,idProducto=?,descripcion=?,fechaimportacion=?,Cantidad=? WHERE idImportaciones=?";

    private static final String SELECT_Prov = "SELECT idProveedor FROM proveedores";
    private static final String SELECT_PROD = "SELECT idProducto FROM productos";

    public static ArrayList<String> getProv() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_Prov);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<String> getProd() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PROD);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<Importaciones> getImportaciones() {
        ArrayList<Importaciones> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_IMPORTACIONES);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new Importaciones(
                        datos.getInt(1),
                        datos.getInt(2),
                        datos.getInt(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getInt(6)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static Importaciones getImportaciones(String idImportaciones) {
        Importaciones importacion = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_IMPORTACION);
            consulta.setString(1, idImportaciones);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                importacion = new Importaciones(
                        datos.getInt(1),
                        datos.getInt(2),
                        datos.getInt(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getInt(6)
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return importacion;
    }

    public static boolean insertarImportacion(Importaciones importacion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_INSERT_IMPORTACION);
            sentencia.setInt(1, importacion.getIdProveedor());
            sentencia.setInt(2, importacion.getIdProducto());
            sentencia.setString(3, importacion.getDescripcion());
            sentencia.setObject(4, importacion.getFechaImportacion());
            sentencia.setInt(5, importacion.getCantidad());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean ModificarImportacion(Importaciones importacion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_UPDATE_IMPORTACION);
            sentencia.setInt(1, importacion.getIdProveedor());
            sentencia.setInt(2, importacion.getIdProducto());
            sentencia.setString(3, importacion.getDescripcion());
            sentencia.setObject(4, importacion.getFechaImportacion());
            sentencia.setInt(5, importacion.getCantidad());
            sentencia.setInt(6, importacion.getIdImportaciones());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean eliminarImportacion(Importaciones importacion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_DELETE_IMPORTACION);
            sentencia.setInt(1, importacion.getIdImportaciones());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ImportacionesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
