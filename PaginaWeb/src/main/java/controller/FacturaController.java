package controller;

import gestion.FacturaGestion;
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
import model.Factura;

/**
 * @author fabry
 */
@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Factura implements Serializable {

    public FacturaController() {
    }

    public List<Factura> getFacturas() {
        return FacturaGestion.getFacturas();
    }

    public String insertaFactura() {
        if (FacturaGestion.insertarFactura(this)) {
            return "listFacturas?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el idFactura  se encuentra duplicado.");
            FacesContext.getCurrentInstance().addMessage("insertaFacturaForm:identificacion", msg);
            return "addFacturas.xhtml";
        }
    }

    public String updateFactura() {
        if (FacturaGestion.editFactura(this)) {
            return "listFacturas?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el idFactura  se encuentra duplicado.");
            FacesContext.getCurrentInstance().addMessage("insertaFacturaForm:identificacion", msg);
            return "listFacturas.xhtml";
        }
    }

    public String editFactura(String id) {
        Factura fact = FacturaGestion.getFactura(id);
        if (fact != null) {
            this.setID_INVOICE(fact.getID_INVOICE());
            this.setUSER_ID(fact.getUSER_ID());
            this.setDATE_SELL(fact.getDATE_SELL());
            this.setSALE_TOTAL(fact.getSALE_TOTAL());
            this.setDISCOUNT_ID(fact.getDISCOUNT_ID());
            this.setPAYMENT_TYPE_ID(fact.getPAYMENT_TYPE_ID());
            this.setPAYMENT_STATE_ID(fact.getPAYMENT_STATE_ID());

            return "editFacturas?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe la entrada.");
            FacesContext.getCurrentInstance().addMessage("editFactform:facturas", msg);

            return "editFacturas?faces-redirect=true";
        }
    }

    private static final String DELETE_FACTURAS = "DELETE INVOICE WHERE ID_INVOICE=?";

    public String delFactura(int id) {
        try {
            PreparedStatement ps = Conexion.getConexion().prepareStatement(DELETE_FACTURAS);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "listFacturas.xhtml";
    }

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    public List<String> getUser() {
        return FacturaGestion.getUser();
    }

    public List<String> getDisc() {
        return FacturaGestion.getDisc();
    }

    public List<String> getPayType() {
        return FacturaGestion.getPayType();
    }

    public List<String> getPayState() {
        return FacturaGestion.getPayState();
    }
}
