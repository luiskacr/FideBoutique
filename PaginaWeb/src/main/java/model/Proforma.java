package model;

import java.util.Date;



public class Proforma {

    private int idProforma;
    private String nombreCliente;
    private String detalle;
    private int telefono;
    private Date fechaEmitida;
    private String vigencia;
    private double AproximadoTotal;

    public Proforma() {
    }

    public Proforma(int idProforma, String nombreCliente, String detalle, int telefono, Date fechaEmitida, String vigencia, double AproximadoTotal) {
        this.idProforma = idProforma;
        this.nombreCliente = nombreCliente;
        this.detalle = detalle;
        this.telefono = telefono;
        this.fechaEmitida = fechaEmitida;
        this.vigencia = vigencia;
        this.AproximadoTotal = AproximadoTotal;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFechaEmitida() {
        return fechaEmitida;
    }

    public void setFechaEmitida(Date fechaEmitida) {
        this.fechaEmitida = fechaEmitida;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public double getAproximadoTotal() {
        return AproximadoTotal;
    }

    public void setAproximadoTotal(double AproximadoTotal) {
        this.AproximadoTotal = AproximadoTotal;
    }

}
