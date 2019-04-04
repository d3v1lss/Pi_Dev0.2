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
import entities.Salle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Houssem
 */
public class SalleServices {
    
    
    Connection connexion;

    public SalleServices() {
        connexion = DbConnexion.getInstance().getConnection();
    }
    
    public void ajouterSalle(Salle s) throws SQLException {
        String req = "INSERT INTO salle (nom, capacite)VALUES ( '"
                + s.getNom() + "', '" + s.getCapacite() + "') ";
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
