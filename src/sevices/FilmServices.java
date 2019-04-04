/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevices;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DbConnexion;
import entities.Film;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Houssem
 */
public class FilmServices {
    
    
    Connection connexion;

    public FilmServices() {
        connexion = DbConnexion.getInstance().getConnection();
    }
    
    public void ajouterFilm(Film s) throws SQLException {
        String req = "INSERT INTO film (nom, discription , duree , datesotie)VALUES ( '"
                + s.getNom() + "', '" + s.getDiscription() + "' , '" + s.getDuree() + "' , '" + s.getDatesotie() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    /*
    public void ajouterProduitPs(Salle s) throws SQLException {
        String req = "INSERT INTO `Product` (`nom`, 'capacite') "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1,  s.getNom());
        ps.setInt(2, s.getCapacite());
        ps.executeUpdate();
    }
    */
    
}
