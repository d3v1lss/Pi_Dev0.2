/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import static controlleur.AfficherMonClubController.getClub;
import entities.club;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.GestionClub;
import static services.GestionClub.cnx;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class ModifierMonCLubController implements Initializable {

    @FXML
    private TextArea txt_dis;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_act;
    private club club;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficherMonClubController c = new AfficherMonClubController();

        club = getClub();
        setClub(club);
    }

    public void setClub(club club) {

        try {
            GestionClub gc = new GestionClub();

            txt_dis.setText("eee");
            txt_nom.setText("fff");
            txt_mail.setText("mail");
            txt_act.setText("act");

        } catch (SQLException ex) {
            Logger.getLogger(AfficherMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {

        GestionClub gc = new GestionClub();

        club.setNom(txt_nom.getText());
        club.setMail(txt_mail.getText());
        club.setDiscription(txt_dis.getText());
        club.setActivite(txt_act.getText());
        gc.update(club);

        new Alert(Alert.AlertType.INFORMATION, "club modifier").show();
    }

}
