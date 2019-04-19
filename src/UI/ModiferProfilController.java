/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class ModiferProfilController implements Initializable {

    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton annuler;
    @FXML
    private ChoiceBox<String> ville;
    @FXML
    private TextField nom;
    @FXML
    private TextField telephone;
    @FXML
    private TextField prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserService us = new UserService();

        User u = us.getUser(Session.Session.getInstance().getUser().getId());
        nom.setText(u.getNom());
        telephone.setText(u.getTelephone());
        prenom.setText(u.getPrenom());
        List<String> list = new ArrayList<>();
        String tab[] = {"Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
        for (int i = 0; i < tab.length; i++) {
            list.add(tab[i]);
        }
        ville.setItems(FXCollections.observableArrayList(list));

    }

    @FXML
    private void modifierProfil(ActionEvent event) {
        UserService us=new UserService();
        User u= new User(nom.getText(),Session.Session.getInstance().getUser().getId(), prenom.getText(), ville.getValue(), telephone.getText());
        
        us.updateUser(u);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }

}
