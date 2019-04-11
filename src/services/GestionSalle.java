/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.club;
import entities.salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static services.GestionClub.cnx;
import utils.DbConnexion;

/**
 *
 * @author HCHAICHI
 */
public class GestionSalle {

    public static Connection cnx;

    public GestionSalle() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    public void ajouter(salle s) {
        String req = "INSERT INTO `salle`(`nom`, `capacite` ) "
                + " VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, s.getNom());
            preparedStatement.setInt(2, s.getCapacite());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionSalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<salle> FetchAll() throws SQLException {

        ObservableList<salle> ListSalle = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM salle";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id = resultat.getInt("id");
            String nom = resultat.getString("nom");
            int capacite = resultat.getInt("capacite");

            ListSalle.add(new salle(nom, capacite));

        }

        return ListSalle;

    }
}
