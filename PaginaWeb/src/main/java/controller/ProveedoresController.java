/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProveedoresGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Proveedores;

/**
 *
 * @author fabry
 */
@Named(value = "proveedoresController")
@SessionScoped
public class ProveedoresController extends Proveedores implements Serializable {

    /**
     * Creates a new instance of ProveedoresController
     */
    public ProveedoresController() {
    }
    
    public List<Proveedores> getProveedores() {
        return ProveedoresGestion.getProveedores();
    }

    public String editProv(String idProveedor) {
        Proveedores prov = ProveedoresGestion.getProveedor(idProveedor);
        if (prov != null) {
            this.setIdProveedor(prov.getIdProveedor());
            this.setNombreProvedor(prov.getNombreProvedor());
            this.setDetalle(prov.getDetalle());
            this.setTelefono(prov.getTelefono());
            
            return "editProv.xhtml";
            
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ocurrió un error al editar");
            FacesContext.getCurrentInstance().addMessage("editProvForm:nombre", msg);
            return "listProv.xhtml";
        }
    }
    
    public String insertProv() {
        if (ProveedoresGestion.insertProv(this)) {
            return "listProv?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ocurrió un error al insertar el registro");
            FacesContext.getCurrentInstance().addMessage("addProvForm:id", msg);
            return "addProv.xhtml";
        }

    }


    public String updateProv() {        
        if (ProveedoresGestion.updateProv(this)) {
            return "listProv?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al modificar el registro");
            FacesContext.getCurrentInstance().addMessage("editProvForm:nombre", msg);
            return "editProv.xhtml";
        }

    }


    public String delProv() {
        if (ProveedoresGestion.delProv(this)) {
            return "listProv?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al eliminar el registro");
            FacesContext.getCurrentInstance().addMessage("editProvForm:nombre", msg);
            return "editProv.xhtml";
        }

    }

}
