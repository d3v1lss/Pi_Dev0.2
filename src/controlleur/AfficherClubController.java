/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXTextField;
import entities.club;
import java.net.URL;
import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import services.GestionClub;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherClubController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<club> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;

    public AfficherClubController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionClub gc = new GestionClub();

            table.getItems().setAll(gc.FetchAll());
            table.setCellFactory(lv -> new ListClub());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Deleteclub(ActionEvent event) {
    }

    @FXML
    private void updateclubb(ActionEvent event) {
    }

    

}
