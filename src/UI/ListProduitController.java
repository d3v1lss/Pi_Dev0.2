/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ignitedev
 */
public class ListProduitController implements Initializable {

    @FXML
    private HBox Show_Product;
   
    @FXML
    private HBox Show_Cart;
    @FXML
    private GridPane List_Product;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Show_Product.setOnMouseClicked(e -> {
            int i = 0;
            List_Product.getChildren().clear();
            ProduitService produit_service = new ProduitService();
            List<Produit> data = produit_service.AfficherProduits();
            Node[] nodes = new Node[data.size()];
            System.out.println(data.size());
            for (Produit item : data) {
                try {
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Produit_item/Single_Produit.fxml"));
                    
                    nodes[i] = (Node) fxmlLoader.load();
                    
                    Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                    
                    Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                    Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                    JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                    Image image = new Image(item.getPhoto());
                    ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                    Product_Pic.setImage(image);
                    Product_Pic.setFitWidth(127);
                    Nom_Produit.setText(item.getNom());
                    Prix_Produit.setText(Double.toString(item.getPrix()));
                    boolean res = item.isDisponible();
                    if (res == true) {
                        Quantite_Produit.setText("Disponible");
                    } else {
                        Quantite_Produit.setText("Non disponible");
                    }

                    //Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));
                    Commander_BTN.setOnMouseClicked(e1 -> {
                        try {
                            
                            /**/
                            FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("/AjouterPanier/AjouterPanier.fxml"));
                            Parent root1 = (Parent) fxmlLoader_panier.load();
                            
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initStyle(StageStyle.UNDECORATED);
                            ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                            Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                            Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");
                            
                            /*Image image_panier = new Image(item.getImage());
                            Image_Produit.setImage(image_panier);
                            Image_Produit.setFitHeight(150);*/
                            Nom_Produit_Panier.setText(item.getNom());
                            boolean res1 = item.isDisponible();
                            if (res1 == true) {
                                Quantite_Produit.setText("Disponible");
                            } else {
                                Quantite_Produit.setText("Non disponible");
                            }
                            stage.setScene(new Scene(root1));
                            // AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                            //display.SetProduit(item);
                            
                            stage.showAndWait();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    });
                    
                    List_Product.addRow(i, nodes[i]);
                    
                    i++;
                    
                } catch (IOException ex) {
                    Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        Show_Cart.setOnMouseClicked(e2 -> {

            try {
                /**/
                FXMLLoader fxmlLoader_afficher_panier = new FXMLLoader(getClass().getResource("/Panier/Panier.fxml"));
                Parent root_panier = (Parent) fxmlLoader_afficher_panier.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root_panier));
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

      

    }

}
