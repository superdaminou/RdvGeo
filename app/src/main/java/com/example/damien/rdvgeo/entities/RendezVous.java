package com.example.damien.rdvgeo.entities;

import android.content.Context;

import com.example.damien.rdvgeo.dao.RendezVousDao;

import java.util.List;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVous {


    private String nom;
    private double coordonneeX;
    private double coordonneeY;
    private String etat;


    public RendezVous() {

    }

    public RendezVous(String nom, String etat) {
        setNom(nom);
        setEtat(etat);

    }

    public RendezVous(String nom, double coordonneeX, double coordonneeY, String etat) {
        setNom(nom);
        setCoordonneeX(coordonneeX);
        setCoordonneeY(coordonneeY);
        setEtat(etat);
    }

    public void createRdv(Context context, String nom, double coordonneeX, double coordonneeY, String etat){
        RendezVousDao dao = new RendezVousDao(context);
        dao.addRendezvous(1, nom, coordonneeX, coordonneeY, etat);
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


}
