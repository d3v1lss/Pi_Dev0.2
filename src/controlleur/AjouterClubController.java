/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import entities.club;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.GestionClub;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AjouterClubController implements Initializable {

    @FXML
    private TextArea txt_dis;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_act;
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterclub(ActionEvent event) throws SQLException, IOException {

        String description = txt_dis.getText();
        String Mail = txt_mail.getText();
        String Nom = txt_nom.getText();
        String Activite = txt_act.getText();
        String S = "Non";

        club C = new club(Nom, Mail, description, 0, Activite, S);

        GestionClub gc = new GestionClub();
        gc.ajouter(C);
        new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }

}
