/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivForumController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

       public void LoadValues(Forum e) throws IOException {
                    ServiceForum ies= new ServiceForum();

        System.out.println("1="+e.getId());
        System.out.println("2="+e.getNom());
        discriptionF.setText((e.getDiscription()));

       
        
       //   System.out.println("temchi 1");

      //  Image imageURI2 = new Image("file:C:/wamp64/www/Pi_Dev0.1/web/img/uploads" + e.getPhoto());
      //  rectangle.setFill(new ImagePattern(imageURI2));
         nomF.setText(e.getNom());
          discriptionF.setText(String.valueOf(e.getDiscription()));
      
             id.setText(String.valueOf(e.getId()));
            
        // System.out.println("temchi 2");
        idForum = ies.retourforumId(e.getNom());
           System.out.println("hedha idd forum"+idForum);
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
    private void affichpub(ActionEvent event) throws IOException {
         //int idEvv= 0;
    

        Publication r = new Publication();
        ServicePublication ps = new ServicePublication();
        ServiceForum pst = new ServiceForum();
         System.out.println(idForum);
         FXMLLoader Loder =new FXMLLoader();

                  Loder.setLocation(getClass().getResource("/views/ListPublication.fxml"));
                 
                  Loder.load();
                  DivPubmController display = Loder.getController();
               
                 // txt_eventname.setText(e.getNom())
               
           
                  Parent AnchorPane = Loder.getRoot();
                   Stage stage = new Stage();
                  Scene scene = new Scene(AnchorPane);
                  stage.setScene(scene);
                  stage.show();
     
       }
     //  pst.countNombrePlace();
       // partie mte3 if nb places <0 System.out.println("nombre de places 9adech +"+nbPlaces);
       //if evenement id = evenement id where user id
       // if verificationReservationExiste(user_id)=IdEv
     
    }
    
        
  
     
    


