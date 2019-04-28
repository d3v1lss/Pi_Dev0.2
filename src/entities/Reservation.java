/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Info-Sys
 */
public class Reservation {
    private int id;
    private int user_id;
    private int evenement_id;

    public Reservation(int id, int user_id, int evenement_id) {
        this.id = id;
        this.user_id = user_id;
        this.evenement_id = evenement_id;
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

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", user_id=" + user_id + ", evenement_id=" + evenement_id + '}';
    }

    public Reservation(int user_id, int evenement_id) {
        this.user_id = user_id;
        this.evenement_id = evenement_id;
    }
    
    
}
