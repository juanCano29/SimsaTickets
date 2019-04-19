package com.example.juankno4.simsaticket.Modelos;

public class Datos {
    public  static  String  URL="http://www.simsa.mipantano.com/api";
    public static  Usuario usm;
    public static Personas per;

    public Datos(Usuario usm) {
        this.usm = usm;
    }

    public static Personas getPer() {
        return per;
    }

    public static void setPer(Personas per) {
        Datos.per = per;
    }

    public static void setUsm(Usuario usm) {
        Datos.usm = usm;
    }

    public static Usuario getpersona(){
        return usm;
    }
}
