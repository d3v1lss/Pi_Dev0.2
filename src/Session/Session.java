/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Produit;
import Entities.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dorra
 */
public class Session {
    private static Session instance;
    private User user;
    private Map<Produit,Integer> panier ;
    
    private Session(){
     panier = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void AddProduit(Produit P, int qte){
        if(panier.putIfAbsent(P, qte) != null){
            panier.put(P,panier.get(P)+qte);        
        }
        System.out.println("list Panier:"+panier);
    }
    public Map<Produit,Integer> getPanier(){
        return this.panier;
    }
    public static Session getInstance(){
       if(instance == null){
           instance = new Session();
       } 
       return instance ;
    }
}
