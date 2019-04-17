/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.workshop;
import java.sql.Connection;
import java.sql.Date;
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
public class GestionWorkshop {

    public static Connection cnx;

    public GestionWorkshop() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    public void ajouter(workshop w) {

        String req = "INSERT INTO `workshop`(`nom`, `nombreplaces`, `datedebut`"
                + ", `datefin`, `discription`) "
                + " VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, w.getNom());
            preparedStatement.setInt(2, w.getNombreplaces());
            preparedStatement.setDate(3, w.getDatedebut());
            preparedStatement.setDate(4, w.getDatefin());
            preparedStatement.setString(5, w.getDiscription());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<workshop> FetchAll() throws SQLException {

        ObservableList<workshop> ListWorkshop = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM workshop";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id = resultat.getInt("id");
            int user = resultat.getInt("user_id");
            int club = resultat.getInt("club_id");
            String nom = resultat.getString("nom");
            int nombreplaces = resultat.getInt("nombreplaces");
            Date datedebut = resultat.getDate("datedebut");
            Date datefin = resultat.getDate("datefin");
            String discription = resultat.getString("discription");

            ListWorkshop.add(new workshop(user, club, nom, nombreplaces, datedebut, datefin, discription));

        }

        return ListWorkshop;

    }

    public ObservableList<workshop> MesWorkshop() throws SQLException {

        ObservableList<workshop> ListWorkshop = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM workshop where user_id=13 ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id = resultat.getInt("id");
            int user = resultat.getInt("user_id");
            int club = resultat.getInt("club_id");
            String nom = resultat.getString("nom");
            int nombreplaces = resultat.getInt("nombreplaces");
            Date datedebut = resultat.getDate("datedebut");
            Date datefin = resultat.getDate("datefin");
            String discription = resultat.getString("discription");

            ListWorkshop.add(new workshop(user, club, nom, nombreplaces, datedebut, datefin, discription));

        }

        return ListWorkshop;

    }

    public void upadte(workshop w) {
        String req = "update `workshop` SET `nom`=?, `nombreplaces`=?,`discription`=?,where id=? ";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, w.getNom());
            preparedStatement.setInt(2, w.getNombreplaces());
            preparedStatement.setString(3, w.getDiscription());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
}
