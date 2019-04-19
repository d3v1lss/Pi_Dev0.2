/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.SalleServices;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ModifierSalleController implements Initializable {

    Connection cnx;
    public static int idSalle;

    @FXML
    private JFXButton RetourModifSalle;
    @FXML
    private JFXTextField txtNomSalle;
    @FXML
    private JFXTextField txtCapaciteSalle;

    /**
     * Initializes the controller class.
     */
    public ModifierSalleController() {
        cnx = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(idSalle);
        RetourModifSalle.setOnAction(event -> {
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
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {

        String nom = txtNomSalle.getText();
        int capacite = Integer.parseInt(txtCapaciteSalle.getText());

        Salle s = new Salle(capacite, nom);
        int idUp = idSalle;

        s.setNom(nom);
        s.setCapacite(capacite);

        try {

            String req = "UPDATE salle SET nom=?, capacite=? where id = ? ";
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, s.getNom());
            stm.setInt(2, s.getCapacite());
            stm.setInt(3, idUp);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
