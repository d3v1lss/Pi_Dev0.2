/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivProduitController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
