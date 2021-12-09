package model;

public class Transporte {

    private int idTransporte;
    private String nombreT;
    private int idFactura;

    public Transporte() {
    }

    public Transporte(int idTransporte, String nombreT, int idFactura) {
        this.idTransporte = idTransporte;
        this.nombreT = nombreT;
        this.idFactura = idFactura;
    }

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
