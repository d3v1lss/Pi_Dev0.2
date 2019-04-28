/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

//import com.auth0.jwt.internal.org.bouncycastle.asn1.ASN1ObjectIdentifier;
//import static com.auth0.jwt.internal.org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import controlleur.DivCommController;
import controlleur.DivPubmController;
import entities.Commentaire;
import entities.Forum;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import services.ServiceCommentaire;
import services.ServiceForum;
import services.ServicePublication;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class ListCommController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private JFXCheckBox checkprix;

    private ObservableList<Commentaire> data;
    @FXML
    private JFXTextField recherchetext;
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
                Logger.getLogger(ListCommController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void RechercheDynamique(KeyEvent event) throws SQLException {

        ServiceCommentaire aff = new ServiceCommentaire();
        data = (ObservableList<Commentaire>) aff.getAllCommentaire();
        ObservableList<Commentaire> myCommentaire = FXCollections.observableArrayList();
        FilteredList<Commentaire> filteredData = new FilteredList<>(data, e -> true);

        TextFields.bindAutoCompletion(recherchetext, filteredData);
        recherchetext.setOnKeyReleased(e
                -> {
                    recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                        filteredData.setPredicate((Predicate<? super Commentaire>) new Predicate<Commentaire>() {
                            @Override
                            public boolean test(Commentaire d) {
                                if (newValue == null || newValue.isEmpty()) {

                                    return true;

                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                return d.getContenu().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                            }
                        });
                    });
                    TilePane b = new TilePane();
                    b.setPadding(new javafx.geometry.Insets(30));
                    TilePane c = new TilePane();

                    for (Commentaire d : filteredData) {

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DivCommm.fxml"));
                            Parent root = (Pane) loader.load();
                            controlleur.DivCommController DHC = loader.getController();
                            DHC.LoadValues(d);

                            //   c.setVgap(40);
                            c.getChildren().removeAll();

                            c.getChildren().add(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ListCommController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    c.setPrefColumns(2);
                    c.setPadding(new javafx.geometry.Insets(0));
                    c.setHgap(10);
                    c.setVgap(80);
                    b.getChildren().add(c);
                    b.setPrefWidth(1000);
                    pane.setContent(b);
                });
    }

    private void gotopubs(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/ListPublication.fxml"));
        retour.getScene().setRoot(root);

    }
}
