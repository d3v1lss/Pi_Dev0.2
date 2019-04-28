/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

//import static com.auth0.jwt.internal.org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import controlleur.DivPubmController;
import entities.Commentaire;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import services.ServicePublication;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class ListPubController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    private ObservableList<Publication> data;
    private ObservableList<Publication> data2;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private JFXCheckBox mypub;
    int idForum;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    public void showinItems(int id) {

        idForum = id;
        System.out.println("bibi comm" + idForum);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));

        ServicePublication aff = new ServicePublication();

        data = (ObservableList<Publication>) aff.getAllpublications();
        for (Publication d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("DivPublication.fxml"));

                Parent root = (Pane) loader.load();

                DivPubmController DHC = loader.getController();

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

    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {

        ServicePublication aff = new ServicePublication();
        //    data = (ObservableList<Publication>) aff.getAllpublications();
        data = (ObservableList<Publication>) aff.getAllpublications();

        ObservableList<Publication> myPublication = FXCollections.observableArrayList();
        FilteredList<Publication> filteredData = new FilteredList<>(data, e -> true);

        recherchetext.setOnKeyReleased(e
                -> {
                    recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                        filteredData.setPredicate((Predicate<? super Publication>) new Predicate<Publication>() {
                            @Override
                            public boolean test(Publication p) {
                                if (newValue == null || newValue.isEmpty()) {

                                    return true;

                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                return p.getContenu().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                            }
                        });
                    });
                    TilePane b = new TilePane();
                    b.setPadding(new javafx.geometry.Insets(30));
                    TilePane c = new TilePane();

                    for (Publication p : filteredData) {

                        try {
                            TextFields.bindAutoCompletion(recherchetext, p.getNom());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DivPublication.fxml"));

                            Parent root = (Pane) loader.load();

                            DivPubmController DHC = loader.getController();

                            DHC.LoadValues(p);

                            //   c.setVgap(40);
                            c.getChildren().removeAll();

                            c.getChildren().add(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ListForumController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void affichePub() throws SQLException {

        ServicePublication ps = new ServicePublication();
        data2 = (ObservableList<Publication>) ps.getAllpublicationsUser();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Publication d : data2) {
            //  System.out.println(d.getPrix());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DivPublication.fxml"));
                Parent root = (Pane) loader.load();
                DivPubmController DHC = loader.getController();

                DHC.LoadValues(d);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListPubController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void afficheMyPub(MouseEvent event) throws SQLException {

        mypub.setSelected(true);
        affichePub();

    }

    @FXML
    private void gotoforums(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/ListForum.fxml"));
        retour.getScene().setRoot(root);

    }

}
