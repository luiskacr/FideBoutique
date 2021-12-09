package model;

public class Comision {

    private int idComision;
    private int idFactura;
    private double total;
    private double porcentaje;
    private int idUsuario;

    public Comision() {
    }

    public Comision(int idComision, int idFactura, double total, double porcentaje, int idUsuario) {
        this.idComision = idComision;
        this.idFactura = idFactura;
        this.total = total;
        this.porcentaje = porcentaje;
        this.idUsuario = idUsuario;
    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
