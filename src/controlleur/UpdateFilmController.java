/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class UpdateFilmController implements Initializable {

    @FXML
    private TextField txt_Nom;
    @FXML
    private TextField txt_duree;
    @FXML
    private TextArea txt_desc;
    @FXML
    private DatePicker txt_dateP;
    @FXML
    private Button RetourListeFilm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifFilm(ActionEvent event) {
        String nom = txt_Nom.getText();
        String duree = txt_duree.getText();
        String desc = txt_desc.getText();
        
        int capacite = Integer.parseInt(txtCapaciteSalle.getText());
    }
    
}
