package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Productos;
import model.Conexion;

public class ProductoGestion {

    private static final String SELECT_PRODUCTOS = "SELECT * FROM PRODUCT";
    private static final String SELECT_PRODUCTO = "SELECT * FROM PRODUCT WHERE PRODUCT_ID=?";
    private static final String INSERT_PRODUCTOS = "INSERT INTO PRODUCT (PRODUCT_NAME, TYPE_PRODUCT_ID, DEPARMENT_PRODUCT_ID, "
            + "BRAND_PRODUCT_ID, PRODUCT_DESCRIPTION, PRODUCT_QUANTITIES, PRODUCT_PRICE) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_PRODUCTOS = "UPDATE PRODUCT SET PRODUCT_NAME=?, TYPE_PRODUCT_ID=?, DEPARMENT_PRODUCT_ID=?, BRAND_PRODUCT_ID=?, "
            + "PRODUCT_DESCRIPTION=?, PRODUCT_QUANTITIES=?, PRODUCT_PRICE=? WHERE PRODUCT_ID=?";

    //------------ SENTENCIAS SELECT DE ID'S FORANEAS -------------------
    private static final String SELECT_TYPE = "SELECT TYPE_PRODUCT_ID FROM TYPE_PRODUCT";
    private static final String SELECT_DEPARTMENT = "SELECT DEPARMENT_PRODUCT_ID FROM DEPARMENT_PRODUCT";
    private static final String SELECT_BRAND = "SELECT BRAND_PRODUCT_ID FROM BRAND_PRODUCT";

    //Select Lista de Productos
    public static ArrayList<Productos> getProductos() {

        ArrayList<Productos> lista = new ArrayList<>();

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_PRODUCTOS);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                lista.add(new Productos(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getFloat(8)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    //Select 1 Producto
    public static Productos getProd(String id) {
        Productos prod = null;
        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_PRODUCTO);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                prod = new Productos(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getFloat(8
                        ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    //Insert Producto
    public static boolean insertProd(Productos prod) {
        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(INSERT_PRODUCTOS);
            ps.setString(1, prod.getPRODUCT_NAME());
            ps.setInt(2, prod.getTYPE_PRODUCT_ID());
            ps.setInt(3, prod.getDEPARMENT_PRODUCT_ID());
            ps.setInt(4, prod.getBRAND_PRODUCT_ID());
            ps.setString(5, prod.getPRODUCT_DESCRIPTION());
            ps.setInt(6, prod.getPRODUCT_QUANTITIES());
            ps.setFloat(7, prod.getPRODUCT_PRICE());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //Update Producto
    public static boolean updateProd(Productos prod) {
        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(UPDATE_PRODUCTOS);
            ps.setString(1, prod.getPRODUCT_NAME());
            ps.setInt(2, prod.getTYPE_PRODUCT_ID());
            ps.setInt(3, prod.getDEPARMENT_PRODUCT_ID());
            ps.setInt(4, prod.getBRAND_PRODUCT_ID());
            ps.setString(5, prod.getPRODUCT_DESCRIPTION());
            ps.setInt(6, prod.getPRODUCT_QUANTITIES());
            ps.setFloat(7, prod.getPRODUCT_PRICE());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    
    //TYPE ID
    public static ArrayList<String> getType() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_TYPE);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    //DEPARTMENT ID
    public static ArrayList<String> getDept() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_DEPARTMENT);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    //BRAND ID
    public static ArrayList<String> getBrand() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_BRAND);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
