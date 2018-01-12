package com.example.damien.rdvgeo.dao;

import android.content.ContentValues;

import com.example.damien.rdvgeo.entities.RendezVous;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVousDao extends DaoBase{

    public static final String TABLE_NAME= "rendezvous";
    public static final String KEY ="id";
    public static final String USERNAME ="name";
    public static final String COORDX = "coordX";
    public static final String COORDY = "coordY";
    public static final String ETAT = "etat";

    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME+ " TEXT," +
            COORDX + " REAL, " +
            COORDY + " REAL, " +
            ETAT + " Text " +
            ") ";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void addRendezvous(int id, String nom, String coordx, String coordy, String etat){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nom", nom);
        values.put("coordx", coordx);
        values.put("coordy", coordy);
        values.put("etat",etat);
        insert(RendezVous.class, values);
    }

    public void deleteRendezvous(){

    }

    public static void changeRendezvous(String username) {

    }

    public static RendezVous getRendezvous(){
        return new RendezVous("yolo", "yolo");
    }
}
