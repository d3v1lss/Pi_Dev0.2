/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class HomeController implements Initializable {

    @FXML
    private ImageView Boutique;
    @FXML
    private AnchorPane dashPane;
    @FXML
    private AnchorPane bgColor;
    @FXML
    private JFXButton Boutique1;
    @FXML
    private Label welcomeLab;
    @FXML
    private Label deconnect;
    @FXML
    private ImageView profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   

 

    @FXML
    private void ShopAction(ActionEvent event) {
        try {
            Boutique.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/ProduitsclientsFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void FilmAction(MouseEvent event) {
    }

    @FXML
    private void eventAction(MouseEvent event) {
    }

    @FXML
    private void ClubAction(MouseEvent event) {
    }

    @FXML
    private void ForumAction(MouseEvent event) {
    }

    @FXML
    private void profileAction(MouseEvent event) {
    }

   @FXML
    private void DeconnectAction(ActionEvent event) {
         try {
            Boutique1.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/firstXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void profileAction(ActionEvent event) {
        try {
            Boutique1.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/profilXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @FXML
    private void BiblioAction(MouseEvent event) {
    }
     
   
}
