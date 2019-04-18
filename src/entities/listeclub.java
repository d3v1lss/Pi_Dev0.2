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
public class listeclub {

    private int id;
    private String club;
    private String membres;

    public listeclub(String membres) {
        this.membres = membres;
    }

    public listeclub(String club, String membres) {
        this.club = club;
        this.membres = membres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getMembres() {
        return membres;
    }

    public void setMembres(String membres) {
        this.membres = membres;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + Objects.hashCode(this.club);
        hash = 89 * hash + Objects.hashCode(this.membres);
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
        final listeclub other = (listeclub) obj;

        if (!Objects.equals(this.club, other.club)) {
            return false;
        }
        if (!Objects.equals(this.membres, other.membres)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "listeclub{" + "id=" + id + ", club=" + club + ", membres=" + membres + '}';
    }

}
