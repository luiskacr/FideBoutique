/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Clientes;

/**
 *
 * @author User
 */
public class ClientesGestion {

    private static final String SQL_SELECT_CLIENTES = "SELECT * FROM cliente";
    private static final String SQL_SELECT_CLIENTE = "Select * from cliente WHERE idClientes=?";
    private static final String SQL_INSERT_CLIENTES = "INSERT INTO cliente (nombreCliente,Direccion,Telefono,idClientes) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE_CLIENTES = "UPDATE cliente SET nombreCliente=?,Direccion=?,Telefono=? WHERE idClientes=?";
    private static final String SQL_DELETE_CLIENTES = "DELETE FROM Cliente WHERE idClientes=?";

    public static ArrayList<Clientes> getClientes() {
        ArrayList<Clientes> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Clientes(
                        datos.getString(4),
                        datos.getString(1),
                        datos.getString(2),
                        datos.getInt(3)
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
                        datos.getString(4),
                        datos.getString(1),
                        datos.getString(2),
                        datos.getInt(3));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public static boolean insertarClientes(Clientes clientes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_INSERT_CLIENTES);
            sentencia.setString(1, clientes.getNombreCliente());
            sentencia.setString(2, clientes.getDireccion());
            sentencia.setInt(3, clientes.getTelefono());
            sentencia.setString(4, clientes.getIdClientes());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean modificarClientes(Clientes clientes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().
                    prepareStatement(SQL_UPDATE_CLIENTES);

            sentencia.setString(1, clientes.getNombreCliente());
            sentencia.setString(2, clientes.getDireccion());
            sentencia.setInt(3, clientes.getTelefono());

            sentencia.setString(4, clientes.getIdClientes());
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
            sentencia.setString(1, clientes.getIdClientes());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String generarJson() {
        Clientes cliente = null;
        String json = "";

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                cliente = new Clientes(datos.getString(1), datos.getString(2), datos.getString(3), datos.getInt(4));

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("id", cliente.getIdClientes()).add("nombre", cliente.getNombreCliente()).add("direccion", cliente.getDireccion()).add("telefono",
                        cliente.getTelefono()).build();

                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objetoJson);
                if (json == null) {
                    json = tira.toString() + "\n";
                } else {
                    json = json + tira.toString() + "\n";
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static String generarJsonClientes() {
        Clientes cliente = null;
        String tiraJson = "";

        try {
            PreparedStatement consulta = Conexion.getConexion().
                    prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                cliente = new Clientes(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4).charAt(0)
                );

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("idClientes", cliente.getIdClientes())
                        .add("nombre Cliente", cliente.getNombreCliente())
                        .add("Direccion", cliente.getDireccion())
                        .add("Telefono", cliente.getTelefono()).build();

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
            Logger.getLogger(ClientesGestion.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

}
