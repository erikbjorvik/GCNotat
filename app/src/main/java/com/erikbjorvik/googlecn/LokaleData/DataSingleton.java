package com.erikbjorvik.googlecn.LokaleData;

import com.example.erikbjorvik.myapplication.backend.myApi.model.Entity;
import com.example.erikbjorvik.myapplication.backend.myApi.model.EntityCollection;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by erikbjorvik on 23.02.16.
 */
public class DataSingleton {
    private static DataSingleton obj = null;
    private String[] liste = {"Ingen notater å vise."};
    private EntityCollection entitetsListe = null;

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

    public EntityCollection getEntitetsListe() {
        return entitetsListe;
    }

    public void setEntitetsListe(EntityCollection entitetsListe) {
        this.entitetsListe = entitetsListe;
    }
}
