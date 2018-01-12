package com.example.damien.rdvgeo.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.damien.rdvgeo.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String[] allColumns;

    public UserDao(){
        super();
    }
    public UserDao(Context context){
        super(context);
        allColumns = new String[]{KEY, USERNAME};
    }

    public void addUser(int id, String username){
        ContentValues values = new ContentValues();
        values.put("username", username);
        insert(User.class, values);
    }

    public void deleteUser(Long id){
        delete(this.getClass(), id);
    }

    public static void changeUser(String username) {

    }

    public static User getUser() {return new User("yolo");}

    public Collection<User> getAllUser(){
        Set<User> users = new HashSet<User>();
        Cursor cursor = getAll(this.getClass().getSimpleName(), allColumns );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User u  = new User(cursor.getString(1));
            users.add(u);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

}
