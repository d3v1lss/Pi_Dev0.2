/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class Commande_itemController_1 implements Initializable {

    @FXML
    private Label Nom_Commande;
    @FXML
    private Label Prix_Commande;
    @FXML
    private Label Etat_Commande;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXButton Imprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
