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
public class Favoris {
    
    // attributs
    private int id;
    private String iduser;
    
    
    //constructeurs 

    public Favoris() {
    }

    public Favoris(String iduser) {
        this.iduser = iduser;
    }
    
    
    // getters and setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
    
    
    
}
