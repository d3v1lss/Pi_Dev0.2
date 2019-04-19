/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.mailer;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class MailController implements Initializable {

    @FXML
    private GridPane table;
    @FXML
    private Label content;
    @FXML
    private TextArea txt_dis;
    @FXML
    private TextField to;
    @FXML
    private TextField object;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void envoyer(ActionEvent event) {
        String TO = to.getText();
        String Object = object.getText();
        String Content = txt_dis.getText();

        mailer.sendMail(TO, Object, Content);
        new Alert(Alert.AlertType.INFORMATION, "mail envoyé").show();
    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MonClub.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
