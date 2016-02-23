package com.example.erikbjorvik.myapplication.backend;

import java.util.Date;

/** The object model for the data we are sending through endpoints */
public class Notat {

    private String overskrift;
    private String notatet;
    private Date dato;
    private String enhetsID;

    public Notat() {
        this.overskrift = "";
        this.notatet = "";
        this.dato = null;
        this.enhetsID = "";
    }

    public Notat(String overskrift, String notatet, Date dato, String enhetsID) {
        this.overskrift = overskrift;
        this.notatet = notatet;
        this.dato = dato;
        this.enhetsID = enhetsID;
    }

    public String getOverskrift() {
        return overskrift;
    }

    public void setOverskrift(String overskrift) {
        this.overskrift = overskrift;
    }

    public String getNotatet() {
        return notatet;
    }

    public void setNotatet(String notatet) {
        this.notatet = notatet;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public String getEnhetsID() {
        return enhetsID;
    }

    public void setEnhetsID(String enhetsID) {
        this.enhetsID = enhetsID;
    }

    public void setData(String overskrift, String notatet, Date dato, String enhetsID) {
        this.overskrift = overskrift;
        this.notatet = notatet;
        this.dato = dato;
        this.enhetsID = enhetsID;
    }

    public Notat getData() {
        return this;
    }

    @Override
    public String toString() {
        return "Notat{" +
                "overskrift='" + overskrift + '\'' +
                ", notatet='" + notatet + '\'' +
                ", dato=" + dato +
                ", enhetsID='" + enhetsID + '\'' +
                '}';
    }
}