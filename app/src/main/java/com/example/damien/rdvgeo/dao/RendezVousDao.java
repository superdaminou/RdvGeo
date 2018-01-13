package com.example.damien.rdvgeo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.damien.rdvgeo.entities.RendezVous;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVousDao extends DaoBase{

    public static final String TABLE_NAME= "rendezvous";
    public static final String KEY ="id";
    public static final String NAME ="name";
    public static final String COORDX = "coordX";
    public static final String COORDY = "coordY";
    public static final String DATERDV = "daterdv";


    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT," +
            COORDX + " REAL, " +
            COORDY + " REAL, " +
            DATERDV + " TEXT " +
            ") ";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public RendezVousDao(){super();}

    public RendezVousDao(Context context){super((context));}

    public void addRendezvous(String nom, double coordx, double coordy, Date date){
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateS = sdf.format(new Date());
        values.put(NAME, nom);
        values.put(COORDX, coordx);
        values.put(COORDY, coordy);
        values.put(DATERDV,dateS);
        insert(RendezVous.class, values);
    }

    public void deleteRendezvous(Long id){
        delete(this.getClass(), id);
    }

    public static void changeRendezvous(String username) {

    }

    public List<RendezVous> getAllRdv(){
        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<String> allColumns = new ArrayList<String>();
        allColumns.add(KEY);
        allColumns.add(NAME);
        allColumns.add(COORDX);
        allColumns.add(COORDY);
        allColumns.add(DATERDV);

        List<RendezVous> rdvs = new ArrayList<RendezVous>();
        Cursor cursor = getAll(TABLE_NAME, allColumns.toArray(new String[0]) );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            try{
                RendezVous u  = new RendezVous(
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        (originalFormat.parse(cursor.getString(4))));
                rdvs.add(u);
                cursor.moveToNext();

            }catch (ParseException e){
                cursor.moveToNext();
            }
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return rdvs;
    }
}
