/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.listeclub;
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
import services.GestionClub;
import services.GestionWorkshop;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class MesMembresController implements Initializable {

    @FXML
    private JFXListView<listeclub> table;
    @FXML
    private JFXTextField text_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            GestionClub gc = new GestionClub();

            table.getItems().setAll(gc.Fetch());
            table.setCellFactory(lv -> new MesMembres());

        } catch (SQLException ex) {
            Logger.getLogger(MesMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retour(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MonClub.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MesMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
