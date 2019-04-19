/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class Single_PanierController implements Initializable {

    @FXML
    private ImageView Product_Pic;
    @FXML
    private Label Nom_Produit;
    @FXML
    private JFXButton Supprimer;
    @FXML
    private ChoiceBox<Integer> qte;
    @FXML
    private Label sous_total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product_Pic.setTranslateY(10);

    }

}
