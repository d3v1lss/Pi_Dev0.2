/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Forum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
      private void gotopubs(MouseEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/views/ListPublication.fxml"));
        retour.getScene().setRoot(root);
        
    }
}
