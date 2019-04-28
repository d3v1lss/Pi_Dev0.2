/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import controlleur.DivForumController;
import entities.Forum;
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
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import services.ServiceForum;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class ListForumController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private JFXCheckBox checkprix;
    private ObservableList<Forum> data;
    private ObservableList<String> data2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));

            ServiceForum aff = new ServiceForum();

            data = aff.ListForum();
            for (Forum d : data) {

                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DivForum.fxml"));

                    Parent root = (Pane) loader.load();

                    DivForumController DHC = loader.getController();

                    DHC.LoadValues(d);

                    c.setVgap(40);
                    c.getChildren().removeAll();

                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListForumController.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (SQLException ex) {
            Logger.getLogger(ListForumController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {

        ServiceForum aff = new ServiceForum();
        data2 = aff.ListForumNom();
        data = aff.ListForum();
        ObservableList<Forum> myForum = FXCollections.observableArrayList();
        FilteredList<Forum> filteredData = new FilteredList<>(data, e -> true);

        TextFields.bindAutoCompletion(recherchetext, filteredData);
        recherchetext.setOnKeyReleased(e
                -> {
                    recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                        filteredData.setPredicate((Predicate<? super Forum>) new Predicate<Forum>() {
                            @Override
                            public boolean test(Forum d) {
                                if (newValue == null || newValue.isEmpty()) {

                                    return true;

                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                return d.getNom().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                            }
                        });
                    });
                    TilePane b = new TilePane();
                    b.setPadding(new javafx.geometry.Insets(30));
                    TilePane c = new TilePane();

                    for (Forum d : filteredData) {

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DivForum.fxml"));
                            Parent root = (Pane) loader.load();
                            DivForumController DHC = loader.getController();
                            DHC.LoadValues(d);

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

    @FXML
    private void refresh(MouseEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/views/ListForum.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
