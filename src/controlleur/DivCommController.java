/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import entities.Commentaire;
import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import services.ServiceCommentaire;
import services.ServicePublication;
import controlleur.ListPubController;

/**
 * FXML Controller class
 *
 * @author Vladio
 */
public class DivCommController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label contenu;
    @FXML
    private Label idpub;
    @FXML
    private Label id;
    @FXML
    private Button ecoutbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void LoadValues(Commentaire e) throws IOException {
        ServiceCommentaire ies = new ServiceCommentaire();

        idpub.setText(ies.retourCommNompub(e.getPublication_id()));
        contenu.setText(e.getContenu());

        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }

        }
        );

    }

    @FXML
    private void ecouter(ActionEvent event) {
        try {
            //  System.setProperty("mbrola.base", "C:\\Users\\DELL\\Desktop\\ESPRIT\\2eme semestre\\pi\\freetts-1.2\\mbrola/");
            System.setProperty("mbrola.base", "mbrola");
            Voice voice;
            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice("mbrola_us2");

            //voice=vm.getVoice(VOICENAME);
            voice.allocate();
            voice.speak(contenu.getText());
            Thread.sleep(5000);
            // label_description.setText("ecouter");
        } catch (InterruptedException ex) {
            Logger.getLogger(DivCommController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
