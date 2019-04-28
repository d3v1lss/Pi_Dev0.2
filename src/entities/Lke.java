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
public class Lke {
    private int id_lke;
    private int id_user;
    private int id_soin;

    public Lke(int id_user, int id_soin) {
        this.id_user = id_user;
        this.id_soin = id_soin;
    }

    public int getId_lke() {
        return id_lke;
    }

    public void setId_lke(int id_lke) {
        this.id_lke = id_lke;
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
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_lke;
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
        final Lke other = (Lke) obj;
        if (this.id_lke != other.id_lke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lke{" + "id_lke=" + id_lke + ", id_user=" + id_user + ", id_soin=" + id_soin + '}';
    }
    
    
}
