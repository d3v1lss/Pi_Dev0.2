/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Interfaces.IProduit;
import java.sql.Connection;
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
 * @author Dorra
 */
public class ProduitService implements IProduit {

    Connection cnx = null;

    public ProduitService() {
        cnx = DbConnexion.getInstance().getConnection();
    }

    @Override
    public List<Produit> AfficherProduits() {
        List<Produit> Produits = new ArrayList();
        String req = "select * from produit";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(req);

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String description = resultat.getString("description");
                double prix = resultat.getDouble("prix");
                String photo = resultat.getString("photo");
                Boolean disponibilité= resultat.getBoolean("disponible");
               
                Produit p = new Produit(id, nom, prix, description, photo, disponibilité);
                System.out.println(p.toString());
                Produits.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Produits;
    }

    public void insert(Produit p) {
        String req = "INSERT INTO `produit`(`nom`, `prix`,`description`,`photo`,`disponible`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, p.getNom());
            preparedStatement.setDouble(2, p.getPrix());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setString(4, p.getPhoto());
            preparedStatement.setBoolean(5, p.isDisponible());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove(int id) {
        String req = "Delete from produit where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void update(Produit p) {
        String req = "update `produit` SET `nom`=?, `prix`=?,`description`=?,`photo`=?,`disponible`=? where id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, p.getNom());
            preparedStatement.setDouble(2, p.getPrix());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setString(4, p.getPhoto());
            preparedStatement.setBoolean(5, p.isDisponible());
            preparedStatement.setInt(6, p.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   



}
