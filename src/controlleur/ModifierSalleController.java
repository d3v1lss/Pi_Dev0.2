/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXTextField;
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
import services.SalleServices;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ModifierSalleController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField capacite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {

        SalleServices Ss = new SalleServices();
        Salle ppa = new Salle();
        ppa.setNom(nom.getText());
        ppa.setCapacite(Integer.parseInt(capacite.getText()));
        Ss.modifierSalle(ppa);
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/AfficherSalle.fxml"));
        
    }
    
    
    

}
