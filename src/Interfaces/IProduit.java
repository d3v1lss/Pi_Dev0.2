/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Produit;
import java.util.List;

/**
 *
 * @author Dorra
 */
public interface IProduit {
    
   public List<Produit> AfficherProduits();
     public void insert(Produit p);
  // public List<Produit> selectAll();
    
}
