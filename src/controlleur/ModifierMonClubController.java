/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import entities.club;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import static services.GestionClub.cnx;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class ModifierMonClubController implements Initializable {

    @FXML
    private GridPane table;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void modifier(ActionEvent event) {
        
        String nom = txt_nom.getText();
        String des = txt_dis.getText();
        String mail = txt_mail.getText();
        String act = txt_act.getText();
        club c = new club();
        c.setMail(mail);
        c.setActivite(act);
        c.setDiscription(des);
        c.setNom(nom);
        try {

            String req = "UPDATE club SET nom=?, mail=?, discription=?,activite=?"
                    + "where president = 13 ";
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, c.getNom());
            stm.setString(2, c.getMail());
            stm.setString(3, c.getDiscription());
            stm.setString(4, c.getActivite());
            stm.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Club modifié").show();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MonClub.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
