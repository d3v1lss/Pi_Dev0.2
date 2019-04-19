/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Forum;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceForum;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class AjoutForumController implements Initializable {
    
  
    

    @FXML
    private TableColumn<Forum, String> ColumnForumNom;
    @FXML
    private TableColumn<Forum, String> ColumnForumDescription;
    @FXML
    private TableView<Forum> tableforum;
    
    private DbConnexion connexion;
    private static Forum S1;
    private ObservableList<Forum> list;
    @FXML
    private TextField fieldnom;
    @FXML
    private TextArea fielddes;
    @FXML
    private Button ajout;
    @FXML
    private Button delet;
    @FXML
    private TableColumn<Forum, String> idfield;
    @FXML
    private TextField searchfield;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceForum sss = new ServiceForum();
        list = (ObservableList<Forum>) sss.getAllForums();
        ColumnForumNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnForumDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
   
        
        tableforum.setEditable(true);
        ColumnForumNom.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnForumDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        tableforum.setItems(null);
        tableforum.setItems(list);
        
        
    }    


 public void ShowArticle(int id) throws SQLException {
        ServiceForum af = new ServiceForum();
        Forum ar = af.ListForum().filtered(e -> e.getId() == id).get(0);

      
       ColumnForumNom.setText(ar.getNom());
         ColumnForumDescription.setText(ar.getDiscription());
        

    }
 
 
    @FXML
    private void ajoutForum(ActionEvent event) throws SQLException, IOException {

        ServiceForum s = new ServiceForum();
      
        Forum s1 = new Forum(fieldnom.getText(), fielddes.getText());
      

     

        if ((fieldnom.getText().equals("")) && (fielddes.getText().equals("")) ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez remplir le nom.", ButtonType.OK);
            alert.showAndWait();

       

        } else if (fielddes.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez entrer la description.", ButtonType.OK);
            alert.showAndWait();
            
        }
       else {
            s.AjouterForums(s1);
         
       
        }

    }

    
    private void refreshAfftable(MouseEvent event) throws IOException {
        tableforum.getSelectionModel().clearSelection();

    }
    
    
    @FXML
    private void cellNomEdit(TableColumn.CellEditEvent<Forum, String> event) {
        ServiceForum sv = new ServiceForum();
        Forum forumSelected = tableforum.getSelectionModel().getSelectedItem();
        forumSelected.setNom(event.getNewValue().toString());

        try {
            sv.UpdateForum(forumSelected);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le nom est modifié.", ButtonType.OK);
        alert.showAndWait();

    }
    @FXML
    private void cellDescEdit(TableColumn.CellEditEvent<Forum, String> event) {
        ServiceForum sv = new ServiceForum();
        Forum forumSelected = tableforum.getSelectionModel().getSelectedItem();
        forumSelected.setDiscription(event.getNewValue().toString());

        try {
            sv.UpdateForum(forumSelected);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "la description est modifiée.", ButtonType.OK);
        alert.showAndWait();
    }
    
 
    @FXML
    private void searchForum(KeyEvent ke) {
        //personList is table setter getter
        FilteredList<Forum> filterData = new FilteredList<>(list, p -> true);
        searchfield.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(foru -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (foru.getNom().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (foru.getDiscription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                

                return false;
            });
            SortedList<Forum> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableforum.comparatorProperty());
            tableforum.setItems(sortedList);
                       
            
        });

    }
    


  
}
    

