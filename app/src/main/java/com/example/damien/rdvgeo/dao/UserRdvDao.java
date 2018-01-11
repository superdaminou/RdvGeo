package com.example.damien.rdvgeo.dao;

import android.content.ContentValues;

import com.example.damien.rdvgeo.entities.User;
import com.example.damien.rdvgeo.entities.UserRdv;

/**
 * Created by damien on 11/01/2018.
 */

public class UserRdvDao extends DaoBase {

    public static final String TABLE_NAME= "userRdv";
    public static final String KEY ="id";
    public static final String USERID ="userid";
    public static final String RDVID ="rdvId";

    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERID+ " REAL," +
            RDVID + " REAL " +
            ") ";


    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void addUserRdv(String id, int userId, int rdvID){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("userId", userId);
        values.put("rdvId", rdvID);
        insert(UserRdv.class, values);

    }

    public void deleteUserRdv(){

    }

    public static void changeUserRdv(String username) {

    }

    public static UserRdv getUserRdv() {return new UserRdv();}

}
