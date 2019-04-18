/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author HCHAICHI
 */
public class workshop {

    private int id;

    private int user_id;
    private int club_id;
    private String nom;
    private int nombreplaces;
    private Date datedebut;
    private Date datefin;
    private String discription;

    public workshop() {
    }

    public workshop(int user_id, int club_id, String nom, int nombreplaces, Date datedebut, Date datefin, String discription) {
        this.user_id = user_id;
        this.club_id = club_id;
        this.nom = nom;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.discription = discription;
    }

    public workshop(String nom, int nombreplaces, Date datedebut, Date datefin, String discription) {
        this.nom = nom;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.discription = discription;
    }

    public workshop(int user_id, String nom, int nombreplaces, Date datedebut, Date datefin, String discription) {
        this.user_id = user_id;
        this.nom = nom;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.discription = discription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nom);
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
        final workshop other = (workshop) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreplaces() {
        return nombreplaces;
    }

    public void setNombreplaces(int nombreplaces) {
        this.nombreplaces = nombreplaces;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "workshop{ nom=" + nom + ", nombreplaces=" + nombreplaces + ", datedebut=" + datedebut + ", datefin=" + datefin + ", discription=" + discription + '}';
    }

}
