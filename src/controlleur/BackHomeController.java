/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class BackHomeController implements Initializable {

    @FXML
    private AnchorPane table;
    @FXML
    private Button Forums;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Clubs(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherBackClubs.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Forum(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjoutForum.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void accsalle(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueilCinema.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void evenement(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ajouterTheme.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
