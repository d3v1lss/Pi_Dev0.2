/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.SalleServices;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ModifSalleController implements Initializable {

    @FXML
    private TextField nomSalleUpdate;
    @FXML
    private TextField capaciteSalleUpdate;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException, IOException {
        
         SalleServices Ss = new SalleServices();
         Salle salle =new Salle();
           salle.setNom(nomSalleUpdate.getText());
           salle.setCapacite(Integer.parseInt(capaciteSalleUpdate.getText()));
           
           Ss.modifierSalle(salle);
            Parent root= FXMLLoader.load(getClass().getResource("/views/listeSalles.fxml"));
               modifier.getScene().setRoot(root);
    }
    
}
