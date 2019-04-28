/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Forum;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import services.ServiceForum;
import services.ServicePublication;
import controlleur.ListPubController;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivForumController_1 implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label txtprix;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label discriptionF;
    @FXML
    private Label nomF;
    @FXML
    private Label id;
    @FXML
    private Button ajouterPublication;
    int idForum;
    @FXML
    private Button addpublication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void LoadValues(Forum e) throws IOException {
        ServiceForum ies = new ServiceForum();

        System.out.println("1=" + e.getId());
        System.out.println("2=" + e.getNom());
        discriptionF.setText((e.getDiscription()));

        nomF.setText(e.getNom());
        discriptionF.setText(String.valueOf(e.getDiscription()));

        id.setText(String.valueOf(e.getId()));

        idForum = ies.retourforumId(e.getNom());

        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }

        }
        );

    }

    @FXML
    private void ajouterPub(ActionEvent event) throws IOException {

        Publication r = new Publication();
        ServicePublication ps = new ServicePublication();
        ServiceForum pst = new ServiceForum();

        FXMLLoader Loder = new FXMLLoader();

        Loder.setLocation(getClass().getResource("/views/ListPublication.fxml"));

        Loder.load();
        ListPubController display = Loder.getController();

        display.showinItems(idForum);

        Parent AnchorPane = Loder.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(AnchorPane);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void gotopub(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/AjoutPublication.fxml"));
        addpublication.getScene().setRoot(root);

    }
}
