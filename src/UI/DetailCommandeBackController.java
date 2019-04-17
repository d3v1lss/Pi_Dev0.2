/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import Services.CommandeService;
import Services.LigneCommandeService;
import static UI.CommandeBackController.c_details;
import static UI.CommandeBackController.x;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class DetailCommandeBackController implements Initializable {

    @FXML
    private TableColumn<LigneCommande, String> nom;
    @FXML
    private TableColumn<LigneCommande, Integer> qte;
    @FXML
    private Button fermer;
    @FXML
    private Label ref;
    @FXML
    private TableView<LigneCommande> table;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        if (c_details.isValider()) {
//            valider.setDisable(true);
//        }
        try {
            LigneCommandeService CS = new LigneCommandeService();
            List<LigneCommande> lc = CS.Display_CommandeBack(c_details.getIdCommande());
            ref.setText("" + c_details.getReference());
            table.setItems(FXCollections.observableArrayList(lc));
            nom.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
            qte.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        } catch (SQLException ex) {
            Logger.getLogger(DetailCommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = (Stage) fermer.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/CommandeBack.fxml"));
            Parent root;
            root = loader.load();
            x.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DetailCommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void valider(ActionEvent event) {
        try {
            CommandeService CS = new CommandeService();
            CS.updateEtat(c_details.getIdCommande());
        } catch (SQLException ex) {
            Logger.getLogger(DetailCommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
