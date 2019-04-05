package com.example.juankno4.simsaticket.Modelos;

public class Usuario {
    public int id;
    public String NomUsario;
    public String api_token;
    public int CodEmp;

    public Usuario(int id, String nomUsario, String api_token, int codEmp) {
        this.id = id;
        this.NomUsario = nomUsario;
        this.api_token = api_token;
        this.CodEmp = codEmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUsario() {
        return NomUsario;
    }

    public void setNomUsario(String nomUsario) {
        NomUsario = nomUsario;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public int getCodEmp() {
        return CodEmp;
    }

    public void setCodEmp(int codEmp) {
        CodEmp = codEmp;
    }


}
