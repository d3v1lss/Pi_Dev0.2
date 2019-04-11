/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXTextField;

import entities.salle;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import services.GestionClub;
import services.GestionSalle;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherSalleController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<salle> table;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    public AfficherSalleController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionSalle gs = new GestionSalle();

            table.getItems().setAll(gs.FetchAll());
            table.setCellFactory(lv -> new ListSalle());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
