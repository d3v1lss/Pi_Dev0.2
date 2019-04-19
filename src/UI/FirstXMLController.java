/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;
import utils.EmailSend;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class FirstXMLController implements Initializable {

    private Button button;
    private TextField texte;
    @FXML
    private Label lab;
    @FXML
    private ImageView pic;
    @FXML
    private AnchorPane home;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pwd;
    @FXML
    private AnchorPane sign;
    @FXML
    private TextField userNew;
    @FXML
    private PasswordField pwdNew;
    @FXML
    private TextField mail;
    @FXML
    private Button logB;
    @FXML
    private Button signB;
    @FXML
    private Button createB;
    @FXML
    private PasswordField pwdNewConf;
    @FXML
    private Button backB;
    @FXML
    private Label verifLab;
    private Boolean verif = true;
    @FXML
    private Button mdpOublier;
    @FXML
    private AnchorPane anchoVerif;
    @FXML
    private TextField codeVerif;
    String usernamee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        String username = user.getText();
        String pass = pwd.getText();
        User p = new User(username, pass);
        UserService us = new UserService();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        User u = us.logMeIn(pass, username);
        if (u != null) {
            Session.Session.getInstance().setUser(u);
            if (u.getUsername().equals("dorra")) {
                // System.out.println("raw hedha admin");
                logB.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/BackHome.fxml")));
            } else {
                // alert.setAlertType(Alert.AlertType.INFORMATION);

                logB.getScene().setRoot(FXMLLoader.load(getClass().getResource("/UI/Home.fxml")));
            }

        } else if (u == null) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Password");
            alert.setHeaderText("Wrong Password");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Username");
            alert.setHeaderText("Wrong Username");
            alert.showAndWait();
        }
    }

    @FXML
    private void signIN(ActionEvent event) {
        sign.setVisible(true);
    }

    @FXML
    private void back(ActionEvent event) {
        sign.setVisible(false);
    }

    @FXML
    private void create(ActionEvent event) {
        String username = userNew.getText();
        String pass = pwdNew.getText();
        String email = mail.getText();
        usernamee=userNew.getText();
        User p = new User(username, pass, email);
        UserService us = new UserService();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Pattern pa = Pattern.compile("[a-zA-Z0-9][a-zA-z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+", Pattern.CASE_INSENSITIVE);
        Matcher m;
        m = pa.matcher(this.mail.getText());

        if (this.mail.getText().equals("") || this.userNew.getText().equals("") || this.pwdNew.getText().equals("")) {
            alert.setTitle("Fill all the blanks");
            alert.setHeaderText(null);
            alert.setContentText("You have to give all the information to register!");
            alert.showAndWait();
            verif = false;
        } else {
            verif = true;
        }
        if (!m.matches()) {
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Email");
            alert.showAndWait();
            verif = false;

        } else {
            verif = true;
        }
        if (verif) {
            if (us.insert(p) == 0) {

                int x = (int) (Math.random() * ((9000)) + 1000);
                us.UserCode(x, mail.getText());
                System.out.println(us.UserCode(x, mail.getText()));
                EmailSend mails = new EmailSend();
                mails.EnvoiMail(mail.getText(), "citedelaculture.tunisie@gmail.com", "Compte crée !", "Bonjour \n"
                        + "Voici le code de confirmation de votre E-Mail pour activer votre profil : \n"
                        + x);
                /* alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Account Created");
                alert.setHeaderText("Account Created");
                alert.showAndWait();*/
                sign.setVisible(false);
                userNew.setText("");
                pwdNew.setText("");
                mail.setText("");
                anchoVerif.setVisible(true);

            } //Username already exist
            else if (us.insert(p) == 1) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("User name already exist");
                alert.setHeaderText("Choose another username");
                alert.showAndWait();
            } //Email already exist
            else if (us.insert(p) == 2) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Email already used");
                alert.setHeaderText("Email already used");
                alert.setContentText("Please choose another Email");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void verifAction(KeyEvent event) {
        String pass = pwdNew.getText();
        String confirmPass = pwdNewConf.getText();
        if (pass.equals(confirmPass)) {
            pwdNewConf.setStyle("-fx-background-color: #09e509;");
            verifLab.setText("");
        } else {
            pwdNewConf.setStyle("-fx-background-color: red;");
            verifLab.setText("Incorrespendant");

        }

    }

    @FXML
    private void mdpOublier(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader_panier = new FXMLLoader(getClass().getResource("/UI/mailMdp.fxml"));
            Parent root1 = (Parent) fxmlLoader_panier.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FirstXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verifCompteAction(ActionEvent event) throws SQLException {
        UserService us = new UserService();
        User u= us.getIdUser(usernamee);
        u.setConfirmationToken(codeVerif.getText());
        System.out.println(u.toString());
        if (us.verifierToken(u)) {
            anchoVerif.setVisible(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Code de confirmation incorrect");
            alert.setHeaderText("Code de confirmation incorrect");
            alert.setContentText("Veuillez vérifier le Code de confirmation reçu par mail.");
            alert.showAndWait();
        }

    }

    @FXML
    private void fermerAction(ActionEvent event) {
        anchoVerif.setVisible(false);
    }
}
