/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXTextField;
import entities.Theme;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceTheme;

/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class AjouterThemeController implements Initializable {

    @FXML
    private ImageView supprimerBtn;
    @FXML
    private TableView<Theme> tableTheme;
    @FXML
    private TableColumn<Theme, String> colTheme;
    @FXML
    private ImageView modofierBtn;
    @FXML
    private ImageView addThemeBtn;
    @FXML
    private JFXTextField txt_ajouter__theme;
    @FXML
    private JFXTextField txt_modifier_theme1;
    int id;
     String nom;
    ObservableList<Theme> data1 = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceTheme theme = new ServiceTheme();
            data1 = FXCollections.observableArrayList(theme.getAllThemes());
            colTheme.setCellValueFactory(new PropertyValueFactory<Theme,String>("nom"));
            tableTheme.setItems(data1);
            changer();
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterThemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      // modifierTheme();
    }    

    @FXML
    private void afficherimage(MouseEvent event) {
        
    }

   


     
   
    @FXML
    private void AjouterTheme(MouseEvent event) throws SQLException {
        ServiceTheme theme = new ServiceTheme();
        Theme t = new Theme(txt_ajouter__theme.getText());
        theme.ajouterTheme(t);
       data1 = FXCollections.observableArrayList(theme.getAllThemes());

         tableTheme.setItems(data1);
        
    }

    @FXML
    private void supprimerTheme(MouseEvent event) {
        ServiceTheme theme = new ServiceTheme();
        Theme t = tableTheme.getSelectionModel().getSelectedItem();
        for(Theme tt : data1)
        {
            if(t.equals(tt))
            {
                theme.supprimerThemeselected(tt.getId());
                System.out.println("supprimer");
            }
        }
        tableTheme.getItems().removeAll(t);
    }

    @FXML
    private void modifierTheme(MouseEvent event) throws SQLException {
       
   // AjouterThemeController AA = new AjouterThemeController();
  //  AA.changer();
       Theme s1 =tableTheme.getSelectionModel().getSelectedItem();
          //  txt_modifier_theme1.setText(s1.getNom());
          
        ServiceTheme e = new ServiceTheme();
        Theme t = new Theme(s1.getId(), txt_modifier_theme1.getText());
        e.modifierThemeid(s1.getId(), txt_modifier_theme1.getText());
         data1 = FXCollections.observableArrayList(e.getAllThemes());

         tableTheme.setItems(data1);
        
        // tableTheme.getSelectionModel().getSelectedItem();
        System.out.println("modif1");
        
        
    }

   


    private void changer() {
            tableTheme.setOnMouseClicked(e->{
          Theme  p1 = tableTheme.getItems().get(tableTheme.getSelectionModel().getSelectedIndex());
            txt_modifier_theme1.setText(p1.getNom());
        });
    }
    
    
   
}
