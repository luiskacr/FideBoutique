package model;

public class Productos {

    private int id;
    private int idProv;
    private String nombreProd;
    private int cantidad;

    public Productos() {
    }

    public Productos(int id, int idProv, String nombreProd, int cantidad) {
        this.id = id;
        this.idProv = idProv;
        this.nombreProd = nombreProd;
        this.cantidad = cantidad; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}