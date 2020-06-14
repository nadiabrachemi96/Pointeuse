package com.upec.androidtemplate20192020.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Etudiant")
public  class Etudiant {

    @DatabaseField(columnName = "id",generatedId = true)
    private int id;

    @DatabaseField(columnName = "CNE")
    String CNE;
    @DatabaseField(columnName = "nom")
    String nom;
    @DatabaseField(columnName = "prenom")
    String prenom;
    @DatabaseField(columnName = "idClasse")
    String idClasse;
    @DatabaseField(columnName = "filiere")
    String filiere;
    @DatabaseField(columnName = "annee")
    String annee;

    public Etudiant() {
    }

    public Etudiant(String CNE, String nom, String prenom, String idClasse, String filiere, String annee) {
        this.CNE = CNE;
        this.nom = nom;
        this.prenom = prenom;
        this.idClasse = idClasse;
        this.filiere = filiere;
        this.annee = annee;


    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }
}