package com.example.juankno4.simsaticket.Modelos;

public class EquipoTrabajo {
    public int id;
    public String Descripcion;
    public String NoSerie;
    public String TipoEquipo;
    public int CodEmp;

    public EquipoTrabajo(int id, String descripcion, String noSerie, String tipoEquipo, int codEmp) {
        this.id = id;
        Descripcion = descripcion;
        NoSerie = noSerie;
        TipoEquipo = tipoEquipo;
        CodEmp = codEmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNoSerie() {
        return NoSerie;
    }

    public void setNoSerie(String noSerie) {
        NoSerie = noSerie;
    }

    public String getTipoEquipo() {
        return TipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        TipoEquipo = tipoEquipo;
    }

    public int getCodEmp() {
        return CodEmp;
    }

    public void setCodEmp(int codEmp) {
        CodEmp = codEmp;
    }
}
