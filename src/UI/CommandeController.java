/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Commande;
import Entities.User;
import Services.CommandeService;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class CommandeController implements Initializable {

    @FXML
    private ScrollPane body_pane;
    @FXML
    private VBox content;
    @FXML
    private JFXButton Close_BTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            CommandeService CS = new CommandeService();
            List<Commande> data = CS.Display_User_Commande(User.getInstance().getId());
            int i = 0;
            content.getChildren().clear();
            Node[] nodes = new Node[data.size()];
            for (Commande item : data) {
                
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Commande_item.fxml"));
                
                try {
                    nodes[i] = (Node) fxmlLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Label Nom_Commande = (Label) nodes[i].lookup("#Nom_Commande");
                
                Label Prix_Commande = (Label) nodes[i].lookup("#Prix_Commande");
                Label Etat_Commande = (Label) nodes[i].lookup("#Etat_Commande");
                JFXButton Annuler = (JFXButton) nodes[i].lookup("#Annuler");
                JFXButton Imprimer = (JFXButton) nodes[i].lookup("#Imprimer");
                Prix_Commande.setText(Double.toString(item.getTotal()));
                
                if (item.isValider()) {
                    Etat_Commande.setText("En cours");
                    Imprimer.setVisible(false);
                    Annuler.setVisible(true);
                    Annuler.setOnMouseClicked(e1 -> {
                        CS.Delete(item);
                        Stage stage = (Stage) Close_BTN.getScene().getWindow();
                        stage.close();
                        
                    });
                } else {
                    Etat_Commande.setText("Validé");
                    Annuler.setVisible(false);
                    Imprimer.setVisible(true);
                    String QRText = item.getReference()+" "+ item.getIdCommande() +" "+ item.getTotal();
                    
                };
                
//                    Stage stage = (Stage) Close_BTN.getScene().getWindow();
// stage.close();

            }
            //  Nom_Commande.setText(item.getNom());
            
            content.getChildren().add(nodes[i]);
            
            i++;
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            } 

        

    

    
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Close_BTN.getScene().getWindow();
        stage.close();
    }

}
