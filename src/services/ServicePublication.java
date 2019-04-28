/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Forum;
import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnexion;

/**
 *
 * @author Vladio
 */
public class ServicePublication {
    Connection connexion;
     private ObservableList<Publication> list;
      int id_user = 1;
    
        public ServicePublication(){
        
        connexion = DbConnexion.getInstance().getConnection();       
}
  
        public void AjouterPublication(Publication d) {
        String req = "insert into publication (forum_id,user_id,contenu,titre) values (?,?,?,?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setInt(1, d.getForum_id());

            preparedStatement.setInt(2,id_user);

            preparedStatement.setString(3, d.getContenu());
            preparedStatement.setString(4, d.getTitre());
            
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

  





    public List<Publication> getAllpublications() {
    ObservableList<Publication> myPublication = FXCollections.observableArrayList();
        String req = "select publication.id,publication.contenu,publication.titre,user.username,forum.nom from publication,user,forum where user.id=publication.user_id and forum.id=publication.forum_id";
        PreparedStatement preparedStatement;
        try {preparedStatement = connexion.prepareStatement(req);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publication P = new Publication(resultSet.getInt("id"),resultSet.getString("contenu"),resultSet.getString("titre"),resultSet.getString("username"),resultSet.getString("nom"));
               myPublication.add(P);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myPublication;
    }

    public List<Publication> getAllpublicationsForum(int forum) {
    ObservableList<Publication> myPublication = FXCollections.observableArrayList();
        String req = "select publication.id,publication.contenu,publication.titre,user.username from publication,user where forum_id=? and user.id=publication.user_id";
        PreparedStatement preparedStatement;
        try {preparedStatement = connexion.prepareStatement(req);
  preparedStatement.setInt(1,forum);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publication P = new Publication(resultSet.getInt("id"),resultSet.getInt("forum_id"),resultSet.getString("contenu"),resultSet.getString("titre"),resultSet.getString("nom"));
               myPublication.add(P);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myPublication;
    }
    
    public List<Publication> getAllpublicationsUser() {
    ObservableList<Publication> myPublication = FXCollections.observableArrayList();
        String req = "select publication.id,publication.contenu,publication.titre,user.username,forum.nom from publication,user,forum where user.id=publication.user_id and forum.id=publication.forum_id and user_id=?";
        PreparedStatement preparedStatement;
        try {preparedStatement = connexion.prepareStatement(req);
  preparedStatement.setInt(1,id_user);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publication P = new Publication(resultSet.getInt("id"),resultSet.getString("contenu"),resultSet.getString("titre"),resultSet.getString("username"),resultSet.getString("nom"));
               myPublication.add(P);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myPublication;
    }
       
       
        public  void modifierPublication(String contenu, String titre){
        
            try {
          
          PreparedStatement pt=connexion.prepareStatement("UPDATE `publication` SET `contenu`=?,`titre`=? WHERE user_id=?");
        
          pt.setString(1,contenu);
            pt.setString(2, titre);
            
              pt.setInt(3,id_user);
            pt.executeUpdate();
          
       
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
            
}
        /*
         public void supprimerPublication(Publication e){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from publication where id=?");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         */
           public void supprimerPublicationsSelected(int id){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from publication where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
             public void supprimerPublicationFromForum(int id){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from publication where forum_id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  public String retourforumNompub (int idForum)
       
     {
    
         
         String themeNom="";
             try {
                 
                 String req="select nom from forum where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idForum);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeNom=rs.getString(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeNom;
    }           
     
       public void Like(Publication s){
                String req="select * from lke where id_user= ? AND id_pub= ?";
                
         try {
             PreparedStatement p = connexion.prepareStatement(req);
                  p.setInt(1,id_user);
                  p.setInt(2,s.getId());
             ResultSet rs=p.executeQuery();
             if(rs.next()){
                  String req1="delete from lke where id_user= ? AND id_pub= ?"; 
                  PreparedStatement prep = connexion.prepareStatement(req1);
                  prep.setInt(1,id_user);
                  prep.setInt(2,s.getId());
                  prep.executeUpdate();
             
             }else{
                    String req2 ="INSERT INTO lke(id_user,id_pub) VALUES (?,?)";
                    String req3="delete from dislke where id_user= ? AND id_pub= ?";

                    PreparedStatement pre = connexion.prepareStatement(req2);
                    PreparedStatement prepa = connexion.prepareStatement(req3);
                 
                    pre.setInt(1, id_user);
                    pre.setInt(2, s.getId());
                    pre.executeUpdate();   
                 
                    prepa.setInt(1,id_user);
                    prepa.setInt(2,s.getId());
                    prepa.executeUpdate();
              
             
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
         }
            }  
    
              
              
                       public boolean dislikeIsThere(Publication s,int userID){
         try {
             String req="select * from dislke where id_user= ? AND id_pub= ?";
             
             PreparedStatement p = connexion.prepareStatement(req);
             p.setInt(1,userID);
             p.setInt(2,s.getId());
             ResultSet rs=p.executeQuery();
             if (rs.next()){
             return true;
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
         }  
            return false;
                }
                       
                       public int retourforumIdPourAffichage (int id)
       
     {  int nb =0;
             try {
                 
                 String req="select forum_id from publication where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, id);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  nb=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return nb;
    }
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
