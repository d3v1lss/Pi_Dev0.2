/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnexion;

/**
 *
 * @author Vladio 
 */
public class ServiceForum {
    
    static Connection connexion;
    private ObservableList<Forum> list = FXCollections.observableArrayList();
    
    public ServiceForum(){
        
        connexion = DbConnexion.getInstance().getConnection();
          
}
    
    
    public ObservableList<Forum> ListForum() throws SQLException{
        ObservableList<Forum> myForum = FXCollections.observableArrayList();
        Statement st = connexion.createStatement();
        String req = "SELECT * from forum";
        ResultSet rs = st.executeQuery(req);
        while(rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String discription =rs.getString("discription");
            myForum.add(new Forum(id,nom,discription));
               
        }
        return myForum;
        }
  
      public ObservableList<String> ListForumNom() throws SQLException{
        ObservableList<String> myForum = FXCollections.observableArrayList();
        Statement st = connexion.createStatement();
        String req = "SELECT nom from forum";
        ResultSet rs = st.executeQuery(req);
        while(rs.next()) {
            
            String nom = rs.getString("nom");
          
            myForum.add(nom);
               
        }
        return myForum;
        }
    
    
    
    
     public void AjouterForums(Forum forums)throws SQLException 
    {
        String req ="INSERT INTO forum(nom,discription) VALUES (?,?)";
  

        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setString(1, forums.getNom());
        pre.setString(2, forums.getDiscription());
 
       
        pre.executeUpdate();   
        
     
        }
    
      
      // public List<Forum> getAllForum() throws SQLException {

        //List<Forum> forums = new ArrayList<>();
        //String req = "select * from forum";
        //Statement stm = connexion.createStatement();
        //ResultSet rst = stm.executeQuery(req);

        //while (rst.next()) {
          //  Forum p = new Forum(rst.getInt("id")
            //        , rst.getString("nom")
              //      , rst.getString("discription"));
            //forums.add(p);
        //}
        //return forums;
    //}
      
   
       
        public  void modifierforum(int id, String nom, String discription){
        
            try {
          
          PreparedStatement pt=connexion.prepareStatement("UPDATE `forum` SET `nom`=?,`discription`=? WHERE id=?");
        
          pt.setString(1,nom);
            pt.setString(2, discription);
            
              pt.setInt(3, id);
            pt.executeUpdate();
          
       
        } catch (SQLException ex) {
            Logger.getLogger(ServiceForum.class.getName()).log(Level.SEVERE, null, ex);
        }
            
}
        /*
        
         public void supprimerforum(Forum e){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from forum where id=?");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceForum.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        
        
*/
  public void delete(int id) throws SQLException{
        
        String req="delete from forum where id= "+id;
        connexion.createStatement().executeUpdate(req);
        ServicePublication p = new ServicePublication();
        p.supprimerPublicationFromForum(id);
      //  ServiceCommentaire c = new ServiceCommentaire();
        
        
  }
      

  public List<Forum> getAllForums() {
        list =FXCollections.observableArrayList();
        String req = "select * from forum";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Forum S = new Forum(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("discription"));
               list.add(S);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

     }
   public void UpdateForum(Forum S) throws SQLException 
    {
        String req ="UPDATE forum SET nom = ?, discription = ? WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req); 
        pre.setString(1, S.getNom());
        pre.setString(2, S.getDiscription());
        pre.setInt(3, S.getId());
        pre.executeUpdate();   
        
       
    }
public int retourforumId (String nomEv)
       
     { int Evid=0;
             try {
                 
                 String req="select id from forum where nom=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setString(1, nomEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceForum.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;
    }
   
}