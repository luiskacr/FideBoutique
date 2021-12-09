package controller;

import gestion.ClientesGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
            this.setIdClientes(elClientes.getIdClientes());
            this.setNombreCliente(elClientes.getNombreCliente());
            this.setDireccion(elClientes.getDireccion());
            this.setTelefono(elClientes.getTelefono());
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
    
       
     private boolean noImprimir = true;
    
     public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }
    
    public void respaldoClientes() {
        ZipOutputStream out = null;
        try {
            String json = ClientesGestion.generarJsonClientes();
            File f = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/respaldo") + "Clientes.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("Clientes.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();
            File zipPath = new File(FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("/respaldo") + "Clientes.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/pdf");
            respuesta.setHeader("Content-disposition", "attachment; filename=Clientes.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientesController.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

}
