package com.example.damien.rdvgeo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.damien.rdvgeo.MySQLiteHelper;

/**
 * Created by damien on 10/01/2018.
 */

public abstract class DaoBase {

    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "RDVGEO.db";

    protected SQLiteDatabase mDb = null;
    protected MySQLiteHelper helper = null;

    public DaoBase() {
    }

    public DaoBase(Context pContext) {
        this.helper = new MySQLiteHelper(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = helper.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

