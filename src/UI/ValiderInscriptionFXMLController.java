/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.User;
import Services.UserService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ValiderInscriptionFXMLController implements Initializable {

    @FXML
    private JFXTextField codeTF;
    @FXML
    private JFXButton confirmeBTN;
    @FXML
    private JFXButton annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void confirmAction(ActionEvent event) throws SQLException {
        
        
            UserService us = new UserService();
            
            if(us.verifierToken(User.getInstance()));
            
            Parent root;
            try {
                       
                root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                
                confirmeBTN.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ValiderInscriptionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void annuler(ActionEvent event) {
    }
    
}
