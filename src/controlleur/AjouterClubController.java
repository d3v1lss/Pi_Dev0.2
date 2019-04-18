/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import services.GestionClub;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.GridPane;

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
    @FXML
    private GridPane table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterclub(ActionEvent event) throws SQLException {

        String description = txt_dis.getText();
        String Mail = txt_mail.getText();
        String Nom = txt_nom.getText();
        String Activite = txt_act.getText();
        String S = "Non";
        GestionClub gc = new GestionClub();
        club C = new club(gc.getLasT()+1, Nom, Mail, description, 0, Activite,13 ,S);

        gc.ajouter(C);
        new Alert(Alert.AlertType.INFORMATION, "Club Ajouté").show();
    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherClubs.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
