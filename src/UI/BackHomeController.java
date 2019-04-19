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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class BackHomeController implements Initializable {

    @FXML
    private Button Boutique;
    @FXML
    private Button Quitter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Boutique(ActionEvent event) {
         try {
            Boutique.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/AfficherProduits.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Quitter(ActionEvent event) {  try {
            Boutique.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/firstXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
