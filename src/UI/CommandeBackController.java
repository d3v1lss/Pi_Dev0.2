/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import Entities.User;
import Services.CommandeService;
import Services.LigneCommandeService;
import Services.ProduitService;
import Services.SmsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private TableColumn<Commande, String> etat;
    @FXML
    private Button Retour;
    @FXML
    private TableColumn<Commande, Integer> reference;

    public static Commande c_details = new Commande();

    public static Button x = new Button();
    @FXML
    private AnchorPane anchoDetails;
    @FXML
    private TableView<LigneCommande> tableDetails;
    @FXML
    private TableColumn<LigneCommande, String> nom;
    @FXML
    private TableColumn<LigneCommande, Integer> qte;
    @FXML
    private Button fermer;
    @FXML
    private Label ref;
    @FXML
    private Button valider;
    @FXML
    private TableColumn<LigneCommande,ImageView> photoCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();

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
                                anchoDetails.setVisible(true);
                                c_details = getTableView().getItems().get(getIndex());
                                LigneCommandeService CS = new LigneCommandeService();
                                List<LigneCommande> lc = CS.Display_CommandeBack(c_details.getIdCommande());
                                ref.setText("" + c_details.getReference());
                                tableDetails.setItems(FXCollections.observableArrayList(lc));
                                nom.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
                                qte.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
                                photoCol.setCellValueFactory(new PropertyValueFactory<LigneCommande,ImageView>("PhotoAff"));
                                if (c_details.isValider()) {

                                    valider.setVisible(false);
                                } else {
                                    valider.setVisible(true);

                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(DetailCommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void valider(ActionEvent event) {
        try {
            CommandeService CS = new CommandeService();
            System.out.println(c_details.toString());
            CS.updateEtat(c_details.getIdCommande());
            anchoDetails.setVisible(false);
            fillTable();
            SmsService sms=new SmsService();
            sms.sendSms("Mr/Mme "+c_details.getUsername()+" votre commande "+c_details.getReference()+" est prête. Cité de la Culture Tunis");

        } catch (SQLException ex) {
            Logger.getLogger(DetailCommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retourD(ActionEvent event) {
        anchoDetails.setVisible(false);

        fillTable();

    }

    public void fillTable() {
        try {
            CommandeService CS = new CommandeService();
            List<Commande> Commandes = CS.AfficherCommandes();
            table.setItems(FXCollections.observableArrayList(Commandes));
            nomClient.setCellValueFactory(new PropertyValueFactory<>("username"));
            reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            etat.setCellValueFactory(cellData -> {
                boolean validation = cellData.getValue().isValider();
                String validerAsString;
                if (validation == true) {
                    validerAsString = "Valider";
                } else {
                    validerAsString = "Non Valider";
                }

                return new ReadOnlyStringWrapper(validerAsString);
            });
            ajouterBoutonDelete();
            voirLigneCommande();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
