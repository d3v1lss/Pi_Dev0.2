/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Vladio
 */
public class Forum {
    private int id;
    private String nom;
    private String discription;

    public Forum(int id, String nom, String discription) {
        this.id = id;
        this.nom = nom;
        this.discription = discription;
    }

    public Forum(int id) {
        this.id = id;
    }

    public Forum(String nom) {
        this.nom = nom;
    }
    

    public Forum() {
    }

    public Forum(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDiscription() {
        return discription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", nom=" + nom + ", discription=" + discription + '}';
    }

    public Forum(String nom, String discription) {
        this.nom = nom;
        this.discription = discription;
    }
   
    
    
    
    
}
