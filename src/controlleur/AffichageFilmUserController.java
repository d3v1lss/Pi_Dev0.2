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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import services.FilmServices;
import controlleur.ListeFilm;
import entities.Film;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AffichageFilmUserController implements Initializable {

    @FXML
    private JFXListView<Film> table;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXButton retourFilm;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {

            services.FilmServices Fs = new FilmServices();

            table.getItems().addAll(Fs.FetchAll());

            table.setCellFactory(lv -> new ListeFilm());

        } catch (SQLException ex) {
            Logger.getLogger(ListesFilmsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
