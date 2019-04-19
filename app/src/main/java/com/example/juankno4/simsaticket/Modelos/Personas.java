package com.example.juankno4.simsaticket.Modelos;

public class Personas {

    public int id;
    public String NomEmp;
    public String ApPat;
    public String ApMat;
    public String TelRed;
    public String CelEmp;
    public String EmailEmp;
    public int CodTipoPersona;
    public int CodDepa;

    public Personas(int id, String nomEmp, String apPat, String apMat, String telRed, String celEmp, String emailEmp, int codTipoPersona, int codDepa) {
        this.id = id;
        this.NomEmp = nomEmp;
        this.ApPat = apPat;
        this.ApMat = apMat;
        this.TelRed = telRed;
        this.CelEmp = celEmp;
        this.EmailEmp = emailEmp;
        this.CodTipoPersona = codTipoPersona;
        this.CodDepa = codDepa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public String getApPat() {
        return ApPat;
    }

    public void setApPat(String apPat) {
        ApPat = apPat;
    }

    public String getApMat() {
        return ApMat;
    }

    public void setApMat(String apMat) {
        ApMat = apMat;
    }

    public String getTelRed() {
        return TelRed;
    }

    public void setTelRed(String telRed) {
        TelRed = telRed;
    }

    public String getCelEmp() {
        return CelEmp;
    }

    public void setCelEmp(String celEmp) {
        CelEmp = celEmp;
    }

    public String getEmailEmp() {
        return EmailEmp;
    }

    public void setEmailEmp(String emailEmp) {
        EmailEmp = emailEmp;
    }

    public int getCodTipoPersona() {
        return CodTipoPersona;
    }

    public void setCodTipoPersona(int codTipoPersona) {
        CodTipoPersona = codTipoPersona;
    }

    public int getCodDepa() {
        return CodDepa;
    }

    public void setCodDepa(int codDepa) {
        CodDepa = codDepa;
    }






}
