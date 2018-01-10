package com.example.damien.rdvgeo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RdvGeo.db";
    public static final String TABLE_COMMENTS = "comments";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";



    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table if not exist "
            + RdvGeoContract.User.TABLE_USER+ "(" +
            RdvGeoContract.User._ID + " INTEGER PRIMARY KEY," +
            RdvGeoContract.User.COLUMN_USERNAME +" TEXT )";





    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}