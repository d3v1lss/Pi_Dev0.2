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
public class Tva {
    private int  idTva;
    private String nom;
    private double valeur;
    private double multiplicate;

    public Tva(int idTva, String nom, double valeur, double multiplicate) {
        this.idTva = idTva;
        this.nom = nom;
        this.valeur = valeur;
        this.multiplicate = multiplicate;
    }

    public Tva() {
    }

    public int getIdTva() {
        return idTva;
    }

    public void setIdTva(int idTva) {
        this.idTva = idTva;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getMultiplicate() {
        return multiplicate;
    }

    public void setMultiplicate(double multiplicate) {
        this.multiplicate = multiplicate;
    }
    public String toString(){
        return nom;
    }
    
    
}
