/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.DivCommController;
import controlleur.DivPubmController;
import entities.Commentaire;
import entities.Forum;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import services.ServiceCommentaire;
import services.ServiceForum;
import services.ServicePublication;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class CommentaireController implements Initializable {

    private TextArea txt_Ajout;
    private ObservableList<Commentaire> data2;
    private ObservableList<Commentaire> data;
    @FXML
    private ScrollPane pane;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));

        ServiceCommentaire aff = new ServiceCommentaire();

        data = (ObservableList<Commentaire>) aff.getAllCommentaire();
        for (Commentaire d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("DivComm.fxml"));

                Parent root = (Pane) loader.load();

                DivCommController DHC = loader.getController();

                DHC.LoadValues(d);

                c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListPubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //   System.out.println("te5dem2");

        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);

    }

    public void affichePub() throws SQLException {

        ServiceCommentaire ps = new ServiceCommentaire();
        data2 = (ObservableList<Commentaire>) ps.getAllCommentaire();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Commentaire d : data2) {
            //  System.out.println(d.getPrix());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DivComm.fxml"));
                Parent root = (Pane) loader.load();
                DivCommController DHC = loader.getController();

                DHC.LoadValues(d);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListCommController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);

    }

    private void ajoutcomm(ActionEvent event) throws SQLException, IOException {

        ServiceCommentaire s = new ServiceCommentaire();

        Commentaire s1 = new Commentaire(txt_Ajout.getText());

        if ((txt_Ajout.getText().equals("")) && (txt_Ajout.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez remplir le nom.", ButtonType.OK);
            alert.showAndWait();

        } else if (txt_Ajout.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "veuillez entrer la description.", ButtonType.OK);
            alert.showAndWait();

        } else {
            s.AjouterCommentaire(s1);

        }
    }

    @FXML
    private void gotopubs(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/ListPublication.fxml"));
        retour.getScene().setRoot(root);

    }

}
