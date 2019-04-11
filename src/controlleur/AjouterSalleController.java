/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;


import entities.salle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import services.GestionSalle;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AjouterSalleController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_cap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajoutersalle(ActionEvent event) throws SQLException, IOException {

        String nom = txt_nom.getText();
    

        salle s = new salle(nom, 20);

        GestionSalle gs = new GestionSalle();
        gs.ajouter(s);
        new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }

}
