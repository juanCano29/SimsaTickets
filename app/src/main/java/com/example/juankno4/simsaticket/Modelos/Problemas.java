package com.example.juankno4.simsaticket.Modelos;

public class Problemas {
    public Integer id;
    public Integer CodEqTrab, CodTipoProblema;
    public String NotaProblema, prioridad, estatus;

    public Problemas(Integer id, Integer codEqTrab, Integer codTipoProblema, String notaProblema, String prioridad, String estatus) {
        this.id = id;
        CodEqTrab = codEqTrab;
        CodTipoProblema = codTipoProblema;
        NotaProblema = notaProblema;
        this.prioridad = prioridad;
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodEqTrab() {
        return CodEqTrab;
    }

    public void setCodEqTrab(Integer codEqTrab) {
        CodEqTrab = codEqTrab;
    }

    public Integer getCodTipoProblema() {
        return CodTipoProblema;
    }

    public void setCodTipoProblema(Integer codTipoProblema) {
        CodTipoProblema = codTipoProblema;
    }

    public String getNotaProblema() {
        return NotaProblema;
    }

    public void setNotaProblema(String notaProblema) {
        NotaProblema = notaProblema;
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
