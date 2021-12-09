/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ComisionGestion;
import gestion.TransporteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Comision;

/**
 *
 * @author fabry
 */
@Named(value = "comisionController")
@SessionScoped
public class ComisionController extends Comision implements Serializable {

    /**
     * Creates a new instance of ComisionController
     */
    public ComisionController() {
    }

    public List<String> getFactura() {
        return ComisionGestion.getFactura();
    }
    private List<String> factura = ComisionGestion.getFactura();

    public List<Comision> getComisiones() {
        return ComisionGestion.getComisiones();
    }

    public String editComision(String idComision) {
        Comision com = ComisionGestion.getComision(idComision);
        if (com != null) {
            this.setIdComision(com.getIdComision());
            this.setIdFactura(com.getIdFactura());
            this.setTotal(com.getTotal());
            this.setPorcentaje(com.getPorcentaje());
            this.setIdUsuario(com.getIdUsuario());

            return "editCom.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al editar");
            FacesContext.getCurrentInstance().addMessage("editComForm:porc", msg);
            return "listCom.xhtml";
        }
    }

    public String insertCom() {
        if (ComisionGestion.insertComision(this)) {
            return "listCom?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al insertar el registro");
            FacesContext.getCurrentInstance().addMessage("addComForm:porc", msg);
            return "addCom.xhtml";
        }

    }

    public String updateCom() {
        if (ComisionGestion.updateComision(this)) {
            return "listCom?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar el registro");
            FacesContext.getCurrentInstance().addMessage("editComForm:porc", msg);
            return "editCom.xhtml";
        }
    }

    public String delCom() {
        if (ComisionGestion.delComision(this)) {
            return "listCom?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al eliminar el registro");
            FacesContext.getCurrentInstance().addMessage("editComForm:factura", msg);
            return "editCom.xhtml";
        }

    }

}
