/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controlleur.ModifierSalleController.idSalle;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private JFXTextField text_id;
    @FXML
    private JFXButton addFilm;
    @FXML
    private JFXButton retour;
    @FXML
    private Button modifSalle;
    private TextField txtid;
    @FXML
    private JFXTextField rechercher;

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
        addFilm.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/views/ajouterSalle.fxml"));

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

        modifSalle.setOnAction(event -> {
            try {
                ModifierSalleController.idSalle = table.getSelectionModel().getSelectedItem().getId();
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/views/modifierSalle.fxml"));

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
                Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        rechercher.setOnAction(event -> {
            
            
            
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
    private void findID(MouseEvent event) {

    }

    @FXML
    private void deleteSalle(ActionEvent event) throws SQLException {
        
        int idSalle = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(" l9a el id ");
        System.out.println(idSalle);
        //int idUp = idSalle;

        String req = "Delete from salle where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, idSalle);
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/views/AfficherSalle.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @FXML
    private void recherche(ActionEvent event) {
        
        
                String rech= rechercher.getText();
                 String req = "Select *from salle where nom=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, rech);
            preparedStatement.execute();
            System.out.println(req);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
    }

}
