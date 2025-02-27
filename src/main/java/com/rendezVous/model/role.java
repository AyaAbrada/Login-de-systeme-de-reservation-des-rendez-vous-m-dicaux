package com.rendezVous.model;

public class role {
    int id;
    int id_user ;
    String nom;

    public role(int id, int id_user, String nom) {
        this.id = id;
        this.id_user = id_user;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
