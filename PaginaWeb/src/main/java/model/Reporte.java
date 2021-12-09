package model;

public class Reporte {
    
       private String idReporte;
    private String nombre;
    private String apellido1;
    private String apellido2;

    public Reporte() {
    }

    public Reporte(String idReporte, String nombre, String apellido1, String apellido2) {
        this.idReporte = idReporte;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Override
    public String toString() {
        
        return "{\"Prospecto\":{\n\"cedula\":\""
                + idReporte + "\",\n\"nombre\":\""
                + nombre + "\",\n\"apellido1\":\""
                + apellido1 + "\",\n\"apellido2\":\""
                + apellido2 + "\",\n\"fechaNacimiento\":\"" + "\"\n}\n}";
                
               
    }
}

