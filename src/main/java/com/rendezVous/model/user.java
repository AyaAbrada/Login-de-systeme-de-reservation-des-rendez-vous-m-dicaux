package com.rendezVous.model;

public class user {
    int userId;
    String nom;
    String email;
    String mdps;
    String telephone;

    public user(int userId, String nom, String email, String mdps,String telephone ) {
        this.userId = userId;
        this.nom = nom;
        this.email = email;
        this.mdps = mdps;
        this.telephone = telephone;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = userId;
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

    public String getMdps() {
        return mdps;
    }

    public void setMdps(String mdps) {
        this.mdps = mdps;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }



}
