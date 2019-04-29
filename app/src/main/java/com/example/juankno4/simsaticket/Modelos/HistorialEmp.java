package com.example.juankno4.simsaticket.Modelos;

public class HistorialEmp {
    public Integer id;
    public String Descripcion;
    public String NombreProblema;
    public String prioridad;
    public String estatus;

    public HistorialEmp(Integer id, String descripcion, String nombreProblema,
                        String prioridad, String estatus) {
        this.id = id;
        Descripcion = descripcion;
        NombreProblema = nombreProblema;
        this.prioridad = prioridad;
        this.estatus = estatus;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombreProblema() {
        return NombreProblema;
    }

    public void setNombreProblema(String nombreProblema) {
        NombreProblema = nombreProblema;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
