/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.User;
import Services.UserService;
import static UI.PanierController.totalPanier;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField ccNumber;
    private TextField nbScoin;
    @FXML
    private TextField annee;
    @FXML
    private TextField mois;
    @FXML
    private TextField montant;
    private User user;
    private String cc="";
    @FXML
    private Label erreurDonnees;
    @FXML
    private Label erreurCC;
    @FXML
    private Label erreurMois;
    @FXML
    private Label erreurAnnee;
    @FXML
    private Label erreurCCLength;
    private Label erreurScoin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            refresh();
        });
        montant.setText(String.valueOf(totalPanier) + " DT");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void payerAction(ActionEvent event) {
       
        Stripe.apiKey = "sk_test_vkapn9Ybw8H6MzVMJvy3G6ks004pcxqXhB";
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount",(int) Math.round(totalPanier)*100);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();
        cardMap.put("number", ccNumber.getText());
        cardMap.put("exp_month", Integer.parseInt(mois.getText()));
        cardMap.put("exp_year", Integer.parseInt(annee.getText()));
        chargeMap.put("card", cardMap);
        try {
            Charge charge = Charge.create(chargeMap);
            Button payer=(Button) event.getSource();
            payer.setDisable(true);
            if (charge.getStatus().equals("succeeded"))
            {
                UserService us=new UserService();
                payer.setDisable(false);
                quitterAction(event);
            }
        } catch (StripeException e) {
            erreurDonnees.setText("Vérifiez vos données");
            Button payer=(Button) event.getSource();
            payer.setDisable(false);
        }
    }

    @FXML
    private void quitterAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.close();
    }

    private void verifier(TextField tf) {
        String val = tf.getText();
        System.out.println(val);
        String newVal = "";
        for (char ch : val.toCharArray()) {
            if (Character.isDigit(ch)) {
                newVal += ch;
            }
        }
        tf.setText(newVal);
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
        else
        {
            if (tf.getId().equals("ccNumber") && tf.getText().length()==16) 
            {
                event.consume();
                erreurCCLength.setVisible(true);
            }
            else if (tf.getId().equals("mois") && tf.getText().length()==2) 
            {
                event.consume();
                erreurMois.setVisible(true);
            }
            else if (tf.getId().equals("annee") && tf.getText().length()==4) 
            {
                event.consume();
                erreurAnnee.setVisible(true);
            }
        }
        //verifier((TextField) event.getSource());
    }

    private void updateMontant(KeyEvent event) {
        montant.setText(String.valueOf(totalPanier) + " DT");
    }

    private void formatCCAction(KeyEvent event) {
        TextField tf=(TextField) event.getSource();
        System.out.println(tf.getText().length());
        if (tf.getText().length()>16) 
        {
            event.consume();
        }
    }
    
    private void refresh()
    {
        erreurCC.setVisible(false);
        erreurCCLength.setVisible(false);
        erreurMois.setVisible(false);
//        erreurScoin.setVisible(false);
        erreurAnnee.setVisible(false);
    }
    
    private void verifierAction(ActionEvent event)
    {
        boolean erreur=false;
        refresh();
        if (ccNumber.getText().length()<16) 
        {
            erreurCC.setVisible(true);
            erreur=true;
        }
       
        if (mois.getText().length()<1) 
        {
            erreurMois.setVisible(true);
            erreur=true;
        }
        if (annee.getText().length()<1) 
        {
            erreurAnnee.setVisible(true);
            erreur=true;
        }
        if (!erreur) payerAction(event);
        
    }


}
