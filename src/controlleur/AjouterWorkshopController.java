/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import controlleur.MonClubController;
import entities.workshop;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.GestionWorkshop;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AjouterWorkshopController implements Initializable {

    @FXML
    private GridPane table;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_nbr;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXDatePicker debut;
    @FXML
    private JFXDatePicker fin;
    @FXML
    private JFXTextArea des;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterworkshop(ActionEvent event) throws SQLException {
        String description = des.getText();

        int nbr = Integer.parseInt(txt_nbr.getText());

        String Nom = txt_nom.getText();
        java.sql.Date d = java.sql.Date.valueOf(debut.getValue());
        java.sql.Date f = java.sql.Date.valueOf(fin.getValue());
        int id = 13;
        workshop w = new workshop(id, Nom, nbr, d, f, description);
        GestionWorkshop gw = new GestionWorkshop();
        gw.ajouter(w);
        new Alert(Alert.AlertType.INFORMATION, "workshop Ajouté").show();

    }

    @FXML
    private void retour(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MesWorkshops.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AjouterWorkshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
