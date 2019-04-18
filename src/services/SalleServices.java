/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controlleur.ModifierSalleController;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DbConnexion;
import entities.Salle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Houssem
 */
public class SalleServices {
    
    
    Connection connexion;
    static Connection cnx;
    static Connection cnx1;

    public SalleServices() {
        connexion = DbConnexion.getInstance().getConnection();
        cnx1 = DbConnexion.getInstance().getConnection();
    }
    
    public void ajouterSalle(Salle s) throws SQLException {
        String req = "INSERT INTO salle (nom, capacite)VALUES ( '"
                + s.getNom() + "', '" + s.getCapacite() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    
    
       
    public void modifierSalle(Salle s) throws SQLException
    {
        Statement stm = cnx.createStatement();
        String req = "UPDATE salle SET `nom`='"+s.getNom()+"',`capacite`='"+s.getCapacite()+"'= WHERE `id`= '"+s.getId()+"' ";
        stm.executeUpdate(req); 
        
    }
    
    
     
     
     
     
     
     public void    update(Salle s )  {
        try {

   String req = "UPDATE salle SET nom=?, capacite=? "; 
   PreparedStatement stm     =   cnx.prepareStatement(req);
     stm.setString(1, s.getNom());
    stm.setInt(2, s.getCapacite());
    stm.executeUpdate(); 
} 
         catch (SQLException ex) 
         {
             Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
         }    
    
}
    
     
    public List<Salle> selectAll() {
        List<Salle> salle=new ArrayList<Salle>();
        String req="select * from salle";
        try {
            Statement statement=connexion.createStatement();
            ResultSet resultat =statement.executeQuery(req);
            while(resultat.next()){
                String nom = resultat.getString("nom");
            int capacite=resultat.getInt("capacite");
                Salle s=new Salle( capacite , nom);
                System.out.println(s.toString());
                salle.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salle;
    }
    
    public ObservableList<Salle> FetchAll() throws SQLException {

        ObservableList<Salle> ListSalle = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM salle";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int capacite = resultat.getInt("capacite");
            String nom = resultat.getString("nom");
            

            ListSalle.add(new Salle(capacite, nom));

        }

        return ListSalle;

    }
    
    
     public ObservableList<Salle> AddAll() throws SQLException {

        ObservableList<Salle> ListSalle = FXCollections.observableArrayList();
        Statement stm= cnx1.createStatement();
        String req = "SELECT * FROM salle";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            System.out.println(resultat.getInt("id"));
            int id = resultat.getInt("id");
            String nom = resultat.getString("nom");
            int capacite = resultat.getInt("capacite");

            ListSalle.add(new Salle(id,capacite, nom));

        }

        return ListSalle;

    }
    
    
     
    
}
