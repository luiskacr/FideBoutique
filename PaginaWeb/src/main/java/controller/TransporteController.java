/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.TransporteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Transporte;

/**
 *
 * @author fabry
 */
@Named(value = "transporteController")
@SessionScoped
public class TransporteController extends Transporte implements Serializable {

    /**
     * Creates a new instance of TransporteController
     */
    public TransporteController() {
    }

    public List<String> getFactura() {
        return TransporteGestion.getFactura();
    }
    private List<String> factura = TransporteGestion.getFactura();

    public List<Transporte> getTransportes() {
        return TransporteGestion.getTransportes();
    }

    public String editTrans(String idTransporte) {
        Transporte trans = TransporteGestion.getTransporte(idTransporte);
        if (trans != null) {
            this.setIdTransporte(trans.getIdTransporte());
            this.setNombreT(trans.getNombreT());
            this.setIdFactura(trans.getIdFactura());

            return "editTrans.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al editar");
            FacesContext.getCurrentInstance().addMessage("editTransForm:nombre", msg);
            return "listTrans.xhtml";
        }
    }

    public String insertTrans() {
        if (TransporteGestion.insertTrans(this)) {
            return "listTrans?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al insertar el registro");
            FacesContext.getCurrentInstance().addMessage("addTransForm:nombre", msg);
            return "addTrans.xhtml";
        }

    }

    public String updateTrans() {
        if (TransporteGestion.updateTrans(this)) {
            return "listTrans?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al modificar el registro");
            FacesContext.getCurrentInstance().addMessage("editTransForm:nombre", msg);
            return "editTrans.xhtml";
        }

    }

    public String delTrans() {
        if (TransporteGestion.delTrans(this)) {
            return "listTrans?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al eliminar el registro");
            FacesContext.getCurrentInstance().addMessage("editTransForm:nombre", msg);
            return "editTrans.xhtml";
        }

    }

}
