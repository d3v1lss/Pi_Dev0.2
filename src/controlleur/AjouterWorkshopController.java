/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXDatePicker;
import entities.workshop;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.GestionWorkshop;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AjouterWorkshopController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextArea txt_dis;
    @FXML
    private TextField txt_nom;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXDatePicker datedebut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retour.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/views/AfficherMesWorkshop.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void ajouterworkshop(ActionEvent event) throws SQLException, IOException {

        String description = txt_dis.getText();
        String Nom = txt_nom.getText();
        java.sql.Date debut = java.sql.Date.valueOf(datefin.getValue());
        java.sql.Date fin = java.sql.Date.valueOf(datedebut.getValue());
        // int nb = txt_nbr.getInt();

        workshop w = new workshop(Nom, 21, debut, fin, description);
        GestionWorkshop gw = new GestionWorkshop();
        gw.ajouter(w);
        new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }

}
