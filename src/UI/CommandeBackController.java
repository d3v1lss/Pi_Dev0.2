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
import Services.LigneCommandeService;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class CommandeBackController implements Initializable {
    
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, String> nomClient;
    @FXML
    private TableColumn<Commande, Boolean> etat;
    @FXML
    private Button Retour;
    @FXML
    private TableColumn<Commande, Integer> reference;
    
    public static Commande c_details = new Commande();
    
    @FXML
    public static Button x=new Button();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CommandeService CS = new CommandeService();
            List<Commande> Commandes = CS.AfficherCommandes();
            table.setItems(FXCollections.observableArrayList(Commandes));
            nomClient.setCellValueFactory(new PropertyValueFactory<>("username"));
            reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            etat.setCellValueFactory(new PropertyValueFactory<>("valider"));
            ajouterBoutonDelete();
            voirLigneCommande();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajouterBoutonDelete() {
        TableColumn<Commande, Void> colBtn = new TableColumn("Delete");
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
                    
                    private final Button btn = new Button("Delete");
                    
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                Commande c = getTableView().getItems().get(getIndex());
                                CommandeService CS = new CommandeService();
                                CS.remove(c.getIdCommande());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/CommandeBack.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    table.getScene().setRoot(root);
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                    }
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        
        table.getColumns().add(colBtn);
    }
    
    public void voirLigneCommande() {
        TableColumn<Commande, Void> colBtn = new TableColumn("Détails");
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
                    
                    private final Button btn = new Button("Détails");
                    
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                c_details = getTableView().getItems().get(getIndex());
                                
                                FXMLLoader fxmlLoader_detail = new FXMLLoader(getClass().getResource("DetailCommandeBack.fxml"));
                                Parent root = (Parent) fxmlLoader_detail.load();
                                
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.setScene(new Scene(root));
                                stage.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                    }
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        
        table.getColumns().add(colBtn);
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
