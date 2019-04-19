/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class AjoutCommentaireController implements Initializable {

    @FXML
    private Button addpub;
    @FXML
    private TextArea contenucomm;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void gotocomms(MouseEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/views/commentaire.fxml"));
        retour.getScene().setRoot(root);
        
    }    
    
}
