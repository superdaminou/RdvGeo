package com.example.damien.rdvgeo.dao;

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

    public void addRendezvous(String username){

    }

    public void deleteRendezvous(){

    }

    public static void changeRendezvous(String username) {

    }

    public static RendezVous getRendezvous(){
        return new RendezVous("yolo", "yolo");
    }
}
