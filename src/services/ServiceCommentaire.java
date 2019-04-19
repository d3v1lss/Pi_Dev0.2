/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import entities.Forum;
import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static services.ServiceForum.connexion;
import utils.DbConnexion;

/**
 *
 * @author Vladio
 */
public class ServiceCommentaire {

    Connection connexion;
    int id_user = 1;

    public ServiceCommentaire() {

        connexion = DbConnexion.getInstance().getConnection();
    }

    public void AjouterCommentaire(Commentaire comm) {
        String req = "insert into commentaire (publication_id,contenu,user_id) values (?,?,?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connexion.prepareStatement(req);

        

            preparedStatement.setString(1,comm.getContenu());

           
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public List<Commentaire> getAllCommentaire() {
        ObservableList<Commentaire> myCommentaire = FXCollections.observableArrayList();
        String req = "select commentaire.id,commentaire.publication_id,commentaire.contenu,user.username from commentaire,user,publication where user.id=commentaire.user_id and publication.id=commentaire.publication_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
         

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Commentaire P;
                P = new Commentaire(resultSet.getInt("id"), resultSet.getInt("publication_id"), resultSet.getString("contenu"));
                myCommentaire.add(P);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myCommentaire;
    }
    
      public List<Commentaire> getAllCommentairePubs(int pub) {
        ObservableList<Commentaire> myCommentaire = FXCollections.observableArrayList();
        String req = "select commenetiare.id,commentaire.publication_id,publication.contenu,user.username from commenetaire,user where publciation_id=? and user.id=commentaire.user_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
          preparedStatement.setInt(1,pub);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Commentaire P = new Commentaire(resultSet.getInt("id"), resultSet.getInt("publication_id"), resultSet.getString("contenu"), resultSet.getInt("user_id"));
                //myCommentaire.add(P);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return myCommentaire;
    }

    public void modifierCommentaires(String contenu) {

        try {

            PreparedStatement pt = connexion.prepareStatement("UPDATE `commentaire` SET `contenu`=? WHERE user_id=?");

            pt.setString(1, contenu);

            pt.setInt(2, id_user);
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
        
         public void supprimerCommentaire(Commentaire e){
        try {
            PreparedStatement pt= connexion.prepareStatement("delete from 'commentaire' where 'id=?'");
            pt.setInt(1,e.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     */
    public void supprimerCommentaireSelected() {
        try {
            PreparedStatement pt = connexion.prepareStatement("delete from 'commentaire' where 'user_id=?'");
            pt.setInt(1, id_user);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String retourCommNompub(int idPub) {

        String commNom = "";
        try {

            String req = "select titre from publication where id=?";
            PreparedStatement preparedStatement;
            preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1, idPub);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                commNom = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commNom;
    }

}
