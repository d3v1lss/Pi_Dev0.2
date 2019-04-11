/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXTextField;

import entities.workshop;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import services.GestionWorkshop;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherWorkshopController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<workshop> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;

    public AfficherWorkshopController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionWorkshop gs = new GestionWorkshop();

            table.getItems().setAll(gs.MesWorkshop());
            table.setCellFactory(lv -> new ListWorkshop());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherWorkshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
