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
public class listeworkshop {

    private int id;
    private String workshop;
    private String membres;

    public listeworkshop(String workshop, String membres) {
        this.workshop = workshop;
        this.membres = membres;
    }

    public listeworkshop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
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

        hash = 29 * hash + Objects.hashCode(this.workshop);
        hash = 29 * hash + Objects.hashCode(this.membres);
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
        final listeworkshop other = (listeworkshop) obj;

        if (!Objects.equals(this.workshop, other.workshop)) {
            return false;
        }
        if (!Objects.equals(this.membres, other.membres)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "listeworkshop{" + "id=" + id + ", workshop=" + workshop + ", membres=" + membres + '}';
    }

}
