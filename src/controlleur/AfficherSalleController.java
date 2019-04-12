/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.SalleServices;

import services.GestionSalle;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AfficherSalleController implements Initializable {

    public Connection cnx;
    Connection cnxC;
    @FXML
    private ListView<Salle> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXButton addFilm;
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    public AfficherSalleController() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionSalle gs = new GestionSalle();

            table.getItems().setAll(gs.FetchAll());
            table.setCellFactory(lv -> new ListSalle());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Redirection
        
        addFilm.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/views/ajouterSalle.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        retour.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/views/AccueilCinema.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }

    

    private void supprimer(ActionEvent event) throws SQLException {
        String query = "DELETE FROM salle where id_service ='"+text_id.getText()+"' " ;
        PreparedStatement pstm     =   cnxC.prepareStatement(query);
        pstm.executeUpdate();
    }

    @FXML
    private void delete(ActionEvent event) {
    }

}
