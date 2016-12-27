package com.adrianjaime.calmatumente.pojo;



/**
 * Created by emaneff on 01/12/2016.
 */
//https://danielme.com/2013/10/09/diseno-android-listview-con-checkbox/
public class Alarma {

    private int hora;
    private int minuto;
    private boolean lunes;
    private boolean martes;

    public Alarma() {
        this.hora = 0;
        this.minuto = 0;
        this.lunes = false;
        this.martes = false;
    }


    public int getHora() {
        return this.hora;
    }
    public int getMinuto() {
        return this.minuto;
    }
    public boolean getLunes() {
        return this.lunes;
    }
    public boolean getMartes() {
        return this.martes;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }
    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    @Override
    public String toString() {
        return "Alarma: " + Integer.toString(this.hora) + ":" + Integer.toString(this.minuto);
    }
}
