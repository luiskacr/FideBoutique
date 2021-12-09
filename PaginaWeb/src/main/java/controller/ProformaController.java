/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.Proforma;
import gestion.ProformaGestion;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@Named(value = "proformaController")
@SessionScoped
public class ProformaController extends Proforma implements Serializable {

    /**
     * Creates a new instance of ProformaController
     */
    public ProformaController() {
    }

    public List<Proforma> getProformas() {
        return ProformaGestion.getProformas();
    }

    public String editaProformas(String idProforma) {
        Proforma proforma = ProformaGestion.getProforma(idProforma);
        if (proforma != null) {
            this.setIdProforma(proforma.getIdProforma());
            this.setNombreCliente(proforma.getNombreCliente());
            this.setDetalle(proforma.getDetalle());
            this.setTelefono(proforma.getTelefono());
            this.setFechaEmitida(proforma.getFechaEmitida());
            this.setVigencia(proforma.getVigencia());
            this.setAproximadoTotal(proforma.getAproximadoTotal());

            return "editProforma.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente No Existe.");
            FacesContext.getCurrentInstance().addMessage("editaProformasForm:identificacion", msg);
            return "listProforma.xhtml";
        }
    }

    public String insertarProforma() {
        if (ProformaGestion.insertarProforma(this)) {
            return "listProforma.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el id se encuentra duplicado.");
            FacesContext.getCurrentInstance().addMessage("insertaProformaForm:identificador", msg);
            return "addProforma.xhtml";
        }
    }

    public String modificarProforma() {
        if (ProformaGestion.modificarProforma(this)) {
            return "listProforma.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No se Pudo Modificar El Registro");
            FacesContext.getCurrentInstance().addMessage("editaProformaForm:identificador", msg);
            return "editProforma.xhtml";
        }

    }

    public String eliminarProforma() {
        if (ProformaGestion.eliminarProforma(this)) {
            return "listProforma.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al eliminar.");
            FacesContext.getCurrentInstance().addMessage("editaProformaForm:identificador", msg);
            return "editProforma.xhtml";
        }

    }

}
