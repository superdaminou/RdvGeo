package com.example.damien.rdvgeo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public abstract class DaoBase {

    protected final static int VERSION =8;
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

    private SQLiteDatabase getReadDB(){
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase getWriteDB(){
        return helper.getWritableDatabase();
    }

    public Long insert(Class clazz, ContentValues values) {
        SQLiteDatabase db = open();
        Long id = db.insert(
                clazz.getSimpleName(),
                null,
                values
        );
        db.close();
        return id;
    }

    public void delete(Class clazz, Long id ){
        mDb.delete(clazz.getSimpleName(), "id = ?", new String[] {String.valueOf(id)});
    }

    public Cursor getAll(String table, String[] column  ){
        SQLiteDatabase db = getReadDB();
        return db.query(table,
                column, null, null, null, null, null);

    }

    public void update(String table, String toChange, String newValue, Long id ){
        SQLiteDatabase db = getWriteDB();
        String strSQL = "UPDATE " + table + " SET " + toChange+ "=" + "'" +newValue +"'"+" WHERE id = "+ id;

        db.execSQL(strSQL);
    }


}

