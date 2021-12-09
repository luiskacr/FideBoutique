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

    private static final String SELECT_PRODUCTOS = "SELECT * FROM PRODUCTOS";
    private static final String SELECT_PRODUCTO = "SELECT * FROM PRODUCTOS WHERE IDPRODUCTO=?";
    private static final String INSERT_PRODUCTOS = "INSERT INTO PRODUCTOS (idProveedor,nombreProducto,cantidad)VALUES(?,?,?)";
    private static final String UPDATE_PRODUCTOS = "UPDATE PRODUCTOS SET IDPROVEEDOR=?,NOMBREPRODUCTO=?,CANTIDAD=? WHERE IDPRODUCTO=?";
    private static final String SELECT_PROV = "SELECT idProveedor FROM proveedores";

    public static ArrayList<String> getProveedor() {
        ArrayList<String> lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SELECT_PROV);
            ResultSet datos = consulta.executeQuery();

            while (datos != null && datos.next()) {
                lista.add(new String(datos.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    //Select Lista de Productos
    public static ArrayList<Productos> getProductos() {

        ArrayList<Productos> lista = new ArrayList<>();

        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(SELECT_PRODUCTOS);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                lista.add(new Productos(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
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
                prod = new Productos(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
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
            ps.setInt(1, prod.getIdProv());
            ps.setString(2, prod.getNombreProd());
            ps.setInt(3, prod.getCantidad());

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
            ps.setInt(1, prod.getIdProv());
            ps.setString(2, prod.getNombreProd());
            ps.setInt(3, prod.getCantidad());
            ps.setInt(4, prod.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
