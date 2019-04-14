/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dorra
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   

    @FXML
    private void Boutique(ActionEvent event) {
        try {
            Boutique.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/ProduitsclientsFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   
}
