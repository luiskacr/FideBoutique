/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Clientes;
import model.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author User
 */
@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }

    public void verPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/Reportes/reporte.jasper"));

            JasperPrint reporteJasper = JasperFillManager
                    .fillReport(jasper.getPath(),
                            null, Conexion.getConexion());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDF() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/Reportes/reporte.jasper"));

            JasperPrint reporteJasper = JasperFillManager
                    .fillReport(jasper.getPath(),
                            null, Conexion.getConexion());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=reporte.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
     public void certifica(Clientes cliente) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idClientes", cliente.getNombreCliente());
        parametros.put("nombre", cliente.getNombreCliente());
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/Reportes/reporte.jasper"));

            JasperPrint reporteJasper = JasperFillManager
                    .fillReport(jasper.getPath(),
                            parametros, Conexion.getConexion());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
     
     public void verPDFFactura() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/Reportes/reporteFactura.jasper"));

            JasperPrint reporteJasper = JasperFillManager
                    .fillReport(jasper.getPath(),
                            null, Conexion.getConexion());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFFactura() {
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/Reportes/reporteFactura.jasper"));

            JasperPrint reporteJasper = JasperFillManager
                    .fillReport(jasper.getPath(),
                            null, Conexion.getConexion());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=reporteFactura.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
