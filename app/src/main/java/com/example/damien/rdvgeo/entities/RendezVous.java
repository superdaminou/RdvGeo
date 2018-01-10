package com.example.damien.rdvgeo.entities;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVous {


    private String nom;
    private double[] coordonnee;
    private String etat;


    public RendezVous(){

    }

    public RendezVous(String nom, String etat){
        setNom(nom);
        setEtat(etat);

    }

    public RendezVous(String nom, double[]coordonnee, String etat){

    }




    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double[] getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(double[] coordonnee) {
        this.coordonnee = coordonnee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


}
