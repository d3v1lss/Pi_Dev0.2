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
public class LigneCommande {

    private int idLigneCommande;
    private int idProduit;
    private String nomProduit;
    private int idCommande;
    private int Quantite;
    private Date dateAjout;

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    
    public int getIdLigneCommande() {
        return idLigneCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getQuantite() {
        return Quantite;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setIdLigneCommande(int idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "idLigneCommande=" + idLigneCommande + ", idProduit=" + idProduit + ", idCommande=" + idCommande + ", Quantite=" + Quantite + ", dateAjout=" + dateAjout + '}';
    }

    public LigneCommande() {
    }


}
