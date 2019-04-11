/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import services.GestionClub;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherMonClubController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<club> table;
    @FXML
    private JFXTextField text_id;
    @FXML
    private Button txt_workshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionClub gc = new GestionClub();

            table.getItems().setAll(gc.MonClub());
            table.setCellFactory(lv -> new ListClub());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listeworkshop(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherMesWorkshop.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
