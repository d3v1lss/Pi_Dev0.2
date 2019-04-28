/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import entities.Evenement;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import services.ServiceEvenement;
import services.ServiceReservation;

/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class ReservationCController implements Initializable {

    
    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label txt_eventname;
    @FXML
    private Label txtdate;
    @FXML
    private Label txtdatefin;
    @FXML
    private Label txt_description;
    @FXML
    private Label id;
  //  @FXML
  //  private Rectangle rectangle;
    @FXML
    private Label txt_nbPlaces;
    @FXML
    private Label txt_theme;
    
     String theme;
    int idEv;
    int user=1;
    int idEvenement;
    String nomEvenement;
  static  int nbPlaces;
    @FXML
    private JFXButton reserverbtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                
    }

    public void LoadValues(Reservation e) throws IOException {
                    ServiceReservation ies= new ServiceReservation();
                   // Evenement e = new Evenement(idEv, theme, theme, theme, nbPlaces, datedebut, datefin);

        //System.out.println("1="+e.getTheme_id());
      //  System.out.println("2="+ies.retourthemeNomevent(e.getTheme_id()));
     //   txt_theme.setText(ies.retourthemeNomevent(e.getTheme_id()));

         txt_eventname.setText(ies.retourEvnementNom(e.getEvenement_id()));
       //   System.out.println("temchi 1");

      //  Image imageURI2 = new Image("file:C:/wamp64/www/Pi_Dev0.1/web/img/uploads" + e.getPhoto());
      //  rectangle.setFill(new ImagePattern(imageURI2));
         txt_description.setText(ies.retourEvnementDiscription(e.getEvenement_id()));
    //      txt_nbPlaces.setText(String.valueOf(e.getNombreplaces()));
         txtdate.setText(SimpleDateFormat(ies.retourEvnementDateDebut(e.getEvenement_id())));
      //   txtdatefin.setText(SimpleDateFormat(e.getDatefin()));
             id.setText(String.valueOf(e.getId()));
           idEv=(ies.retourReservationIdD(e.getEvenement_id()));
          //   nomEvenement=ies.retourEvnementNom(e.getEvenement_id());
          //   nbPlaces=ies.countNombrePlace(e.getNom());
            // System.out.println("nombre de places "+nbPlaces);
        // System.out.println("temchi 2");
        

        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
          //  @Override
          //  public void handle(MouseEvent event) {
               // if (event.getClickCount() == 2) {
                 ///   try {
                 //       doubleclick(event, e);
                 //   } catch (SQLException ex) {
                  //      Logger.getLogger(Evt1Controller.class.getName()).log(Level.SEVERE, null, ex);
                  //  }
                }
          //  }
        );

    }
   
    private String SimpleDateFormat(java.sql.Date d) {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;    }


    @FXML
    private void annulerReservation(MouseEvent event) throws IOException {
      
        ServiceEvenement pst = new ServiceEvenement();
        ServiceReservation ps=new ServiceReservation();
        System.out.println("RESERVATION id = "+idEvenement);
          idEvenement=(ps.retourEvnement_ID(idEv));

        pst.incrémenterNBplaces(idEvenement);
        ps.supprimerR2(idEv);
      /*   Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("listeRerservation.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();*/
         Parent root =FXMLLoader.load(getClass().getResource("/views/listeRerservation.fxml"));
    }

}
