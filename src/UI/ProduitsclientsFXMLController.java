/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import Entities.Produit;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author oumayma haouet
 */
public class ProduitsclientsFXMLController implements Initializable {

public static String ServerImg = "http://localhost/Pi_Dev0.1/web/img/";   
private ObservableList<Produit> dataUser;
    @FXML
    private GridPane List_Product;
  
    @FXML
    private HBox Show_Cart;

    private JFXTextField cherche;
    @FXML
    private JFXTextField rechercheavancee;
    @FXML
    private JFXButton acsTrie1;
    @FXML
    private JFXButton chercherav;
    @FXML
    private JFXButton recherche;
    @FXML
    private JFXButton descTrie;
    @FXML
    private JFXButton acsTrie;
    @FXML
    private JFXComboBox<?> txtSousCategorie1;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        Show_Cart.setOnMouseClicked(e2 -> {

            try {
                
               
                FXMLLoader fxmlLoader_afficher_panier = new FXMLLoader(getClass().getResource("Panier.fxml"));
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

        /*Show_Command.setOnMouseClicked(e3 -> {
            System.out.println("test");
            try {

                
                FXMLLoader fxmlLoader_commande = new FXMLLoader(getClass().getResource("../gui/Commande.fxml"));
                Parent root1 = (Parent) fxmlLoader_commande.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);

                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
*/
        int i = 0;
        List_Product.getChildren().clear();
        ProduitService produit_service = new ProduitService();
        List<Produit> data = produit_service.AfficherProduits();
        Node[] nodes = new Node[data.size()];
        //  System.out.println(data.size());
        for (Produit item : data) {
            
            try {
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../UI/Single_Produit.fxml"));
                
                nodes[i] = (Node) fxmlLoader.load();
                
                Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                Image image = new Image( ServerImg + item.getPhoto());
                ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                Product_Pic.setImage(image);
                Product_Pic.setFitWidth(127);
                Nom_Produit.setText(item.getNom());
                Prix_Produit.setText(Double.toString(item.getPrix()));
                boolean res1 = item.isDisponible();
                if (res1 == true) {
                    Quantite_Produit.setText("Disponible");
                } else {
                    Quantite_Produit.setText("Non disponible");
                }

                Voirplus.setOnMouseClicked((MouseEvent e1) -> {
                    
                    try {
                        
                        FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../UI/DetailproduitFXML.fxml"));
                        Parent root1 = (Parent) fxmlLoader_panier.load();
                        
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.UNDECORATED);
                        ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                        Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                        Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                        Label Desc_Produit_P = (Label) root1.lookup("#Description");
                        Label Prix_P = (Label) root1.lookup("#Quantite_Produit11");
                        //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                        Image image_p = new Image(ServerImg + item.getPhoto());
                        Image_Produit.setImage(image_p);
                        Image_Produit.setFitHeight(150);
                        
                        Nom_Produit_P.setText(item.getNom());
                        boolean res2 = item.isDisponible();
                        if (res1 == true) {
                            Quantite_Produit.setText("Disponible");
                        } else {
                            Quantite_Produit.setText("Non disponible");
                        }
                        
                        Prix_P.setText(Double.toString(item.getPrix()));
                        Desc_Produit_P.setText(item.getDescription());
                        stage.setScene(new Scene(root1));
                        /*DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                        display.SetProduit(item);*/
                        
                        stage.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                Commander_BTN.setOnMouseClicked(e1 -> {
                    try {
                        
                        /**/
                        FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                        Parent root1 = (Parent) fxmlLoader_panier.load();
                        
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.UNDECORATED);
                        ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                        Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                        Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");
                        
                        /* Image image_panier = new Image(Routes.ServerImg + item.getImage());
                        Image_Produit.setImage(image_panier);
                        Image_Produit.setFitHeight(150);*/
                        
                        Nom_Produit_Panier.setText(item.getNom());
                        boolean res2 = item.isDisponible();
                            if (res2 == true) {
                                Quantite_Produit.setText("Disponible");
                            } else {
                                Quantite_Produit.setText("Non disponible");
                            }
                        stage.setScene(new Scene(root1));
                         AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                        display.SetProduit(item);
                        System.out.println(item);
                        stage.showAndWait();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                List_Product.addRow(i, nodes[i]);
                
                i++;
                
            } catch (IOException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       /* recherche.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.chercherProduitdispo();
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                             
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        chercherav.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.chercherProduitepuise();
                Node[] nodes = new Node[data.size()];
                //  System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        rechercheavancee.setOnAction((ActionEvent event) -> {

            try {
                int i = 0;

                String nom = rechercheavancee.getText();
                //   int id= Integer.parseInt(recherche.getText());

                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheproduitparsonnom(nom);
                Node[] nodes = new Node[data.size()];
                //   System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });*/

       /* acsTrie.setOnAction((ActionEvent event) -> {

            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.trierlesproduitsascendant();
                Node[] nodes = new Node[data.size()];
                //   System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        descTrie.setOnAction((ActionEvent event) -> {

            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.trierlesproduitsdescendant();
                Node[] nodes = new Node[data.size()];
                //   System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                               
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        })

        EteRadio.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheproduitparsaisonETE();
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                              
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        PrintempsRadio.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheproduitparsaisonPRINTEMPS();
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        AutomneRadio.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheproduitparsaisonAUTOMNE();
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                               
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Hiver1.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                //   int id= Integer.parseInt(recherche.getText());
                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheproduitparsaisonHIVER();
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        recherchecategorie.setOnAction((ActionEvent event) -> {
            try {
                int i = 0;

                CategorieProduit nom = recherchecategorie.getValue();
                //     int id= Integer.parseInt(recherche.getText());

                List_Product.getChildren().clear();

                ProduitService produit_service = new ProduitService();
                List<Produit> data = produit_service.rechercheaveccategorie(nom.toString());
                Node[] nodes = new Node[data.size()];
                //      System.out.println(data.size());

                for (Produit item : data) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/Single_Produit.fxml"));

                        nodes[i] = (Node) fxmlLoader.load();

                        Label Nom_Produit = (Label) nodes[i].lookup("#Nom_Produit");
                        Label Prix_Produit = (Label) nodes[i].lookup("#Prix_Produit");
                        Label Quantite_Produit = (Label) nodes[i].lookup("#Quantite_Produit");
                        JFXButton Commander_BTN = (JFXButton) nodes[i].lookup("#Commander_BTN");
                        JFXButton Voirplus = (JFXButton) nodes[i].lookup("#Voirplus");
                        Image image = new Image(Routes.ServerImg + item.getImage());
                        ImageView Product_Pic = (ImageView) nodes[i].lookup("#Product_Pic");
                        Product_Pic.setImage(image);
                        Product_Pic.setFitWidth(127);
                        Nom_Produit.setText(item.getNom());
                        Prix_Produit.setText(Double.toString(item.getPrix()));
                        Quantite_Produit.setText(Integer.toString(item.getQuantiteStock()));

                        Voirplus.setOnMouseClicked(e1 -> {

                            try {

                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("../GUI/DetailproduitFXML.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_P = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_P = (Label) root1.lookup("#Quantite_Produit");
                                Label Prix_P = (Label) root1.lookup("#Prix_Produit");
                                //   Label Prix_P= (Label) root1.lookup("#Prix_Produit");
                                Image image_p = new Image(Routes.ServerImg + item.getImage());
                                Image_Produit.setImage(image_p);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_P.setText(item.getNom());
                                Qte_Produit_P.setText(Integer.toString(item.getQuantiteStock()));

                                //Prix_P.setText(Double.toString(item.getPrix()));
                                //    Prix_P.setText(item.getDescription());
                                stage.setScene(new Scene(root1));
                                DetailproduitFXMLController display = fxmlLoader_panier.<DetailproduitFXMLController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Commander_BTN.setOnMouseClicked(e1 -> {
                            try {

                                
                                FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                                Parent root1 = (Parent) fxmlLoader_panier.load();

                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                ImageView Image_Produit = (ImageView) root1.lookup("#Image_Produit");
                                Label Nom_Produit_Panier = (Label) root1.lookup("#Nom_Produit");
                                Label Qte_Produit_Panier = (Label) root1.lookup("#Quantite_Produit");

                                Image image_panier = new Image(item.getImage());
                                Image_Produit.setImage(image_panier);
                                Image_Produit.setFitHeight(150);

                                Nom_Produit_Panier.setText(item.getNom());
                                Qte_Produit_Panier.setText(Integer.toString(item.getQuantiteStock()));
                                stage.setScene(new Scene(root1));
                                AjouterPanierController display = fxmlLoader_panier.<AjouterPanierController>getController();
                                display.SetProduit(item);

                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        List_Product.addRow(i, nodes[i]);

                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(ProduitsclientsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });*/

    }


    @FXML
    private void rechercheavancee(KeyEvent event) {
    }


    @FXML
    private void acsTrie(KeyEvent event) {
    }


    @FXML
    private void MenuAction(ActionEvent event) throws IOException {

       // acsTrie.getScene().setRoot(FXMLLoader.load(getClass().getResource(Routes.HOME)));
    }

}
