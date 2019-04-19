/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import services.ServiceCommentaire;

import services.ServicePublication;


/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivPubmController implements Initializable {

  public static Publication Sn;
    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label titre;
    @FXML
    private Label contenu;
    @FXML
    private Label idforum;
    @FXML
    private Label nomUser;
    @FXML
    private Label cat;
    @FXML
    private Label id;
    @FXML
    private Button addComment;
    @FXML
    private ImageView like;
    @FXML
    private Label etat;
    @FXML
    private Label labelDislike;
    @FXML
    private ImageView dislike;
    @FXML
    private Label labelLIke;
    int idForum;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


 public void LoadValues(Publication e) throws IOException {
                    ServicePublication ies= new ServicePublication();

        idforum.setText(ies.retourforumNompub(idForum));

         titre.setText(e.getTitre());
    
         contenu.setText(e.getContenu());
         nomUser.setText(e.getNom());
         cat.setText(e.getNomForum());
          
          
  
        

        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
      
                }
          
        );

    }    
  



    @FXML
    private void gotoComment(MouseEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/views/commentaire.fxml"));
        addComment.getScene().setRoot(root);
        
    }

}
