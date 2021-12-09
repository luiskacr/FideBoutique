package model;

import java.util.Date;

public class Importaciones {

        private int idImportaciones;
        private int idProveedor;
        private int idProducto;
        private String descripcion;
        private Date fechaImportacion;
        private int cantidad;

        public Importaciones() {
        }

        public Importaciones(int idImportaciones, int idProveedor, int idProducto, String descripcion, Date fechaImportacion, int cantidad) {
            this.idImportaciones = idImportaciones;
            this.idProveedor = idProveedor;
            this.idProducto = idProducto;
            this.descripcion = descripcion;
            this.fechaImportacion = fechaImportacion;
            this.cantidad = cantidad;
        }

        public int getIdImportaciones() {
            return idImportaciones;
        }

        public void setIdImportaciones(int idImportaciones) {
            this.idImportaciones = idImportaciones;
        }

        public int getIdProveedor() {
            return idProveedor;
        }

        public void setIdProveedor(int idProveedor) {
            this.idProveedor = idProveedor;
        }

        public int getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(int idProducto) {
            this.idProducto = idProducto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Date getFechaImportacion() {
            return fechaImportacion;
        }

        public void setFechaImportacion(Date fechaImportacion) {
            this.fechaImportacion = fechaImportacion;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

    }