/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import controlleur.ListSalle;
import entities.Salle;
import java.sql.PreparedStatement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.SalleServices;
import utils.DbConnexion;


/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AffichageSalleUserController implements Initializable {

    @FXML
    private JFXListView<Salle> table;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXTextField rechercher;
    
    Connection cnx; 
    ListSalle l = new ListSalle();

    /**
     * Initializes the controller class.
     */
    
    public AffichageSalleUserController() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {         
            
            SalleServices Ss = new services.SalleServices();

            table.getItems().setAll(Ss.AddAll());
            table.setCellFactory(lv -> new ListSalle());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageSalleUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        rechercher.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Resultat((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AffichageSalleUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        if (rechercher.getText() == null) {
            SalleServices Sc = new SalleServices();
            l.getNom().setUserData(new PropertyValueFactory<>("nom"));
        }

        l.getNom().setUserData(TextFieldTableCell.forTableColumn());

    }
        
        
    public void Resultat(String oldValue, String newValue) throws SQLException {
        SalleServices Ss = new SalleServices();

        ObservableList<Salle> salle = FXCollections.observableArrayList(Ss.FetchAll());
        ObservableList<Salle> filteredList = FXCollections.observableArrayList();

        if (rechercher == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(salle);
        } else {
            newValue = newValue.toUpperCase();
            for (Salle s : table.getItems()) {
                String nom = s.getNom();

                if (nom.toUpperCase().contains(newValue)) {
                    filteredList.add(s);
                }
            }
            table.setItems(filteredList);
        }
    }    
    
     
    

    @FXML
    private void findID(MouseEvent event) {
    }

    
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
