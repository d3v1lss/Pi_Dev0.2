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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
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
    }

    public void ajouterFilm(Film s) throws SQLException {
        String req = "INSERT INTO film (nom, discription , duree , datesotie,image)VALUES ( '"
                + s.getNom() + "', '" + s.getDiscription() + "' , '" + s.getDuree() + "' , '" + s.getDatesotie() + "','" + s.getImage() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

}
