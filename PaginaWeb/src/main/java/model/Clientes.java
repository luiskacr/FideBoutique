package model;

public class Clientes {
    
    private String idClientes;
    private String nombreCliente;
    private String Direccion;
    private int Telefono;    

    public Clientes() {
    }

    public Clientes(String idClientes, String nombreCliente, String Direccion, int Telefono) {
        this.idClientes = idClientes;
        this.nombreCliente = nombreCliente;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public String getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(String idClientes) {
        this.idClientes = idClientes;
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

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }    
}
