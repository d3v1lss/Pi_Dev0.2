/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import entities.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 *
 */
public class HomeController implements Initializable {

    @FXML
    private Button Evenement;
    @FXML
    private Button Cinema;
    @FXML
    private Button Boutique;
    @FXML
    private Button Clubs;
    @FXML
    private Button Forum;
    @FXML
    private Button Bibliotheque;
    @FXML
    private Button Quitter;
    @FXML
    private ListView<club> list;
    UserSession u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Clubs(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherClubs.fxml"));
        Parent root;

        try {
            root = loader.load();

            list.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Cinema(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueilCinema.fxml"));
        Parent root;

        try {
            root = loader.load();

            list.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

};
