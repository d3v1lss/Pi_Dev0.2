/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Panier_Session;
import Entities.Produit;
import Entities.User;
import Services.CommandeService;
import Services.LigneCommandeService;
import Services.ProduitService;
import static UI.ProduitsclientsFXMLController.ServerImg;
//import Utils.Routes;
//import UI.Produit.ListProduitController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class PanierController implements Initializable {

    @FXML
    private ScrollPane body_pane;
    @FXML
    private JFXButton Close_BTN;
    @FXML
    private JFXButton Commander_BTN;
    @FXML
    private VBox content;
    @FXML
    private Label prixT;

    private Double x = 0d;
    private Double y = 0d;
    public static Double totalPanier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<LigneCommande> LC_Panier = Panier_Session.Produit_Panier;

        int j = 0;
        body_pane.setContent(content);
        content.getChildren().clear();
        Node[] nodes = new Node[LC_Panier.size()];
        for (LigneCommande item : LC_Panier) {
            try {
                nodes[j] = (Node) FXMLLoader.load(getClass().getResource("Single_Panier.fxml"));
                ImageView Product_Pic = (ImageView) nodes[j].lookup("#Product_Pic");
                Label Nom_Produit = (Label) nodes[j].lookup("#Nom_Produit");
                ChoiceBox Quantite_Produit = (ChoiceBox) nodes[j].lookup("#qte");
                Label Sous_total = (Label) nodes[j].lookup("#sous_total");
                JFXButton Supprimer = (JFXButton) nodes[j].lookup("#Supprimer");

                Supprimer.setOnMouseClicked(e3 -> {
                    LC_Panier.remove(item);
                    Stage stage = (Stage) Close_BTN.getScene().getWindow();
                    stage.close();

                });

                ProduitService PS = new ProduitService();
                Produit Prod_panier = PS.Get_Produit(item.getIdProduit());
                y = Prod_panier.getPrix() * item.getQuantite();
                x = x + y;
                Quantite_Produit.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                Quantite_Produit.setValue(item.getQuantite());
                Quantite_Produit.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                        Double x1 = 0d;
                        Double y1 = 0d;
                        item.setQuantite((Integer) Quantite_Produit.getItems().get((Integer) number2));
                        Sous_total.setText(Double.toString(Prod_panier.getPrix() * item.getQuantite()));
                        for (LigneCommande item : LC_Panier) {
                            try {

                                ProduitService PS = new ProduitService();
                                Produit Prod_panier = PS.Get_Produit(item.getIdProduit());
                                y1 = Prod_panier.getPrix() * item.getQuantite();
                                x1 = x1 + y1;

                            } catch (SQLException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        totalPanier = x1;
                        prixT.setText(Double.toString(x1));
                    }
                });

                Image image_panier = new Image(ServerImg + Prod_panier.getPhoto());
                Product_Pic.setImage(image_panier);
                Product_Pic.setFitWidth(127);

                Nom_Produit.setText(Prod_panier.getNom());
                // Quantite_Produit.setValue(item.getQuantite());

                Sous_total.setText(Double.toString(y));

                content.getChildren().add(nodes[j]);
                j++;
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        totalPanier = x;
        prixT.setText(Double.toString(x));

    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Close_BTN.getScene().getWindow();
        stage.close();

    }

    private boolean verify_Qte() throws SQLException {
        for (LigneCommande item : Panier_Session.Produit_Panier) {
            ProduitService PS = new ProduitService();
            Produit produit = PS.Get_Produit(item.getIdProduit());
            if (produit.isDisponible() != true) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Quantite");
                alert.setHeaderText("Verifiez la quantite du produit");
                alert.setContentText("Quantite du produit " + produit.getNom());

                alert.showAndWait();
                return false;
            }

        }
        return true;
    }

    @FXML
    private void Commander(ActionEvent event) throws SQLException {
        if (verify_Qte()) {
            CommandeService CS = new CommandeService();
            Commande CN = new Commande();
            CN.setIdUtilisateur(Session.Session.getInstance().getUser().getId());
            int ID_Commande = CS.Add_Commande(CN);
            for (LigneCommande item : Panier_Session.Produit_Panier) {
                item.setIdCommande(ID_Commande);
                LigneCommandeService LCS = new LigneCommandeService();
                ProduitService PS = new ProduitService();
                Produit P = new Produit();
                P.setId(item.getIdProduit());
                // P.setDisponible(item.getDisponible());
                //  PS.Update(P);
                LCS.Add(item);
            }
            Panier_Session.Produit_Panier = new ArrayList<LigneCommande>();
            try {
                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../UI/Paiement.fxml"));
                Parent root1 = (Parent) fxmlLoader_panier.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.showAndWait();

                /* try {
            statistique.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/statistiqueP.fxml")));
            } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }*/            } catch (IOException ex) {
                Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
