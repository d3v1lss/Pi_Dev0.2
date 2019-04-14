/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Services.CommandeService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class CommandeBackController implements Initializable {

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<User, String> nomClient;
    @FXML
    private TableColumn<Commande,Boolean> etat;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        try {
            CommandeService CS = new CommandeService();
            List<Commande> Commandes = CS.AfficherCommandes();
            table.setItems(FXCollections.observableArrayList(Commandes));
            nomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void retour(ActionEvent event) {
        try {
            Retour.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/AfficherProduits.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
