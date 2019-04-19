/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller0;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivPubmController implements Initializable {

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
    private Label id;
    @FXML
    private Label cat;
    @FXML
    private Label nomUser;
    @FXML
    private ImageView like;
    @FXML
    private Label etat;
    @FXML
    private Label labelDislike;
    @FXML
    private Label labelLIke;
    @FXML
    private ImageView dislike;
    @FXML
    private Button addComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoComment(MouseEvent event) {
    }
    
}
