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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class DonnerVotreAvisController implements Initializable {

    @FXML
    private TextArea text;
    String msg;
    @FXML
    private Button retour;
            /**
             * Initializes the controller class.
             */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
retour.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/views/accueilCinema.fxml"));

                Scene scene = new Scene(parent2);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccueilCinemaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public  void enoyer(ActionEvent event) throws Exception {

        System.out.println("envoyer debut");
        Properties properties = new Properties();

        //configuration
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String monCompte = "hbouzid80@gmail.com";
        String password = "houssem12394";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monCompte, password);
            }
        });
        String recepient = "houssem.bouzid@esprit.tn";

        Message message = prepareMessage(session, monCompte, recepient, msg);

        //Message message = prepareMessage(session , monCompte,recepient );
        Transport.send(message);
        System.out.println("message envoyé");

    }

    private  Message prepareMessage(Session session, String monCompte, String recepient, String msg) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(monCompte));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Avis");

            message.setText(msg= text.getText());
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
;

}
