/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Dorra
 */
public class Produit {

    private int id;
    private int tva_id;
    private String nom;
    private double prix;
    private String description;
    private String photo;
    private boolean disponible;

    public Produit(int id, String nom, double prix, String description, String photo, Boolean disponible) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.disponible = disponible;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit(String nom, double prix, String description, String photo) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
    }

    public Produit(String nom, double prix, String description, String photo, boolean disponible) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.photo = photo;
        this.disponible = disponible;
    }

    public Produit(int id, double prix, int tva_id, String nom, String description, String photo, boolean disponible) {
        this.id = id;
        this.tva_id = tva_id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.disponible = disponible;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTva_id() {
        return tva_id;
    }

    public void setTva_id(int tva_id) {
        this.tva_id = tva_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", tva_id=" + tva_id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", disponible=" + disponible + '}';
    }

}
