/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.club;
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
public class GestionClub {

    public static Connection cnx;
    private Object connexion;

    public GestionClub() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    public void ajouter(club C) {
        String req = "INSERT INTO `club`(`nom`, `mail`, `discription`"
                + ", `nbrparticipant`, `activite`, `statut`) "
                + " VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, C.getNom());
            preparedStatement.setString(2, C.getMail());
            preparedStatement.setString(3, C.getDiscription());
            preparedStatement.setInt(4, C.getNbrparticipant());
            preparedStatement.setString(5, C.getActivite());
            preparedStatement.setString(6, C.getStatut());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<club> FetchAll() throws SQLException {

        ObservableList<club> ListClub = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM club";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id = resultat.getInt("id");
            String nom = resultat.getString("nom");
            String mail = resultat.getString("mail");
            String discription = resultat.getString("discription");
            int nbrparticipant = resultat.getInt("nbrparticipant");
            String activite = resultat.getString("activite");
            int President = resultat.getInt("president");
            String Statut = resultat.getString("statut");

            ListClub.add(new club(nom, mail, discription, nbrparticipant, activite, President, Statut));

        }

        return ListClub;

    }

    public ObservableList<club> MonClub() throws SQLException {

        ObservableList<club> MonClub = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM club where president=13 ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id = resultat.getInt("id");
            String nom = resultat.getString("nom");
            String mail = resultat.getString("mail");
            String discription = resultat.getString("discription");
            int nbrparticipant = resultat.getInt("nbrparticipant");
            String activite = resultat.getString("activite");
            int President = resultat.getInt("president");
            String Statut = resultat.getString("statut");

            MonClub.add(new club(nom, mail, discription, nbrparticipant, activite, President, Statut));

        }

        return MonClub;

    }

    public void update(club C) {

        String req = "update `club` SET `nom`=?, `mail`=?,`discription`=?,`activite`=? "
                + "where president=7";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, C.getNom());
            preparedStatement.setString(2, C.getMail());
            preparedStatement.setString(3, C.getDiscription());
            preparedStatement.setString(4, C.getActivite());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
