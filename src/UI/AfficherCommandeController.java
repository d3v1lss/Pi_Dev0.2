/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Entities.Commande;
import Services.CommandeService;
import static UI.CommandeBackController.c_details;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
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
public class AfficherCommandeController implements Initializable {

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> reference;
    @FXML
    private TableColumn<Commande, Boolean> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CommandeService cs = new CommandeService();
            List<Commande> commandes = cs.AfficherCommandesClient(Session.Session.getInstance().getUser().getId());
            table.setItems(FXCollections.observableArrayList(commandes));
            reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            etat.setCellValueFactory(new PropertyValueFactory<>("valider"));
            voirLigneCommande();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

                                FXMLLoader fxmlLoader_detail = new FXMLLoader(getClass().getResource("DetailCommandeClient.fxml"));
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

}
