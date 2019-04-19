package com.example.juankno4.simsaticket.Modelos;

public class TipoProb {
    public Integer id;
    public String NombreProblema;

    public TipoProb(Integer id, String nombreProb) {
        this.id = id;
        NombreProblema = nombreProb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProblema() {
        return NombreProblema;
    }

    public void setNombreProblema(String nombreProblema) {
        NombreProblema = nombreProblema;
    }
}
