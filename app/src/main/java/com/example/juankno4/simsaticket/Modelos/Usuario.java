package com.example.juankno4.simsaticket.Modelos;

public class Usuario {
    public int id;
    public String NomUsuario;
    public String PassUsuario;
    public String api_token;
    public String NomPer;
    public int CodEmp;
    public int TipoP;


    public Usuario(int id, String nomUsuario, String passUsuario, String api_token, int codEmp, int tipoP, String nomPer) {
        this.id = id;
        NomUsuario = nomUsuario;
        PassUsuario = passUsuario;
        this.api_token = api_token;
        CodEmp = codEmp;
        TipoP=tipoP;
        NomPer = nomPer;
    }

    public String getNomPer()
    {
        return NomPer;
    }

    public void setNomPer(String nomPer)
    {
        NomPer = nomPer;
    }

    public int getTipoP() {
        return TipoP;
    }

    public void setTipoP(int tipoP) {
        TipoP = tipoP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        NomUsuario = nomUsuario;
    }

    public String getPassUsuario() {
        return PassUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        PassUsuario = passUsuario;
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
