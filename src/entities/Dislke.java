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
public class Dislke {
    private int id_dislke;
    private int id_user;
    private int id_soin;

    public Dislke(int id_user, int id_soin) {
        this.id_user = id_user;
        this.id_soin = id_soin;
    }

    public int getId_dislke() {
        return id_dislke;
    }

    public void setId_dislke(int id_dislke) {
        this.id_dislke = id_dislke;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_soin() {
        return id_soin;
    }

    public void setId_soin(int id_soin) {
        this.id_soin = id_soin;
    }

    @Override
    public String toString() {
        return "Dislke{" + "id_user=" + id_user + ", id_soin=" + id_soin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id_dislke;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dislke other = (Dislke) obj;
        if (this.id_dislke != other.id_dislke) {
            return false;
        }
        return true;
    }
    
    
}
