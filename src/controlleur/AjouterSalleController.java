/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Salle;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import utils.DbConnexion;
import services.SalleServices;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AjouterSalleController implements Initializable {

    Connection cnx;
    
    @FXML
    private TextField txtsalle;
    @FXML
    private TextField txtcap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    public AjouterSalleController() {
        cnx = DbConnexion.getInstance().getConnection();
    }
    
    
     @FXML
    private void ajoutSalle(ActionEvent event) throws SQLException {
        
        String nom= txtsalle.getText();
        int capacite =Integer.parseInt(txtcap.getText());
        
         Salle S = new Salle(capacite, nom);
         SalleServices Ss = new SalleServices();
         Ss.ajouterSalle(S);
    }
    
}
