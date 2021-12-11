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
 * @author fabry
 */
@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Productos implements Serializable {

    public ProductoController() {
    }

    public List<Productos> getProductos() {
        return ProductoGestion.getProductos();
    }

    public String editProd(String id) {
        Productos prod = ProductoGestion.getProd(id);
        if (prod != null) {
            this.setPRODUCT_ID(prod.getPRODUCT_ID());
            this.setPRODUCT_NAME(prod.getPRODUCT_NAME());
            this.setTYPE_PRODUCT_ID(prod.getTYPE_PRODUCT_ID());
            this.setDEPARMENT_PRODUCT_ID(prod.getDEPARMENT_PRODUCT_ID());
            this.setBRAND_PRODUCT_ID(prod.getBRAND_PRODUCT_ID());
            this.setPRODUCT_DESCRIPTION(prod.getPRODUCT_DESCRIPTION());
            this.setPRODUCT_QUANTITIES(prod.getPRODUCT_QUANTITIES());
            this.setPRODUCT_PRICE(prod.getPRODUCT_PRICE());

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

    private static final String DELETE_PRODUCTOS = "DELETE PRODUCT WHERE PRODUCT_ID=?";

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

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    public List<String> getType() {
        return ProductoGestion.getType();
    }

    public List<String> getDept() {
        return ProductoGestion.getDept();
    }

    public List<String> getBrand() {
        return ProductoGestion.getBrand();
    }

}
