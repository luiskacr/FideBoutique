package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Proforma;


public class ProformaGestion {

    private static final String SQL_SELECT_PROFORMAS = "SELECT * FROM PROFORMA";
    private static final String SQL_SELECT_PROFORMA = "SELECT * FROM proforma where idProforma=?";
    private static final String SQL_INSERT_PROFORMA = "INSERT INTO Proforma (idProforma,nombreCliente,detalle,telefono,fechaEmitida,vigencia,AproximadoTotal)"
            + " VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_PROFORMA = "DELETE FROM PROFORMA WHERE idProforma=?";
    private static final String SQL_UPDATE_PROFORMA
            = "UPDATE PROFORMA SET nombreCliente=?,Detalle=?,telefono=?,fechaEmitida=?,vigencia=?,AproximadoTotal=? WHERE idProforma=?";

    public static ArrayList<Proforma> getProformas() {
        ArrayList<Proforma> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_PROFORMAS);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Proforma(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getDate(5),
                        datos.getString(6),
                        datos.getInt(7)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProformaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public static Proforma getProforma(String idProforma) {
        Proforma proforma = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_PROFORMA);
            consulta.setString(1, idProforma);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                proforma = new Proforma(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getDate(5),
                        datos.getString(6),
                        datos.getInt(7)
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProformaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return proforma;
    }

    public static boolean insertarProforma(Proforma proforma) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_INSERT_PROFORMA);
            sentencia.setInt(1, proforma.getIdProforma());
            sentencia.setString(2, proforma.getNombreCliente());
            sentencia.setString(3, proforma.getDetalle());
            sentencia.setInt(4, proforma.getTelefono());
            sentencia.setObject(5, proforma.getFechaEmitida());
            sentencia.setString(6, proforma.getVigencia());
            sentencia.setDouble(7, proforma.getAproximadoTotal());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProformaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean modificarProforma(Proforma proforma) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_UPDATE_PROFORMA);
            sentencia.setString(1, proforma.getNombreCliente());
            sentencia.setString(2, proforma.getDetalle());
            sentencia.setInt(3, proforma.getTelefono());
            sentencia.setObject(4, proforma.getFechaEmitida());
            sentencia.setString(5, proforma.getVigencia());
            sentencia.setDouble(6, proforma.getAproximadoTotal());
            sentencia.setInt(7, proforma.getIdProforma());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProformaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean eliminarProforma(Proforma proforma) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_DELETE_PROFORMA);
            sentencia.setInt(1, proforma.getIdProforma());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProformaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
