package com.example.damien.rdvgeo.dao;


import com.example.damien.rdvgeo.entities.User;

/**
 * Created by damien on 11/01/2018.
 */

public class UserDao extends DaoBase {

    public static final String TABLE_NAME= "user";
    public static final String KEY ="id";
    public static final String USERNAME ="username";

    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME+ " TEXT" +
            ") ";


    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void addRUser(String username){

    }

    public void deleteUser(){

    }

    public static void changeUser(String username) {

    }

    public static User getUser() {return new User("yolo");}

}
