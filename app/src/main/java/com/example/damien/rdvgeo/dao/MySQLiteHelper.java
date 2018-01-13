package com.example.damien.rdvgeo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "RdvGeo.db";
    // Commande sql pour la création de la base de données

    private static final String CREATE_USER_TABLE = "create table if not exists "
            + UserDao.TABLE_NAME+ "(" +
            UserDao.KEY+ " INTEGER PRIMARY KEY," +
            UserDao.USERNAME+" TEXT )";

    private static final String DROP_TABLE = " drop table "+
            UserDao.TABLE_NAME +
            ','+
            RendezVousDao.TABLE_NAME;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(RendezVousDao.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE  " + UserDao.TABLE_NAME);
        db.execSQL("DROP TABLE  "+ RendezVousDao.TABLE_NAME);
        onCreate(db);
    }

}
