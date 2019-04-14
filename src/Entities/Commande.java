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
    private String Nom;
    private Date dateAjout;
    private boolean valider;
    private int idUtilisateur;
    private double Total ;

    public Commande(int idCommande, boolean valider, int idUtilisateur) {
        this.idCommande = idCommande;
        this.valider = valider;
        this.idUtilisateur = idUtilisateur;
    }

    public Commande() {
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", Nom=" + Nom + ", dateAjout=" + dateAjout + ", Etat=" + valider + ", idUtilisateur=" + idUtilisateur + '}';
    }

    public int getIdCommande() {
        return idCommande;
    }

    public String getNom() {
        return Nom;
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

    public void setNom(String Nom) {
        this.Nom = Nom;
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
