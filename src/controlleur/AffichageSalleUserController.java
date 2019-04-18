/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import controlleur.ListSalle;
import entities.Salle;
import services.SalleServices;
import utils.DbConnexion;


/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AffichageSalleUserController implements Initializable {

    @FXML
    private JFXListView<Salle> table;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXTextField rechercher;
    
    Connection cnx; 

    /**
     * Initializes the controller class.
     */
    
    public AffichageSalleUserController() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {         
            
            SalleServices Ss = new services.SalleServices();

            table.getItems().setAll(Ss.AddAll());
            table.setCellFactory(lv -> new ListSalle());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageSalleUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
     
    

    @FXML
    private void findID(MouseEvent event) {
    }

    @FXML
    private void recherche(ActionEvent event) {
    }
    
}
