package com.rendezVous.model;

public class user {
    int id;
    String nom;
    String email;
    int mdps;
    int telephone;

    public user(int id, String nom, String email, int mdps, int telephone ) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mdps = mdps;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMdps() {
        return mdps;
    }

    public void setMdps(int mdps) {
        this.mdps = mdps;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }



}
