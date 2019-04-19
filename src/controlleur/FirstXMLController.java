/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Session;
import entities.User;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;

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
            Session.getInstance().setUser(u);
            if (u.getRole().contains("ROLE_ADMIN")) {
                // System.out.println("raw hedha admin");
                logB.getScene().setRoot(FXMLLoader.load(getClass().getResource("/views/BackHome.fxml")));
            } else if (u.getRole().contains("ROLE_CLIENT")) {
                // System.out.println("raw hedha admin");
                logB.getScene().setRoot(FXMLLoader.load(getClass().getResource("/views/Home.fxml")));
            } else {
                // alert.setAlertType(Alert.AlertType.INFORMATION);

                logB.getScene().setRoot(FXMLLoader.load(getClass().getResource("/views/Home.fxml")));
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
        User p = new User(username, pass, email);
        UserService us = new UserService();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (us.insert(p) == 0) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Created");
            alert.setHeaderText("Account Created");
            alert.showAndWait();
            sign.setVisible(false);

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
            alert.setHeaderText("Choose another email");
            alert.showAndWait();
        }

    }

}
