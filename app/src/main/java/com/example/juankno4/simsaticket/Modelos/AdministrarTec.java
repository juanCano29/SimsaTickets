package com.example.juankno4.simsaticket.Modelos;

public class AdministrarTec {

    private Integer id;
    private String NomEmp;
    private String NombreProblema;
    private String estatus;


    public AdministrarTec(Integer id, String nomEmp, String nombreProblema,String estatus) {
        this.id = id;
        NomEmp = nomEmp;
        NombreProblema = nombreProblema;
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
