package controller;

import gestion.ClientesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Clientes;

@Named(value = "clientesController")
@SessionScoped
public class ClientesController extends Clientes implements Serializable {

    public List<Clientes> getClientes() {
        return ClientesGestion.getClientes();
    }

    //Este metodo se encarga de llamar el edita.xhtml por un cliente
    public String editaClientes(String idClientes) {
        Clientes elClientes = ClientesGestion.getCliente(idClientes);
        if (elClientes != null) {
            this.setUSER_ID(elClientes.getUSER_ID());
            this.setUSER_NAME1(elClientes.getUSER_NAME1());
            this.setUSER_NAME2(elClientes.getUSER_NAME2());
            this.setUSER_LASTNAME1(elClientes.getUSER_LASTNAME1());
            this.setUSER_LASTNAME2(elClientes.getUSER_LASTNAME2());
            this.setUSER_ID_CARD(elClientes.getUSER_ID_CARD());
            this.setUSER_EMAIL(elClientes.getUSER_EMAIL());
            this.setUSER_AUTH_ID(elClientes.getUSER_AUTH_ID());
            this.setCOUNTRY_ID(elClientes.getCOUNTRY_ID());
            this.setPROVINCE_ID(elClientes.getPROVINCE_ID());
            this.setDISTRICT_ID(elClientes.getDISTRICT_ID());
            this.setCANTON_ID(elClientes.getCANTON_ID());
            this.setUSER_ADDRESS(elClientes.getUSER_ADDRESS());
            return "editCliente.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el Cliente no exista.");
            FacesContext.getCurrentInstance().addMessage("editaClientesForm:identificacion", msg);
            return "listCliente.xhtml";
        }
    }

    //Metodo Encargado de Insertar Clientes.
    public String insertaClientes() {
        if (ClientesGestion.insertarClientes(this)) {
            return "listCliente?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el Cliente se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("insertaClientesForm:identificacion", msg);
            return "addCliente.xhtml";
        }

    }

    //Metodo Encargado de Modificar Estudiantes.
    public String modificarClientes() {
        if (ClientesGestion.modificarClientes(this)) {
            return "listCliente?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al modificar el Cliente.");
            FacesContext.getCurrentInstance().addMessage("editaClientesForm:identificacion", msg);
            return "editCliente.xhtml";
        }

    }

    //Metodo Encargado de Modificar Estudiantes.
    public String eliminarClientes() {
        if (ClientesGestion.eliminarClientes(this)) {
            return "listCliente?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ocurrio un error al eliminar el Cliente.");
            FacesContext.getCurrentInstance().addMessage("editaClientesForm:identificacion", msg);
            return "editCliente.xhtml";
        }
    }

    //---------------------------------------------------------------
    // SELECT DE ID's FORANEAS
    //---------------------------------------------------------------
    public List<String> getUserAuth() {
        return ClientesGestion.getUserAuth();
    }

    public List<String> getCountry() {
        return ClientesGestion.getCountry();
    }

    public List<String> getProv() {
        return ClientesGestion.getProv();
    }

    public List<String> getDist() {
        return ClientesGestion.getDist();
    }

    public List<String> getCant() {
        return ClientesGestion.getCant();
    }

}
