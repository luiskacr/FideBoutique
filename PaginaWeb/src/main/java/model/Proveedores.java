package model;

public class Proveedores {

    private int idProveedor;
    private String nombreProvedor;
    private String detalle;
    private int telefono;

    public Proveedores() {
    }

    public Proveedores(int idProveedor, String nombreProvedor, String detalle, int telefono) {
        this.idProveedor = idProveedor;
        this.nombreProvedor = nombreProvedor;
        this.detalle = detalle;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProvedor() {
        return nombreProvedor;
    }

    public void setNombreProvedor(String nombreProvedor) {
        this.nombreProvedor = nombreProvedor;
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
    
    
    
}
