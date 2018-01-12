package com.example.damien.rdvgeo.entities;

/**
 * Created by damien on 11/01/2018.
 */

public class UserRdv {

    private int id;
    private int userId;
    private int rdvId;

    public UserRdv(){}

    public UserRdv(int id, int userId, int rdvId ){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getRdvId(){
        return rdvId;
    }

    public void setRdvId(int rdvId){
        this.rdvId=rdvId;
    }
}
