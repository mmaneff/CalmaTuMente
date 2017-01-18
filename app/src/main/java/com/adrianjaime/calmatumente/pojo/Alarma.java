package com.adrianjaime.calmatumente.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emaneff on 01/12/2016.
 * https://elbauldelprogramador.com/adapter-personalizado-en-android/
 */
//https://danielme.com/2013/10/09/diseno-android-listview-con-checkbox/
public class Alarma implements Parcelable {

    private long _id;
    private int hora;
    private int minuto;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabados;
    private boolean domingos;

    public Alarma(){}

    public Alarma(int hora, int minuto, boolean lunes, boolean martes, boolean miercoles,
                  boolean jueves, boolean viernes, boolean sabados, boolean domingos) {
        this.hora = hora;
        this.minuto = minuto;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabados = sabados;
        this.domingos = domingos;
    }

    public Alarma(Parcel in) {
        this.hora = in.readInt();
        this.minuto = in.readInt();
        this.lunes = in.readInt() == 1 ? true : false;
        this.martes = in.readInt() == 1 ? true : false;
        this.miercoles = in.readInt() == 1 ? true : false;
        this.jueves = in.readInt() == 1 ? true : false;
        this.viernes = in.readInt() == 1 ? true : false;
        this.sabados = in.readInt() == 1 ? true : false;
        this.domingos = in.readInt() == 1 ? true : false;
    }

    public long getId() { return _id; }
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
    public boolean getMiercoles() {
        return this.miercoles;
    }
    public boolean getJueves() {
        return this.jueves;
    }
    public boolean getViernes() {
        return this.viernes;
    }
    public boolean getSabados() {
        return this.sabados;
    }
    public boolean getDomingos() {
        return this.domingos;
    }

    public void setId(long id) { _id = id; }
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
    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }
    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }
    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }
    public void setSabados(boolean sabados) {
        this.sabados = sabados;
    }
    public void setDomingos(boolean domingos) {
        this.domingos = domingos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getHora());
        dest.writeInt(getMinuto());
        dest.writeInt(getLunes() ? 1 : 0);
    }

    /**
     *
     */
    public static final Parcelable.Creator<Alarma> CREATOR = new Parcelable.Creator<Alarma>() {
        public Alarma createFromParcel(Parcel in) {
            return new Alarma(in);
        }

        public Alarma[] newArray(int size) {
            return new Alarma[size];
        }
    };

    @Override
    public String toString() {
        return "Alarma: " + Integer.toString(this.hora) + ":" + Integer.toString(this.minuto);
    }
}
