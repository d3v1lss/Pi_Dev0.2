/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import utils.DbConnexion;

/**
 *
 * @author Info-Sys
 */
public class ServiceEvenement {
    
    Connection connexion;
        private ObservableList<Evenement> items = FXCollections.observableArrayList();


    public ServiceEvenement() {
        connexion = DbConnexion.getInstance().getConnection();
    }
    /*  public void ajouterEvenement(Evenement e){
        try {
            Statement st=connexion.createStatement();//créer statement
            String req="insert into evenement values ("+e.getId()+",'"+e.getUser_id()+"','"+e.getTheme_id()+"','"+e.getNom()+"','"+e.getPhoto()+"','"+e.getDiscription()+"','"+e.getNombreplaces()+"','"+e.getDatedebut()+"','"+e.getDatefin()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      */
    
    public  int ajouterProduit(Evenement p) throws SQLException {
        int a = 0;
        String requete = "INSERT INTO `evenement`(`user_id`, `theme_id`, `nom`, `photo`, `discription` , `nombreplaces`, `datedebut`, `datefin`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement st = connexion.prepareStatement(requete);
        st.setInt(1, p.getUser_id());
        st.setInt(2, p.getTheme_id());
        st.setString(3, p.getNom());
        st.setString(4, p.getPhoto());
        st.setString(5, p.getDiscription());
        st.setInt(6, p.getNombreplaces());
        st.setDate(7, p.getDatedebut());
        st.setDate(8, p.getDatefin());
        st.executeUpdate();
        System.out.println("Article ajouté");
        a = 1;

        return a;
    }
          
