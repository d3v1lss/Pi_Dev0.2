/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.EmailSend;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class MailMdpController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private JFXButton confirmer;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane anchoMail;
    @FXML
    private AnchorPane anchoConf;
    @FXML
    private TextField code;
    @FXML
    private TextField mdp;
    @FXML
    private JFXButton annuler1;
    @FXML
    private JFXButton confirmer1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();

        int x = (int) (Math.random() * ((9000)) + 1000);
        confirmer.setOnMouseClicked(e -> {

            if (!mail.getText().equals("")) {

                if (us.UserCode(x, mail.getText())) {
                    EmailSend mails = new EmailSend();
                    mails.EnvoiMail(mail.getText(), "cyrine.rien@gmail.com", "Mot de passe oublié !", "Bonjour \n"
                            + "Voici le code de confirmation de votre E-Mail concernant le changement du mot de passe : \n"
                            + x);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialogue");
                    alert.setHeaderText("Envoi du mail");
                    alert.setContentText("Un mail contenant un code de confirmation a été envoyé maintenant");
                    alert.showAndWait();

                    anchoConf.setVisible(true);
                    anchoMail.setVisible(false);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Verifiez votre email");
                    alert.setContentText("Cet email est introuvable");
                    alert.showAndWait();
                }

            }
        });
        // TODO
    }

    @FXML
    private void confirm(ActionEvent event) {
        UserService us = new UserService();
        System.out.println(Integer.parseInt(code.getText()));
        int x = (int) (Math.random() * ((9000)) + 1000);
        if (!mdp.equals("") && mdp.getText().length() >= 6) {
            if (us.VerifCode(Integer.parseInt(code.getText()), mail.getText())) {
                System.out.println("");
                us.NouveauMdp(mail.getText(), mdp.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialogue");
                alert.setHeaderText("Changement de mot de passe");
                alert.setContentText("Votre mot de passe a été changé avec succès");
                alert.showAndWait();
                Stage stage = (Stage) mdp.getScene().getWindow();
                stage.close();

            }
        } else {

        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) mdp.getScene().getWindow();
        stage.close();
    }

}
