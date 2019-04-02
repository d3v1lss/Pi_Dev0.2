/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Houssem
 */
public class Rate {
   
   // attributs 
   private int id,note;
   private String iduser,film; 
   
   // constructeurs 

    public Rate() {
    }

    public Rate(int note, String iduser, String film) {
        this.note = note;
        this.iduser = iduser;
        this.film = film;
    }
   

    
    
//getters ands setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
    
   
   
   
}
