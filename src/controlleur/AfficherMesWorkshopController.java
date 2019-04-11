/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.workshop;
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
import javafx.scene.control.ListView;
import services.GestionWorkshop;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherMesWorkshopController implements Initializable {

    Connection cnxC;
    @FXML
    private ListView<workshop> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;

    public AfficherMesWorkshopController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionWorkshop gs = new GestionWorkshop();

            table.getItems().setAll(gs.MesWorkshop());
            table.setCellFactory(lv -> new ListWorkshop());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherMesWorkshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterworkshop(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterWorkshop.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
