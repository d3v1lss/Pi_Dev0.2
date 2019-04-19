/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.workshop;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.GestionWorkshop;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherBackWorkshopsController implements Initializable {

    @FXML
    private JFXListView<workshop> table;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXTextField des;
    @FXML
    private JFXDatePicker debut;
    @FXML
    private JFXDatePicker fin;
    private int selectIndex;
    ListWorkshop l = new ListWorkshop();

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        try {
            GestionWorkshop gw = new GestionWorkshop();

            table.getItems().setAll(gw.FetchAll());
            table.setCellFactory(lv -> new ListWorkshop());

            table.setOnMouseClicked(event -> {
                workshop w = table.getSelectionModel().getSelectedItem();
                selectIndex = table.getSelectionModel().getSelectedIndex();
                nom.setText(w.getNom());
                des.setText(w.getDiscription());
                nbr.setText(w.getNombreplaces() + "");
                debut.setValue(LocalDate.parse(w.getDatedebut().toString()));
                fin.setValue(LocalDate.parse(w.getDatefin().toString()));
            });

        } catch (SQLException ex) {
            Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        search.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerWorkshop((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        if (search.getText() == null) {
            try {
                GestionWorkshop gw = new GestionWorkshop();

                l.getTxt_nom().setUserData(new PropertyValueFactory<>("nom"));

            } catch (SQLException ex) {
                Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        l.getTxt_nom().setUserData(TextFieldTableCell.forTableColumn());

    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherBackClubs.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filtrerWorkshop(String oldValue, String newValue) throws SQLException {
        GestionWorkshop gw = new GestionWorkshop();

        ObservableList<workshop> workshop = FXCollections.observableArrayList(gw.FetchAll());
        ObservableList<workshop> filteredList = FXCollections.observableArrayList();

        if (search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(workshop);
        } else {
            newValue = newValue.toUpperCase();
            for (workshop w : table.getItems()) {
                String nom = w.getNom();

                if (nom.toUpperCase().contains(newValue)) {
                    filteredList.add(w);
                }
            }
            table.setItems(filteredList);
        }
    }
}
