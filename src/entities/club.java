/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author HCHAICHI
 */
public class club {

    private int id;
    private String nom;
    private String mail;
    private String discription;
    private int nbrparticipant;
    private String activite;
    private int president;
    private String statut;

    public club() {
    }

    public club(int id, String nom, String mail, String discription, int nbrparticipant, String activite, int president, String statut) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.discription = discription;
        this.nbrparticipant = nbrparticipant;
        this.activite = activite;
        this.president = president;
        this.statut = statut;
    }

    public club(String nom, String mail, String discription, int nbrparticipant, String activite, int president, String statut) {
        this.nom = nom;
        this.mail = mail;
        this.discription = discription;
        this.nbrparticipant = nbrparticipant;
        this.activite = activite;
        this.president = president;
        this.statut = statut;
    }

    public club(String nom, String mail, String discription,
            int nbrparticipant, String activite, String statut) {

        this.nom = nom;
        this.mail = mail;
        this.discription = discription;
        this.nbrparticipant = nbrparticipant;
        this.activite = activite;
        this.statut = statut;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(int nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public int getPresident() {
        return president;
    }

    public void setPresident(int president) {
        this.president = president;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final club other = (club) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "club{" + "id=" + id + ", nom=" + nom + ", mail=" + mail + ", discription=" + discription + ", nbrparticipant=" + nbrparticipant + ", activite=" + activite + ", president=" + president + ", statut=" + statut + '}';
    }

}
