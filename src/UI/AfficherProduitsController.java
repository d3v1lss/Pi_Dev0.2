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
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AfficherProduitsController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableColumn<Produit, String> description;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button ajouter;

    private static Produit pEdit = new Produit();
    @FXML
    private TableColumn<Produit, Boolean> disponibilité;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitService ps = new ProduitService();
        List<Produit> listP = ps.AfficherProduits();
        table.setItems(FXCollections.observableArrayList(listP));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibilité.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        ajouterBoutonEdit();
        ajouterBoutonDelete();

    }
    

    public void ajouterBoutonDelete() {
        TableColumn<Produit, Void> colBtn = new TableColumn("Delete");
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Produit p = getTableView().getItems().get(getIndex());
                            ProduitService ps = new ProduitService();
                            ps.remove(p.getId());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherProduits.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                                table.getScene().setRoot(root);

                            } catch (IOException ex) {
                                Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void ajouterBoutonEdit() {
        TableColumn<Produit, Void> colBtn = new TableColumn("Edit");
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/modifierProduit.fxml"));
                            Parent root;
                            try {
                                pEdit = getTableView().getItems().get(getIndex());
                                root = loader.load();
                                table.getScene().setRoot(root);

                            } catch (IOException ex) {
                                Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
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

    public Produit getpEdit() {
        return pEdit;
    }

    public void setpEdit(Produit pEdit) {
        this.pEdit = pEdit;
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {
        
                          
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ajouterProduit.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                                table.getScene().setRoot(root);

                            } catch (IOException ex) {
                                Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

    }

}
