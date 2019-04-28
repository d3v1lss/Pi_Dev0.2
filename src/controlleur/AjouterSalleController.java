/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Salle;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import utils.DbConnexion;
import services.SalleServices;
import org.controlsfx.control.Notifications;


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
    @FXML
    private Button RetourListeSalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Redirection

        RetourListeSalle.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/views/AfficherSalle.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public AjouterSalleController() {
        cnx = DbConnexion.getInstance().getConnection();
    }

    @FXML
    private void ajoutSalle(ActionEvent event) throws SQLException {

        String nom = txtsalle.getText();
        int capacite = Integer.parseInt(txtcap.getText());

        Salle S = new Salle(capacite, nom);
        SalleServices Ss = new SalleServices();
        Ss.ajouterSalle1(S);

        Notifications.create()
                .title("salle ajouté")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER).show();

        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/views/AfficherSalle.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Interface 2");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
