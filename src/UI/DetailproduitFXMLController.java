/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
 * @author oumayma haouet
 */
public class DetailproduitFXMLController implements Initializable {
    
    private Produit P;
    @FXML
    private HBox Show_Product;
    @FXML
    private ImageView Image_Produit;
    @FXML
    private Label Nom_Produit;
  
    @FXML
    private JFXButton Close_BTN;
    @FXML
    private Label Description;
    @FXML
    private Label Quantite_Produit11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    
       public void SetProduit(Produit P1) {
        P = P1;
    }

    public Produit GetProduit() {
        return P;
    }

    @FXML
    private void Close(ActionEvent event) {
          Stage stage = (Stage) Close_BTN.getScene().getWindow();
        stage.close();
    }
}
