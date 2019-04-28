/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import services.ServiceTheme;

/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class Evt1Controller implements Initializable {

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
    @FXML
    private Button supprimerbtn;
    int idEv;
    int user=1;
    @FXML
    private JFXButton modifierbtn1;
        private ObservableList<Evenement> data1;
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
         
       System.out.println("nomphoto ="+e.getPhoto());
       
          imageEvnt.setImage(new Image("http://localhost/Pi_Dev0.1/web/img/uploads/" + e.getPhoto()));
       // Image imageURI2 = new Image("http://localhost/Pi_Dev0.1/web/img/uploads" + e.getPhoto());
     //  rectangle.setFill(new ImagePattern(imageURI2));
         txt_description.setText(e.getDiscription());
          txt_nbPlaces.setText(String.valueOf(e.getNombreplaces()));
         txtdate.setText(SimpleDateFormat(e.getDatedebut()));
         txtdatefin.setText(SimpleDateFormat(e.getDatefin()));
             id.setText(String.valueOf(e.getId()));
             idEv=(ies.retourEvId(e.getNom()));
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
 @FXML
    private void supprimerEvenement(MouseEvent event) throws IOException, SQLException {
        ServiceEvenement ps=new ServiceEvenement();
        System.out.println(idEv);
        ps.supprimerEv2(idEv);
         Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
        
    }
   

    private String SimpleDateFormat(java.sql.Date d) {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;    }

    @FXML
    private void modifierEvenement(MouseEvent event) throws IOException {
        
        ServiceEvenement ps=new ServiceEvenement();
        System.out.println(idEv);
      
                  FXMLLoader Loder =new FXMLLoader();

                  Loder.setLocation(getClass().getResource("Event_Update.fxml"));
                 
                  Loder.load();
                  Event_UpdateController display = Loder.getController();
                  System.out.println("yakra lena");
                 // txt_eventname.setText(e.getNom())
                  display.showinItems(idEv, ps.retourNomEvent(idEv),ps.retourdsc(idEv),ps.retourNombreDePlaceEvent(idEv));
                 System.out.println("khde l id");
                  Parent AnchorPane = Loder.getRoot();
                   Stage stage = new Stage();
                  Scene scene = new Scene(AnchorPane);
                  stage.setScene(scene);
                  stage.show();
                /*           
        System.out.println(idEv);
         Stage stage=new Stage();
        FXMLLoader Loder =new FXMLLoader();
          Parent root = FXMLLoader.load(getClass().getResource("Event_Update.fxml"));

          Event_UpdateController display = Loder.getController();
                 display.showinItems(idEv);
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();*/
    }
/*
     private void modif2Evenement(MouseEvent event) throws IOException, SQLException {
        ServiceEvenement ps=new ServiceEvenement();
        System.out.println(idEv);
        ps.supprimerEv2(idEv);
         Stage stage=new Stage();
         FXMLLoader Loder =new FXMLLoader();

          Event_UpdateController display = Loder.getController();
                  display.showinItems(idEv,idEv);
          Parent root = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
    }
*/
   

  
}
