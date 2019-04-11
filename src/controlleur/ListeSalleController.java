/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SalleServices;
import entities.Salle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ListeSalleController implements Initializable {
    
    
    @FXML
    private TableView<Salle> listeSalle;
    @FXML
    private TableColumn<Salle, String> nomSalle;
    @FXML
    private TableColumn<Salle, Integer> capacite;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SalleServices SalleService = new SalleServices();
        ArrayList arrayList= (ArrayList) SalleService.selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeSalle.setItems(observableList);
        nomSalle.setCellValueFactory(new PropertyValueFactory<>("nomSalle"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
    }    

    @FXML
    private void modiferSalle(ActionEvent event) throws IOException  {
        
       
    }
    
}
