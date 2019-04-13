/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DbConnexion;
import entities.Film;
import entities.Salle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import static services.GestionSalle.cnx;
import static services.SalleServices.cnx;

/**
 *
 * @author Houssem
 */
public class FilmServices {

    public Connection cnx;
    Connection connexion;

    public FilmServices() {
        connexion = DbConnexion.getInstance().getConnection();
        cnx = DbConnexion.getInstance().getConnection();
    }

    public void ajouterFilm(Film s) throws SQLException {
        String req = "INSERT INTO film (nom, discription , duree , datesotie,image)VALUES ( '"
                + s.getNom() + "', '" + s.getDiscription() + "' , '" + s.getDuree() + "' , '" + s.getDatesotie() + "','" + s.getImage() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    
    public ObservableList<Film> FetchAll() throws SQLException {

        ObservableList<Film> ListFilm = FXCollections.observableArrayList();
        System.out.println("observable liste");
        Statement stm = cnx.createStatement();
        System.out.println(" cnx BD");
        String req = "SELECT * FROM film";
        System.out.println("requette");
        ResultSet resultat = stm.executeQuery(req);
        System.out.println("resultat");
        while (resultat.next()) {
            
            String image = resultat.getString("image");
            System.out.println("nom");
            int id = resultat.getInt("id");
            System.out.println("id");
            String duree = resultat.getString("duree");
            System.out.println("duree");
            String nom = resultat.getString("nom");
            Date datesotie = resultat.getDate("datesotie");
            System.out.println("date");
            String discription = resultat.getString("discription");
            System.out.println("description");
            

            ListFilm.add(new Film(nom, discription, duree, datesotie, image));

        }

        return ListFilm;
    }
    
    public void modifierSalle(Salle s) throws SQLException
    {
        Statement stm = cnx.createStatement();
        String req = "UPDATE `produit` SET `nom`='"+s.getNom()+"',`capacite`='"+s.getCapacite()+"' ";
        stm.executeUpdate(req); 
        
    }

}
