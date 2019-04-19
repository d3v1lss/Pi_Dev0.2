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
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.SalleServices;

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
            
            
            
    ListeFilm lf = new ListeFilm();

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
        
        
        retourFilm.setOnAction(event -> {
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
                Logger.getLogger(ListesFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        search.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Resultat((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AffichageFilmUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        if (search.getText() == null) {
            SalleServices Sc = new SalleServices();
            lf.getNom().setUserData(new PropertyValueFactory<>("nom"));
        }

        lf.getNom().setUserData(TextFieldTableCell.forTableColumn());

    }
        
        
        
       
    
 public void Resultat(String oldValue, String newValue) throws SQLException {
        FilmServices Fs = new FilmServices();

        ObservableList<Film> film = FXCollections.observableArrayList(Fs.FetchAll());
        ObservableList<Film> filteredList = FXCollections.observableArrayList();

        if (search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(film);
        } else {
            newValue = newValue.toUpperCase();
            for (Film s : table.getItems()) {
                String nom = s.getNom();

                if (nom.toUpperCase().contains(newValue)) {
                    filteredList.add(s);
                }
            }
            table.setItems(filteredList);
        }
    }
 } 
