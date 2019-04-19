/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.User;
import Services.UserService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ProfilXMLController implements Initializable {

    @FXML
    private Label First_Name;
    @FXML
    private Label Last_Name;
    @FXML
    private Label Username;
    @FXML
    private Label E_mail;
    @FXML
    private Label Phone;
    //public User Current_User;
    @FXML
    private Button close;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   Last_Name.setText(userSession.getUser_Lastname());
        UserService us = new UserService();
        User u = us.getUser(Session.Session.getInstance().getUser().getId());
        First_Name.setText(u.getNom());
        Phone.setText(u.getTelephone());
        Last_Name.setText(u.getPrenom());
        E_mail.setText(u.getEmail());
        Username.setText(u.getUsername());
      
    }

   
    @FXML
    private void fermer(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader_afficher_panier = new FXMLLoader(getClass().getResource("/UI/modiferProfil.fxml"));
            Parent root_panier = (Parent) fxmlLoader_afficher_panier.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root_panier));
            stage.show();
            Stage stage1 = (Stage) close.getScene().getWindow();
            stage1.close();
        } catch (IOException ex) {
            Logger.getLogger(ProfilXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
