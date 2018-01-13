package com.example.damien.rdvgeo.entities;

import android.content.Context;

import com.example.damien.rdvgeo.dao.UserDao;

/**
 * Created by damien on 10/01/2018.
 */

public class User {

    private int id;
    private String username;


    public User(String username){

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getUsername(){return username;};
    public void setUsername(String username){this.username=username;};

    public void createUser(Context context, int id, String username){
        UserDao dao  = new UserDao(context);
        dao.addUser(id, username);
    }


}
