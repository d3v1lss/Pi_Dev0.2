/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Theme;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbConnexion;

/**
 *
 * @author Info-Sys
 */
public class ServiceTheme {
     Connection connexion;

    public ServiceTheme() {
        connexion = DbConnexion.getInstance().getConnection();
    }
      public void ajouterTheme(Theme e){
        try {
            Statement st=connexion.createStatement();//créer statement
            String req="insert into theme values ("+e.getId()+",'"+e.getNom()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
        public ArrayList<Theme> getAllThemes() throws SQLException {

        ArrayList<Theme> themes = new ArrayList<>();
        String req = "select * from theme";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Theme p = new Theme(rst.getInt("id")
                    , rst.getString("nom"));
            themes.add(p);
        }
        return themes;
    }
      
      
       
        
        public void modifierThemet(Theme t) {
        String req = "update theme set nom=? where id = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, t.getNom());
            preparedStatement.setInt(3, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
        public  void modifierThemeid(int id, String nom){
        
            try {
          
          PreparedStatement pt=connexion.prepareStatement("UPDATE `theme` SET `nom`=? WHERE id=?");
        
          pt.setString(1,nom);
           
              pt.setInt(2, id);
            pt.executeUpdate();
          
       
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
            
}
        
        
        public void supprimerTheme(Theme e){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from theme where id=?");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void supprimerThemeselected(int id){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from theme where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        

        public ArrayList<String>  retourNomTheme () 
     { 
          ArrayList<String> l = new ArrayList<>();
             try {
                 
                 String req="select nom from theme ";
                 PreparedStatement pt;
                 pt=connexion.prepareStatement(req);
               
                
                 ResultSet rs = pt.executeQuery();
                  while(rs.next())
                  {
                 l.add(rs.getString(1));
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return l;
    }




        
}
