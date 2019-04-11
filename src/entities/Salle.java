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
public class Salle {
    
    //les attributs
    private int id , capacite;
    private String nom ;
    
    
    //les constructeurs 
     
    
    public Salle() {
    }

    public Salle( int capacite, String nom) {
        
        this.capacite = capacite;
        this.nom = nom;
    }
    
    
    //Getters and Setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", capacite=" + capacite + ", nom=" + nom + '}';
    }
    
    
    
}
