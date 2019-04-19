/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ignitedev
 */
public class Commande {

    private int idCommande;
    private int reference;
    private Date dateAjout;
    private boolean valider;
    private int idUtilisateur;
    private String username;
    private double Total;

    public Commande(int reference, boolean valider, int idUtilisateur) {
        this.reference = reference;
        this.valider = valider;
        this.idUtilisateur = idUtilisateur;
    }

    public Commande() {
    }

    public Commande(int reference, boolean valider, String username) {
        this.reference = reference;
        this.valider = valider;
        this.username = username;
    }

    public Commande(int id, int reference, boolean valider, String username) {
        
        this.idCommande=id;
        this.reference = reference;
        this.valider = valider;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", reference=" + reference + ", dateAjout=" + dateAjout + ", Etat=" + valider + ", idUtilisateur=" + idUtilisateur + '}';
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getReference() {
        return reference;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

}
