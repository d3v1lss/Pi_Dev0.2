/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox<?> tva;
    @FXML
    private CheckBox disponibilité;
    @FXML
    private TextField photo;
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;

    private Produit produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherProduitsController pc = new AfficherProduitsController();
        
        produit = pc.getpEdit();
        setProduit(produit);
    }

    public void setProduit(Produit produit) {
        nom.setText(produit.getNom());
        prix.setText(Double.toString(produit.getPrix()));
        description.setText(produit.getDescription());
        photo.setText(produit.getPhoto());
        disponibilité.setSelected(produit.isDisponible());

    }

    @FXML
    private void Modifer() throws IOException {

        produit.setNom(nom.getText());
        produit.setPrix(Double.parseDouble(prix.getText()));
        produit.setDescription(description.getText());
        produit.setPhoto(photo.getText());
        produit.setDisponible(disponibilité.isSelected());
        ProduitService ps= new ProduitService();
        ps.update(produit);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherProduits.fxml"));
        Parent root;
        root = loader.load();
        modifier.getScene().setRoot(root);
        new Alert(Alert.AlertType.INFORMATION, "produit modifier").show();

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void Annuler() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherProduits.fxml"));
        Parent root;
        root = loader.load();
        annuler.getScene().setRoot(root);
    }
    

}
