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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import services.ServiceEvenement;
import services.ServiceReservation;
import services.ServiceTheme;


/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class Evt1_cientController implements Initializable {



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
    
    @FXML
    private Rectangle rectangle;
     String theme;
    int idEv;
    int user=1;
    int nbPlaces;
    @FXML
    private JFXButton reserverbtn;
    @FXML
    private ImageView imageEvnt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                
    }

    public void LoadValues(Evenement e) throws IOException {
                    ServiceEvenement ies= new ServiceEvenement();

        System.out.println("1="+e.getTheme_id());
        System.out.println("2="+ies.retourthemeNomevent(e.getTheme_id()));
        txt_theme.setText(ies.retourthemeNomevent(e.getTheme_id()));

         txt_eventname.setText(e.getNom());
       //   System.out.println("temchi 1");

      //  Image imageURI2 = new Image("file:C:/wamp64/www/Pi_Dev0.1/web/img/uploads" + e.getPhoto());
      //  rectangle.setFill(new ImagePattern(imageURI2));
          imageEvnt.setImage(new Image("http://localhost/Pi_Dev0.1/web/img/uploads/" + e.getPhoto()));

         txt_description.setText(e.getDiscription());
          txt_nbPlaces.setText(String.valueOf(e.getNombreplaces()));
         txtdate.setText(SimpleDateFormat(e.getDatedebut()));
         txtdatefin.setText(SimpleDateFormat(e.getDatefin()));
             id.setText(String.valueOf(e.getId()));
             idEv=(ies.retourEvId(e.getNom()));
             nbPlaces=ies.countNombrePlace(e.getNom());
             System.out.println("nombre de places "+nbPlaces);
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
    private void supprimerEvenement(MouseEvent event) throws IOException, SQLException {
        ServiceEvenement ps=new ServiceEvenement();
        System.out.println(idEv);
        ps.supprimerEv2(idEv);
       /* Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();*/
        Parent root =FXMLLoader.load(getClass().getResource("/views/ListEvenementclient.fxml"));
    }
   

    private String SimpleDateFormat(java.sql.Date d) {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;    }
/*
    private void reserverUnePlace(MouseEvent event) throws IOException {
        Reservation r = new Reservation(user, idEv);
        ServiceReservation ps = new ServiceReservation();
         System.out.println(idEv);
       ps.ajouterReservation(r);
       Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("reservationEcoute.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();*/
       /*
         Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();*/
    

    
   /* public int recupererIdEv(){
    
       // int id = idEv;
        
        if(reserverbtn.onActionProperty()){
        } else {
            
            
        }
)
        
        return idEv;
    }
*/
    @FXML
    private void reserverUnePlace(ActionEvent event) throws IOException {
      int idEvv= 0;
    

        Reservation r = new Reservation(user, idEv);
        ServiceReservation ps = new ServiceReservation();
        ServiceEvenement pst = new ServiceEvenement();
         System.out.println(idEv);
      
     idEvv= ps.verificationReservationExiste(user);
      //  System.out.println("idEvvv ="+idEvv);
       if(nbPlaces==0){
            Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("reservationPasDePlaces.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
       }
      else if (idEvv==idEv){
              Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("reservationExiste.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
           
       }
       else {
            ps.ajouterReservation(r);
           pst.décrémenterNBplaces(idEv);
         Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("reservationEcoute.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
       }
     //  pst.countNombrePlace();
       // partie mte3 if nb places <0 System.out.println("nombre de places 9adech +"+nbPlaces);
       //if evenement id = evenement id where user id
       // if verificationReservationExiste(user_id)=IdEv
     
    }

   

   

  
}


