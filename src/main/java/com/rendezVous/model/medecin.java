package com.rendezVous.model;

public class medecin extends user{

    String specialite;

    public medecin(int id, String nom, String email, String mdps, String telephone, String specialite) {

        super(id, nom, email, mdps, telephone);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
