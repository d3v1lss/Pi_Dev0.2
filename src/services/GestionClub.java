/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.club;
import entities.listeclub;
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
    private String req;
    private Statement stm;
    private ResultSet resultat;
    int lastid;

    public GestionClub() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    public int getLasT() throws SQLException {
        req = "SELECT max(id) FROM club";
        try {
            stm = cnx.createStatement();
            resultat = stm.executeQuery(req);
            while (resultat.next()) {
                lastid = resultat.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return lastid;
    }

    public void ajouter(club C) {
        req = "INSERT INTO `club`(`id`,`nom`, `mail`, `discription`"
                + ", `nbrparticipant`, `activite`,`president`, `statut`) "
                + " VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, C.getId());
            preparedStatement.setString(2, C.getNom());
            preparedStatement.setString(3, C.getMail());
            preparedStatement.setString(4, C.getDiscription());
            preparedStatement.setInt(5, C.getNbrparticipant());
            preparedStatement.setString(6, C.getActivite());
            preparedStatement.setInt(7, C.getPresident());
            preparedStatement.setString(8, C.getStatut());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inscrit(listeclub lclub) {
        req = "INSERT INTO `listeclub`(`club`, `membres`) "
                + " VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, lclub.getClub());
            preparedStatement.setString(2, lclub.getMembres());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<club> FetchAll() throws SQLException {

        ObservableList<club> ListClub = FXCollections.observableArrayList();
        stm = cnx.createStatement();
        req = "SELECT * FROM club";
        resultat = stm.executeQuery(req);
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
        stm = cnx.createStatement();
        req = "SELECT * FROM club where president=13 ";
        resultat = stm.executeQuery(req);
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

        req = "update `club` SET `nom`=?, `mail`=?,`discription`=?,`activite`=? "
                + "where president=13";

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

    public ObservableList<listeclub> Fetch() throws SQLException {

        ObservableList<listeclub> MesMembres = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        String re = "SELECT * FROM listeclub";
        ResultSet res = st.executeQuery(re);

        while (res.next()) {
            String nom = res.getString("membres");

            MesMembres.add(new listeclub(nom));

        }

        return MesMembres;

    }
}
