package com.example.erikbjorvik.myapplication.backend;

import java.util.Date;

/** The object model for the data we are sending through endpoints */
public class Notat {

    private String overskrift;
    private String notatet;
    private String datostreng;
    private String enhetsID;
    private String token;

    public Notat() {
        this.overskrift = "";
        this.notatet = "";
        this.datostreng = "";
        this.enhetsID = "";
    }

    public Notat(String overskrift, String notatet, String dato, String enhetsID, String token) {
        this.overskrift = overskrift;
        this.notatet = notatet;
        this.datostreng = dato;
        this.enhetsID = enhetsID;
        this.token = token;
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

    public String getDato() {
        return datostreng;
    }

    public void setDato(String dato) {
        this.datostreng = dato;
    }

    public String getEnhetsID() {
        return enhetsID;
    }

    public void setEnhetsID(String enhetsID) {
        this.enhetsID = enhetsID;
    }

    public void setData(String overskrift, String notatet, String dato, String enhetsID) {
        this.overskrift = overskrift;
        this.notatet = notatet;
        this.datostreng = dato;
        this.enhetsID = enhetsID;
    }

    public String getData() {
        return this.toString();
    }

    public String getDatostreng() {
        return datostreng;
    }

    public void setDatostreng(String datostreng) {
        this.datostreng = datostreng;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Notat{" +
                "overskrift='" + overskrift + '\'' +
                ", notatet='" + notatet + '\'' +
                ", dato=" + datostreng +
                ", enhetsID='" + enhetsID + '\'' +
                '}';
    }
}