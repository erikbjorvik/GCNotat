package com.erikbjorvik.googlecn.LokaleData;

import org.json.simple.JSONObject;

/**
 * Created by erikbjorvik on 23.02.16.
 */
public class DataSingleton {
    private static DataSingleton obj = null;
    private String[] liste = {"Ingen notater å vise."};
    private JSONObject[] jsonListe = null;

    protected DataSingleton() {

    }
    public static DataSingleton getInstance() {
        if(obj == null) {
            obj = new DataSingleton();
        }
        return obj;
    }

    public String[] getListe() {
        return liste;
    }

    public void setListe(String[] liste) {
        this.liste = liste;
    }

    public JSONObject[] getJsonListe() {
        return jsonListe;
    }

    public void setJsonListe(JSONObject[] jsonListe) {
        this.jsonListe = jsonListe;
    }

}
