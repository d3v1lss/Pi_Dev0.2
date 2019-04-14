/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class Single_ProduitController implements Initializable {

    @FXML
    private JFXButton Commander_BTN;
    @FXML
    private JFXButton Voirplus;
    @FXML
    private ImageView Product_Pic;
    @FXML
    private Label Nom_Produit;
    @FXML
    private Label Prix_Produit;
    @FXML
    private Label Quantite_Produit;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Commander(ActionEvent event) {
        
    }
    
}
