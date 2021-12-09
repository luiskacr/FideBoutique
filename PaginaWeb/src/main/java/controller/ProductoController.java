/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProductoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Conexion;
import model.Productos;

/**
 *
 * @author fabry
 */
@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Productos implements Serializable {

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
    }

    public List<String> getProveedor() {
        return ProductoGestion.getProveedor();
    }
    private List<String> prov = ProductoGestion.getProveedor();

    public List<Productos> getProductos() {
        return ProductoGestion.getProductos();
    }

    public String editProd(String id) {
        Productos prod = ProductoGestion.getProd(id);
        if (prod != null) {
            this.setId(prod.getId());
            this.setIdProv(prod.getIdProv());
            this.setNombreProd(prod.getNombreProd());
            this.setCantidad(prod.getCantidad());
            return "editProductos?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe la entrada.");
            FacesContext.getCurrentInstance().addMessage("editProdForm:prod", msg);

            return "listProductos?faces-redirect=true";
        }
    }

    public String insertProd() {
        if (ProductoGestion.insertProd(this)) {
            return "listProductos?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al insertar el registro");
            FacesContext.getCurrentInstance().addMessage("addProdForm:prod", msg);

            return "addProducto.xhtml";
        }
    }

    public String updateProd() {
        if (ProductoGestion.updateProd(this)) {

            return "listProductos?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al modificar el registro.");
            FacesContext.getCurrentInstance().addMessage("editProdForm:prod", msg);

            return "editProducto.xhtml";
        }

    }

    private static final String DELETE_PRODUCTOS = "Delete from PRODUCTOS where IDPRODUCTO=?";

    public String delProd(int id) {
        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(DELETE_PRODUCTOS);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "listProductos.xhtml";
    }
}
