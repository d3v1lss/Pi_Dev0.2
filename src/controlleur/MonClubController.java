/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import services.GestionClub;
import entities.UserSession;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import static services.GestionClub.cnx;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class MonClubController implements Initializable {

    @FXML
    private JFXListView<club> table;
    @FXML
    private JFXTextField text_id;

    UserSession us;
    private int iduser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            GestionClub gc = new GestionClub();

            table.getItems().setAll(gc.MonClub());
            table.setCellFactory(lv -> new MonClub());
        } catch (SQLException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void modifierclub(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ModifierMonClub.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        new Alert(Alert.AlertType.INFORMATION, "club Ajouté").show();
    }

    @FXML
    private void mesmembres(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MesMembres.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listeworkshop(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MesWorkshops.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
