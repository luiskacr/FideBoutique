/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ImportacionesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Importaciones;

@Named(value = "importacionesController")
@SessionScoped
public class ImportacionesController extends Importaciones implements Serializable {

    public List<String> getProv() {
        return ImportacionesGestion.getProv();
    }
    private List<String> proveedor = ImportacionesGestion.getProv();

    public List<String> getProd() {
        return ImportacionesGestion.getProd();
    }
    private List<String> producto = ImportacionesGestion.getProd();

    public List<Importaciones> getImportaciones() {
        return ImportacionesGestion.getImportaciones();
    }

    public String editaImportaciones(String idImportaciones) {
        Importaciones importacion = ImportacionesGestion.getImportaciones(idImportaciones);
        if (importacion != null) {
            this.setIdImportaciones(importacion.getIdImportaciones());
            this.setIdProveedor(importacion.getIdProveedor());
            this.setIdProducto(importacion.getIdProducto());
            this.setDescripcion(importacion.getDescripcion());
            this.setFechaImportacion(importacion.getFechaImportacion());
            this.setCantidad(importacion.getCantidad());

            return "editImportacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente No Existe.");
            FacesContext.getCurrentInstance().addMessage("editImportacionForm:identificador", msg);
            return "listImportacion.xhtml";
        }
    }

    public String insertarImportacion() {
        if (ImportacionesGestion.insertarImportacion(this)) {
            return "listImportacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el id se encuentra duplicado.");
            FacesContext.getCurrentInstance().addMessage("insertaImportacionForm:identificador", msg);
            return "addImportacion.xhtml";
        }
    }

    public String modificarImportaciones() {
        if (ImportacionesGestion.ModificarImportacion(this)) {
            return "listImportacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No se Pudo Modificar El Registro");
            FacesContext.getCurrentInstance().addMessage("editImportacionForm:identificador", msg);
            return "editImportacion.xhtml";
        }

    }

    public String eliminarImportaciones() {
        if (ImportacionesGestion.eliminarImportacion(this)) {
            return "listImportacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al eliminar.");
            FacesContext.getCurrentInstance().addMessage("editaImportacionForm:identificador", msg);
            return "editImportacion.xhtml";
        }
    }
}
