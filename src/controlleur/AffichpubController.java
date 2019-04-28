/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Forum;
import entities.Publication;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import services.ServiceForum;
import services.ServicePublication;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class AffichpubController implements Initializable {

    @FXML
    private TableColumn<Publication, String> idpub;
    @FXML
    private TableColumn<Publication, String> forumid;
    @FXML
    private TableColumn<Publication, String> userid;
    @FXML
    private TableColumn<Publication, String> contenu;
    @FXML
    private TableColumn<Publication, String> titre;

    private DbConnexion connexion;
    private static Publication P1;
    private ObservableList<Publication> listt;
    @FXML
    private TableView<Publication> tablepublication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePublication ppp = new ServicePublication();
        listt = (ObservableList<Publication>) ppp.getAllpublications();

        idpub.setCellValueFactory(new PropertyValueFactory<>("idpub"));
        forumid.setCellValueFactory(new PropertyValueFactory<>("forumid"));
        userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        titre.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        tablepublication.setEditable(true);
        idpub.setCellFactory(TextFieldTableCell.forTableColumn());
        forumid.setCellFactory(TextFieldTableCell.forTableColumn());
        userid.setCellFactory(TextFieldTableCell.forTableColumn());
        contenu.setCellFactory(TextFieldTableCell.forTableColumn());
        titre.setCellFactory(TextFieldTableCell.forTableColumn());
        tablepublication.setItems(null);
        tablepublication.setItems(listt);

    }

}
