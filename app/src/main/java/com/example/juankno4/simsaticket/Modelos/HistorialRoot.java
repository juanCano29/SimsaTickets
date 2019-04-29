package com.example.juankno4.simsaticket.Modelos;

public class HistorialRoot {
    public Integer id;
    public String empleado,tp,tecnico,estatus;

    public HistorialRoot(Integer id, String empleado, String tp, String tecnico, String estatus) {
        this.id = id;
        this.empleado = empleado;
        this.tp = tp;
        this.tecnico = tecnico;
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
