/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import services.GestionClub;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherBackClubsController implements Initializable {

    Connection cnxC;

    @FXML
    private JFXListView<club> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField act;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextArea des;
    
    private int selectIndex;
    
    ListClub l = new ListClub();

    public AfficherBackClubsController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionClub gc = new GestionClub();

            table.getItems().setAll(gc.FetchAll());
            table.setCellFactory(lv -> new ListClub());

            table.setOnMouseClicked(event -> {

                club c = table.getSelectionModel().getSelectedItem();
                selectIndex = table.getSelectionModel().getSelectedIndex();
                nom.setText(c.getNom());
                act.setText(c.getActivite());
                des.setText(c.getDiscription());
                mail.setText(c.getMail());
            });

        } catch (SQLException ex) {
            Logger.getLogger(AfficherClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        search.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerClubs((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherClubsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        if (search.getText() == null) {
            try {
                GestionClub gc = new GestionClub();

                l.getNom().setUserData(new PropertyValueFactory<>("nom"));

            } catch (SQLException ex) {
                Logger.getLogger(AfficherClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        l.getNom().setUserData(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void workshops(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherBackWorkshops.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {

        String query = "DELETE FROM club where nom ='" + nom.getText() + "' ";
        PreparedStatement pstm = cnxC.prepareStatement(query);
        pstm.executeUpdate();
        getAll(event);
    }

    public void filtrerClubs(String oldValue, String newValue) throws SQLException {
        GestionClub gc = new GestionClub();

        ObservableList<club> demandes = FXCollections.observableArrayList(gc.FetchAll());
        ObservableList<club> filteredList = FXCollections.observableArrayList();

        if (search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(demandes);
        } else {
            newValue = newValue.toUpperCase();
            for (club c : table.getItems()) {
                String nom = c.getNom();

                if (nom.toUpperCase().contains(newValue)) {
                    filteredList.add(c);
                }
            }
            table.setItems(filteredList);
        }
    }

    public void getAll(ActionEvent event) throws SQLException {

        GestionClub gc = new GestionClub();

        table.getItems().setAll(gc.FetchAll());
        table.setCellFactory(lv -> new ListClub());

        club c = table.getSelectionModel().getSelectedItem();
        selectIndex = table.getSelectionModel().getSelectedIndex();

    }
}
