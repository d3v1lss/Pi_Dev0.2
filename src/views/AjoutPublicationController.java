/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Forum;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ServiceForum;
import services.ServicePublication;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class AjoutPublicationController implements Initializable {

    @FXML
    private Button addpub;
    private ObservableList<Forum> forum = FXCollections.observableArrayList();
    @FXML
    private Button retour;
    @FXML
    private TextField titrepub;
    @FXML
    private TextArea contenupub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
    }
  @FXML
    private void ajoutpub(ActionEvent event) throws SQLException, IOException {

        ServicePublication s = new ServicePublication();
      
        Publication s1 = new Publication(titrepub.getText(), contenupub.getText());
      

     

        if ((titrepub.getText().equals("")) && (contenupub.getText().equals("")) ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez remplir le nom.", ButtonType.OK);
            alert.showAndWait();

       

        } else if (titrepub.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez entrer la description.", ButtonType.OK);
            alert.showAndWait();
            
        }
       else {
            s.AjouterPublication(s1);
         
       
        }

    }    
    @FXML
      private void gotopubs(MouseEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/views/ListPublication.fxml"));
        retour.getScene().setRoot(root);
        
    }
}
