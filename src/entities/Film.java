/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Houssem
 */
import java.util.Date;

public class Film {

    // Déclaration des attiributs
    private int id;
    private String nom, discription, duree;
    private Date datesotie;
    private String image;
    // les constructeurs

    public Film() {
    }

    public Film(int id, String nom, String discription, String duree, Date datesotie) {
        this.id = id;
        this.nom = nom;
        this.discription = discription;
        this.duree = duree;
        this.datesotie = datesotie;
    }

    public Film(String nom, String discription, String duree, Date datesotie, String image) {
        
        this.nom = nom;
        this.discription = discription;
        this.duree = duree;
        this.datesotie = datesotie;
        this.image = image;
    }

    // Getters and setters
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Date getDatesotie() {
        return datesotie;
    }

    public void setDatesotie(Date datesotie) {
        this.datesotie = datesotie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", nom=" + nom + ", discription=" + discription + ", duree=" + duree + ", datesotie=" + datesotie + '}';
    }

}
