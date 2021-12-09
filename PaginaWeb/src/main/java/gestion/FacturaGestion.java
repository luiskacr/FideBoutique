package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Factura;

public class FacturaGestion {

    private static final String SQL_SELECT_Facturas = "SELECT * FROM INVOICE";
    private static final String SQL_SELECT_Factura = "SELECT * FROM factura where idFactura=?";
    private static final String SQL_INSERT_Facturas = "INSERT INTO factura (idUsuario,nombreCliente,Direccion,detalle,Total) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT idUsuario FROM usuario";

    public static ArrayList<String> getIdUser() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_ID);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<Factura> getFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_Facturas);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Factura(
                        datos.getInt(1),
                        datos.getDate(2),
                        datos.getFloat(3),
                        datos.getInt(4),
                        datos.getInt(5),
                        datos.getInt(6)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public static Factura getFactura(int idFactura) {
        Factura factura = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_Factura);
            consulta.setInt(1, idFactura);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                factura = new Factura(
                        datos.getInt(1),
                        datos.getDate(2),
                        datos.getFloat(3),
                        datos.getInt(4),
                        datos.getInt(5),
                        datos.getInt(6
                ));
            }

        } catch (Exception ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return factura;
    }

   /* public static boolean insertarFactura(Factura factura) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_INSERT_Facturas);
            sentencia.setString(1, factura.getIdUsuario());
            sentencia.setString(2, factura.getNombreCliente());
            sentencia.setString(3, factura.getDireccion());
            sentencia.setString(4, factura.getDetalle());
            sentencia.setDouble(5, factura.getTotal());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }


    public static String generarJsonFactura() {
        Factura factura = null;
        String tiraJson = "";

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_Facturas);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                factura = new Factura(
                        datos.getString(1).charAt(0),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6).charAt(0)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("idFactura", factura.getIdFactura())
                        .add("idUsuario", factura.getIdUsuario())
                        .add("Nombre Cliente", factura.getNombreCliente())
                        .add("Direccion", factura.getDireccion())
                        .add("Detalle", factura.getDetalle())
                        .add("Total", factura.getTotal())
                        .build();

                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objetoJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
*/
}
