/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import services.GestionClub;
import entities.UserSession;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.listeclub;
import entities.listeworkshop;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static services.GestionClub.cnx;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherClubsController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<club> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField act;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextArea des;
    private int selectIndex;
    UserSession us;
    private int iduser;
    ListClub l = new ListClub();
    @FXML
    private JFXTextField text_id;

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
                nbr.setText(c.getNbrparticipant() + "");
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
    private void AjouterClub(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterClub.fxml"));
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
    private void inscrit(ActionEvent event) throws SQLException {

        String club = nom.getText();
        String user = "AKRAM";
        int i = Integer.parseInt(nbr.getText());
        listeclub lclub = new listeclub(club, user);

        GestionClub gc = new GestionClub();
        gc.inscrit(lclub);
        club c = new club();
        System.out.println(i);
        int y = i + 1;
        System.out.println(y);
        c.setNbrparticipant(y);
        String req = "update `club` SET `nbrparticipant`=?  where nom ='" + nom.getText() + "' ";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, c.getNbrparticipant());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClub.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Alert(Alert.AlertType.INFORMATION, "inscrit fait").show();
    }

    @FXML
    private void workshops(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherWorkshops.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

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
}
