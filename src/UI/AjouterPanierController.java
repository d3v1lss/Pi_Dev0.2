/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.LigneCommande;
import Entities.Panier_Session;
import Entities.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class AjouterPanierController implements Initializable {

    @FXML
    private JFXTextField Qte_Commander;
    @FXML
    private JFXButton Close_BTN;

    private Produit P;
    @FXML
    private ImageView Image_Produit;
    @FXML
    private Label Nom_Produit;
    @FXML
    private Label Quantite_Produit;
    @FXML
    private Label Quantite_Produit1;
    @FXML
    private JFXButton AjoutPanier_BTN;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Close_BTN.getScene().getWindow();
        stage.close();

    }

    public void SetProduit(Produit P1) {
        P = P1;
        System.out.println("produit set !");
    }

    public Produit GetProduit() {
        return P;
    }

    @FXML
    private void Commander(ActionEvent event) {

        LigneCommande LC = new LigneCommande();
        List<LigneCommande> Panier_List = Panier_Session.Produit_Panier;
        if (!Panier_Session.Produit_Panier.isEmpty()) {
            boolean verif = false;
            for (LigneCommande item : Panier_List) {

                if (item.getIdProduit() == P.getId()) {

                    item.setQuantite(item.getQuantite() + Integer.parseInt(Qte_Commander.getText()));
                    verif = true;
                    break;
                }
            }
            if (!verif) {
                LC.setIdProduit(P.getId());
                LC.setQuantite(Integer.parseInt(Qte_Commander.getText()));
                Panier_Session.Produit_Panier.add(LC);
            }

        } else {
            LC.setIdProduit(P.getId());
            LC.setQuantite(Integer.parseInt(Qte_Commander.getText()));
            Panier_Session.Produit_Panier.add(LC);
        }

        Stage stage = (Stage) Close_BTN.getScene().getWindow();
        stage.close();
    }

}
