/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import static controlleur.AjoutFilmController.saveToFileImageNormal;
import static controlleur.ListesFilmsController.idFilm;
import static controlleur.ModifierSalleController.idSalle;
import entities.Film;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.image.Image;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class UpdateFilmController implements Initializable {

Connection cnx;    
    
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
    
    public static int idFilm;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    public UpdateFilmController() {
        cnx = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         System.out.println(idSalle);
    }
    
    

    @FXML
    private void ModifFilm(ActionEvent event) {
       
        System.out.println(" l9a el id ");
        System.out.println(idFilm);

        String nom = txt_Nom.getText();
        String duree = txt_duree.getText();
        String description = txt_desc.getText();

        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(txt_dateP.getValue());
        
        
        try {

            String req = "UPDATE salle SET nom=?, duree=? , discription	 where id = ? ";
            PreparedStatement stmp = cnx.prepareStatement(req);
            stmp.setString(1, nom);
            stmp.setString(2, duree);
            stmp.setString(3, description);
            stmp.setInt(3, idFilm);
            System.out.println("famma mochkel");
            stmp.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}