/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.mailer;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class DonnerVotreAvisController implements Initializable {

    @FXML
    private TextArea text;
    String msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void enoyer(ActionEvent event) throws Exception {

        /*String TO = "houssem.bouzid@esprit.tn";
         String Object = "RECLAMATION";
         String Content = text.getText();

         mailer.sendMail(TO, Object, Content);
         new Alert(Alert.AlertType.INFORMATION, "mail envoyé").show();*/
    }

    @FXML
    private void retour(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
        Parent root;
        try {
            root = loader.load();
            text.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
