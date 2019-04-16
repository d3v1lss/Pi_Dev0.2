/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Film;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
/*
    @FXML
    private void ModifFilm(ActionEvent event) {
        String nom = txt_Nom.getText();
        String duree = txt_duree.getText();
        String description = txt_desc.getText();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(txt_dateP.getValue());

        Film F = new Film(nom, description, duree, gettedDatePickerDate, nameImage1);
        
        
        String req = "UPDATE salle SET nom=?, capacite=? where id = ? "; 
   PreparedStatement stm     =   cnx.prepareStatement(req);
     stm.setString(1, F.getNom());
    stm.setInt(2, s.getCapacite());
    stm.setInt(3, idUp);
    stm.executeUpdate(); 
} 
         catch (SQLException ex) 
         {
             Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
*/
}
