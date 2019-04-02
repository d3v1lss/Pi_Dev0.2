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
public class Avis {
    

// les attributs
    private int id;
    private String avischoix;


// les constructeurs 

    public Avis() {
    }

    public Avis(String avischoix) {
        this.avischoix = avischoix;
    }
    
    
// getter and setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvischoix() {
        return avischoix;
    }

    public void setAvischoix(String avischoix) {
        this.avischoix = avischoix;
    }
    
    
    
    
}
