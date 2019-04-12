/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void ajouter(Salle s) {
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

    public ObservableList<Salle> FetchAll() throws SQLException {

        ObservableList<Salle> ListSalle = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM salle";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            System.out.println(resultat.getInt("id"));
            int id = resultat.getInt("id");
            String nom = resultat.getString("nom");
            int capacite = resultat.getInt("capacite");

            ListSalle.add(new Salle(id,capacite, nom));

        }

        return ListSalle;

    }
}