    public void addEvent(Evenement d) {
        String req = "insert into evenement (id,user_id,theme_id,nom,photo,discription,nombreplaces,datedebut,datefin) values (?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1,d.getId());
         

         //   preparedStatement.setInt(1, d.getId());
            preparedStatement.setInt(2, d.getUser_id());

            preparedStatement.setInt(3, d.getTheme_id());

            preparedStatement.setString(4, d.getNom());
            preparedStatement.setString(5, d.getPhoto());
            preparedStatement.setString(6, d.getDiscription());
            preparedStatement.setInt(7, d.getNombreplaces());
            preparedStatement.setDate(8, d.getDatedebut());
            preparedStatement.setDate(9, d.getDatefin());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    /*
      public void addEven12tt(Evenement d) {
        String req = "insert into evenement (id,theme_id,nom,discription,nombreplaces,datedebut,datefin) values (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1,d.getId());
         

         //   preparedStatement.setInt(1, d.getId());
          preparedStatement.setInt(2, d.getUser_id());

            preparedStatement.setInt(2, d.getTheme_id());

            preparedStatement.setString(3, d.getNom());
         //   preparedStatement.setString(4, d.getPhoto());
            preparedStatement.setString(5, d.getDiscription());
            preparedStatement.setInt(6, d.getNombreplaces());
            preparedStatement.setDate(7, d.getDatedebut());
            preparedStatement.setDate(8, d.getDatefin());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }*/
     public int retourthemeId (String nomTheme)
       
     { int themeid=0;
             try {
                 
                 String req="select id from theme where nom=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setString(1, nomTheme);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  themeid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceTheme.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return themeid;
    }
      public String retourthemeNom (int idTheme)
       
     { String themeNom="";
             try {
                 
                 String req="select theme_id from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idTheme);
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
       public String retourthemeNomevent (int idTheme)
       
     {
    
         
         String themeNom="";
             try {
                 
                 String req="select nom from theme where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, idTheme);
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
    
      
   /*    public void ajouterService1(Evenement e){
        try {
            Statement st=connexion.createStatement();//créer statement
            String req="insert into evenement values ("+e.getUser_id()+"','"+e.getTheme_id()+"','"+e.getNom()+"','"+e.getPhoto()+"','"+e.getDiscription()+"','"+e.getNombreplaces()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      */
    /*
     public List<Evenement> getAllEvents() throws SQLException {

        List<Evenement> evenements = new ArrayList<>();
        String req = "select * from evenement";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Evenement p = new Evenement(rst.getInt("id")
                    , rst.getInt("user_id")
                     ,rst.getInt("theme_id")
                    ,rst.getString("nom")
                    ,rst.getString("photo")
                    ,rst.getString("discription")
                    ,rst.getInt("nombreplaces")
                    ,rst.getDate("datedebut")
                    ,rst.getDate("datefin"));
                    
                    
            evenements.add(p);
        }
        return evenements;
    }
       */
      public ObservableList<Evenement> afficherToutLesEvenements() throws SQLException, ParseException{

        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        Statement st = connexion.createStatement();
        String requete = "SELECT id ,theme_id , nom ,photo, discription , nombreplaces , datedebut,datefin FROM `evenement`";
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id");
            int theme_id= rs.getInt("theme_id");
            String nom = rs.getString("nom");
            String photo = rs.getString("photo");
            String discription = rs.getString("discription");
            int nombreplaces = rs.getInt("nombreplaces");
            Date datedebut =rs.getDate("datedebut");
            Date datefin =rs.getDate("datefin");

            
            myList.add(new Evenement(id, theme_id, nom, photo, discription, nombreplaces,datedebut,datefin));

        }
        return myList;
    }
       
       /*
        public  void modifierEvenement(int id, String nom, String discription, int nombreplaces, Date datedebut, Date datefin){
        
            try {
          
          PreparedStatement pt=connexion.prepareStatement("UPDATE `evenement` SET `nom`=?,`discription`=?,`nombreplaces`=?,`datedebut`=?,`datefin`=? WHERE id=?");
       //  pt.setInt(1, theme_id);
          pt.setString(1,nom);
           // pt.setString(3, photo);
            pt.setString(2 ,discription);
            pt.setInt(3, nombreplaces);
              pt.setDate(4, datedebut);
              pt.setDate(5, datefin);
              pt.setInt(6, id);
            pt.executeUpdate();
          
       
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
}*//*
          public void modifierEvenement(int id, String nom, String discription, int nombreplaces) {
        try {

           // int id_pa = SelectPanierUser();
            Statement stm = connexion.prepareStatement(nom);

            String req = "UPDATE `evenement` SET `nom`=? SET `discription`=? Set `nombreplaces`=? WHERE id=" + id + "' ";
            
            stm.executeUpdate(req);
            System.out.println("Modification done");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
      
            public void modifierbladateEvenement(int id,int theme_id,String nom , String photo, String discription, int nombreplaces) {
      

        String req = "UPDATE evenement SET theme_id=? , nom=? , photo=? ,discription=? , nombreplaces=? WHERE id= " + id + " ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setInt(1, theme_id);
             preparedStatement.setString(2,nom);
            preparedStatement.setString(3,photo);
            preparedStatement.setString(4,discription);
              preparedStatement.setInt(5, nombreplaces);
        
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            
        public void supprimerEvenement(int id){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from evenement where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        
        public void supprimerEv2(int id) {
        try {
         // int user=1;
     
           // int id_pa = SelectPanierUser();
          //  user;
            Statement stm = connexion.createStatement();

           String req = "DELETE FROM `evenement` WHERE `id`= '" + id + "' ";
             stm.executeUpdate(req);
            System.out.println("suppression done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText("votre evenement a été supprimé");
            alert.setContentText("evenement supprimé");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public  int countEvenement () {

            int total =0 ;
            Statement st;
       //  PreparedStatement pt =
        try {
            st= connexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS 'total' FROM `evenement`");
            while(rs.next())
                  {
                  total=rs.getInt(1);
                  }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

            return total ; 
        }

         
         public ObservableList<Evenement> triEvenementParDateDebut() throws SQLException, ParseException {

        PreparedStatement pt = connexion.prepareStatement("SELECT id ,theme_id , nom ,photo, discription , nombreplaces , datedebut,datefin FROM evenement ORDER BY datedebut");
        ResultSet resultat = pt.executeQuery();
        while (resultat.next()) {
            int id = resultat.getInt("id");
            int theme_id= resultat.getInt("theme_id");
            String nom = resultat.getString("nom");
            String photo = resultat.getString("photo");
            String discription = resultat.getString("discription");
            int nombreplaces = resultat.getInt("nombreplaces");
            Date datedebut =resultat.getDate("datedebut");
            Date datefin =resultat.getDate("datefin");

            Evenement p = new Evenement(id, theme_id, nom, photo, discription, nombreplaces,datedebut,datefin);
            items.add(p);
        }
        return items;
    }

   
         public ObservableList<Evenement> triEvenementByNom() throws SQLException, ParseException {
    
        PreparedStatement pt = connexion.prepareStatement("SELECT id ,theme_id , nom ,photo, discription , nombreplaces , datedebut,datefin FROM evenement ORDER BY nom ");
       // pt.setInt(1, id_user);
        ResultSet resultat = pt.executeQuery();
        while (resultat.next()) {
            int id = resultat.getInt("id");
            int theme_id= resultat.getInt("theme_id");
            String nom = resultat.getString("nom");
            String photo = resultat.getString("photo");
            String discription = resultat.getString("discription");
            int nombreplaces = resultat.getInt("nombreplaces");
            Date datedebut =resultat.getDate("datedebut");
            Date datefin =resultat.getDate("datefin");

            Evenement p = new Evenement(id, theme_id, nom, photo, discription, nombreplaces,datedebut,datefin);
            items.add(p);
        }
        
        return items;
    }
         
         public int retourEvId (String nomEv)
       
     { int Evid=0;
             try {
                 
                 String req="select id from evenement where nom=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setString(1, nomEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;
    }
         
           public  int countNombrePlace (String nomEv) {

           int Evid=0;
             try {
                 
            //st= connexion.createStatement();
            String req =("SELECT nombreplaces FROM `evenement`where nom=?");
             PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setString(1, nomEv);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  Evid=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return Evid;}
           
     
      public void décrémenterNBplaces(int id) {
        String req = "UPDATE evenement SET nombreplaces=nombreplaces-1 WHERE id=" + id + " ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
      //      preparedStatement.setInt(1, id);
         //   preparedStatement.setInt(2, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       public void incrémenterNBplaces(int id) {
        String req = "UPDATE evenement SET nombreplaces=nombreplaces+1 WHERE id=" + id + " ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);
      //      preparedStatement.setInt(1, id);
         //   preparedStatement.setInt(2, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public String retourNomEvent (int id)
       
     { String nom="";
             try {
                 
                 String req="select nom from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, id);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  nom=rs.getString(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return nom;
    }
          public int retourNombreDePlaceEvent (int id)
       
     {  int nb =0;
             try {
                 
                 String req="select nombreplaces from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, id);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  nb=rs.getInt(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return nb;
    }
            public String retourdsc (int id)
       
     { String nom="";
             try {
                 
                 String req="select discription from evenement where id=?";
                 PreparedStatement preparedStatement;
                 preparedStatement=connexion.prepareStatement(req);
               
                 preparedStatement.setInt(1, id);
                 ResultSet rs = preparedStatement.executeQuery();
                  while(rs.next())
                  {
                  nom=rs.getString(1);
                  }
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            return nom;
    }
            
           
    
    }

