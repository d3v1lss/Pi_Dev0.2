/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import services.ServiceEvenement;
import services.ServiceReservation;

/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class ListeRerservationController implements Initializable {
  @FXML
    private AnchorPane ap;
   // @FXML
 //   private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
    private JFXComboBox tri;

    private ObservableList<Reservation> data;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private Label total;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     /*   
        try {
            AnchorPane anchrone = FXMLLoader.load(getClass().getResource("/vue/test.fxml"));
            menu.setSidePane(anchrone);

            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(affmenu);
            burgerTask2.setRate(-1);

            affmenu.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();

             /*   if (menu.isShown()) {
                    menu.close();
                } else {
                    menu.open();
                    menu.toFront();
                }*/
          //  }
          //  );
            
       /* } catch (IOException ex) {
            Logger.getLogger(ListEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/

        try {
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));

            ServiceReservation aff = new ServiceReservation();

        //     total.setText(Integer.toString(aff.countEvenement()));
          
                data= aff.afficherTTreservation();
            for (Reservation d : data) {

                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("reservationC.fxml"));
                     
                    Parent root = (Pane) loader.load();

                    ReservationCController DHC = loader.getController();

                   DHC.LoadValues(d);

                      c.setVgap(40);
                    c.getChildren().removeAll();

                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListeRerservationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            c.setPrefColumns(3);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(1000);
            pane.setContent(b);

        } catch (SQLException ex) {
            Logger.getLogger(ListeRerservationController.class.getName()).log(Level.SEVERE, null, ex);
        
      } catch (ParseException ex) {
          Logger.getLogger(ListeRerservationController.class.getName()).log(Level.SEVERE, null, ex);
      }

    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {
    }
}
