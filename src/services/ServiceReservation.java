/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.Reservation;
import entities.Theme;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.DbConnexion;

/**
 *
 * @author Info-Sys
 */
public class ServiceReservation {
      
    Connection connexion;

    public ServiceReservation() {
        connexion = DbConnexion.getInstance().getConnection();
    }
    
     public void ajouterReservation(Reservation e){
        try {
            Statement st=connexion.createStatement();//créer statement
            String req="insert into reservation values ("+e.getId()+",'"+e.getUser_id()+"','"+e.getEvenement_id()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
      public List<Reservation> getAllReservations() throws SQLException {

        List<Reservation> reservations = new ArrayList<>();
        String req = "select * from reservation";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reservation p = new Reservation(rst.getInt("id")
                    , rst.getInt("user_id"),
            rst.getInt("evenement_id"));
            reservations.add(p);
        }
        return reservations;
    }
      
      /*
     
       public void supprimerReservation(Reservation e){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from resrvation where id=?");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
*/
        
        public void supprimerReservation(int id){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from reservation where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         public void supprimerReservation2(int id) {
        try {
         // int user=1;
     
           // int id_pa = SelectPanierUser();
          //  user;
            Statement stm = connexion.createStatement();

           String req = "DELETE reservation FROM reservation, evenement WHERE reservation.user_id = evenement.user_id AND reservation.evenement_id = '" + id + "' ";
             stm.executeUpdate(req);
            System.out.println("reservation annuler");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText("votre reservation a été supprimé");
            alert.setContentText("reservation supprimé");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
             
         public int retourReservationId (int EvId)
       
     { int Evid=0;
             try {
                 
                 String req="Delete reservation.id FROM reservation, evenement WHERE reservation.user_id = evenement.user_id AND reservation.evenement_id = evenement.id";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, EvId);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;
    }*/
         
           public String retourEvnementNom (int idEv)
       
     {
    
         
         String themeNom="";
             try {
                 
                 String req="select nom from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeNom=rs.getString(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeNom;
    }
               public String retourEvnementDiscription (int idEv)
       
     {
    
         
         String themeNom="";
             try {
                 
                 String req="select discription from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeNom=rs.getString(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeNom;
    }
               
              public Date retourEvnementDateDebut (int idEv)
       
     {
    
         
         Date themeNom = null;
             try {
                 
                 String req="select datedebut from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeNom=rs.getDate(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeNom;
    }
          
        public ObservableList<Reservation> afficherTTreservation() throws SQLException, ParseException{

        ObservableList<Reservation> myList = FXCollections.observableArrayList();
        Statement st = connexion.createStatement();
        String requete = "SELECT id ,evenement_id  FROM reservation";
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id");
           // int user_id= rs.getInt("user_id");
            int evenement_id= rs.getInt("evenement_id");

            
            myList.add(new Reservation(id, evenement_id));

        }
        return myList;
    }
          public void supprimerR2(int id) {
        try {
         // int user=1;
     
           // int id_pa = SelectPanierUser();
          //  user;
            Statement stm = connexion.createStatement();

           String req = "DELETE FROM `reservation` WHERE `id`= '" + id + "' ";
             stm.executeUpdate(req);
            System.out.println("suppression done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" annuler Reservation ");
            alert.setHeaderText("Reservation annuler ! ");
            alert.setContentText("Reservation annuler");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
           
          public int retourReservationIdD (int nomEv)
       
     { int Evid=0;
             try {
                 
                 String req="select id from reservation where evenement_id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, nomEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;
    }
            public int retourEvnement_ID (int idEv)
       
     {
    
         
         int themeNom=0;
             try {
                 
                 String req="select evenement_id from reservation where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeNom=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeNom;
    }
            
            
        /*    public void verificationReservationExiste(int user_id) {
        String req = "select evenement_id from reservation where user_id=?" + user_id + " ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
      //      preparedStatement.setInt(1, id);
         //   preparedStatement.setInt(2, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
            }*/
            
                public int verificationReservationExiste (int user_id)
       
     { int Evid=0;
             try {
                 
                 String req="select evenement_id from reservation where user_id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, user_id);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;
    }
                
                 public  int countPaticipation (int user_id) {

            int total =0 ;
            Statement st;
       //  PreparedStatement pt =
        try {
            st= connexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS 'total' FROM `reservattion where user_id=?`");
            while(rs.next())
                  {
                  total=rs.getInt(1);
                  rs.getInt(2);
                  }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

            return total ; 
        }

}
