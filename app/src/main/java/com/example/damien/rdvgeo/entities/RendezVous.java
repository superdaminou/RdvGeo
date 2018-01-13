package com.example.damien.rdvgeo.entities;

import android.content.Context;

import com.example.damien.rdvgeo.dao.RendezVousDao;

import java.util.Date;
import java.util.List;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVous {


    private String nom;
    private double coordonneeX;
    private double coordonneeY;
    private Date date;
    private String etat;


    public RendezVous() {

    }


    public RendezVous(String nom, double coordonneeX, double coordonneeY, Date date, String etat) {
        setNom(nom);
        setCoordonneeX(coordonneeX);
        setCoordonneeY(coordonneeY);
        setDate(date);
        setEtat(etat);

    }

    public RendezVous(String nom, Date date, String etat) {
        setNom(nom);
        setCoordonneeX(-12);
        setCoordonneeY(12.4);
        setDate(date);
        setEtat(etat);

    }

    public Long createRdv(Context context,RendezVous rdv){
        RendezVousDao dao = new RendezVousDao(context);
        Long id = dao.addRendezvous( rdv.getNom(), rdv.getCoordonneeX(), rdv.getCoordonneeY(), rdv.getDate(), rdv.getEtat());
        return id;
    }

    public static List<RendezVous> getAll(Context context){
        RendezVousDao dao = new RendezVousDao(context);
        return dao.getAllRdv();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCoordonneeY() {
        return coordonneeY;
    }

    public void setCoordonneeY(double coordonnee) {
        this.coordonneeY = coordonnee;
    }

    public double getCoordonneeX() {
        return coordonneeX;
    }

    public void setCoordonneeX(double coordonnee) {
        this.coordonneeX = coordonnee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat(){return etat;}

    public void setEtat(String etat){this.etat= etat;}


}
