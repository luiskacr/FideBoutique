/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.FacturaGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.Factura;

/**
 *
 * @author fabry
 */
@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Factura implements Serializable {

    /**
     * Creates a new instance of FacturaController
     */
    public FacturaController() {
    }
    
    public List<String> getId() {
        return FacturaGestion.getIdUser();
    }
    private List<String> id = FacturaGestion.getIdUser();

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
    
    private static final String DELETE_FACTURAS = "Delete from factura where IDFACTURA=?";
    
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
    
        public void respaldoFacturas() {
        ZipOutputStream out = null;
        try {
            String json = FacturaGestion.generarJsonFactura();
            File f = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/respaldo") + "Facturas.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("Facturas.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();
            File zipPath = new File(FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("/respaldo") + "Facturas.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/pdf");
            respuesta.setHeader("Content-disposition", "attachment; filename=Facturas.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FacturaController.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }
        
        
    
}