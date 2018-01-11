package com.example.damien.rdvgeo.dao;

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

    public void addUserRdv(String username){

    }

    public void deleteUserRdv(){

    }

    public static void changeUserRdv(String username) {

    }

    public static UserRdv getUserRdv() {return new UserRdv();}

}
