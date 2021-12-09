package model;

public class Factura {
    private int idFactura;
    private String idUsuario;
    private String nombreCliente;
    private String Direccion;
    private String Detalle;
    private double total;

    public Factura() {
    }

    public Factura(int idFactura, String idUsuario, String nombreCliente, String Direccion, String Detalle, double total) {
        this.idFactura = idFactura;
        this.idUsuario = idUsuario;
        this.nombreCliente = nombreCliente;
        this.Direccion = Direccion;
        this.Detalle = Detalle;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}