package com.example.juankno4.simsaticket.Modelos;

public class AdministrarTec {

    private Integer id;
    private String NomEmp;
    private String NombreProblema;

    public AdministrarTec(Integer id, String nomEmp, String nombreProblema) {
        this.id = id;
        NomEmp = nomEmp;
        NombreProblema = nombreProblema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public String getNombreProblema() {
        return NombreProblema;
    }

    public void setNombreProblema(String nombreProblema) {
        NombreProblema = nombreProblema;
    }
}
