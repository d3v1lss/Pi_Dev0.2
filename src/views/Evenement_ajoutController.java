/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import services.ServiceEvenement;
import services.ServiceTheme;
import static views.Event_UpdateController.saveToFileImageNormal;



/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class Evenement_ajoutController implements Initializable {

    @FXML
    private DatePicker txtdate;
    @FXML
    private JFXTextField txt_eventname;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXButton btinsertimage;
    @FXML
    private JFXComboBox<String> listTheme;
    ObservableList<String> data1 = FXCollections.observableArrayList();
    @FXML
    private DatePicker txtdatefin;
    @FXML
    private JFXTextField txt_nbPlaces;

     // String imgName  ;
     static String getImageUrl;
     int user=1;
     int matricule;
    @FXML
    private ImageView imagee;
    @FXML
    private JFXButton addEvButton;
    int testDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceTheme theme = new ServiceTheme();
        data1 = FXCollections.observableArrayList(theme.retourNomTheme());
       
        listTheme.getItems().addAll(data1);
    }    
    

       // d.setId_demande(Integer.valueOf(id_dem_entry.getText()));

    

   /* private void ajouterrEvenement(MouseEvent event) {

        Evenement d = new Evenement();
        ServiceEvenement des = new ServiceEvenement();
       // d.setUser_id(user);
       // d.setTheme_id(listTheme.getValue());
        d.setNom(txt_eventname.getText());
        //d.setImage(imgName);
        d.setDiscription(txt_description.getText());
        d.setNombreplaces(Integer.valueOf(txt_nbPlaces.getText()));
     //   LocalDate db = txtdate.getValue();
     //   d.setDatedebut(java.sql.Date.valueOf(db));
      //  LocalDate db1 = txtdatefin.getValue();
      //  d.setDatedebut(java.sql.Date.valueOf(db));
        des.addEvent(d);

    }*/
    
   

  //  @FXML
    private void ajouteer101Evenement(ActionEvent event) throws IOException, ParseException {
 { 
     
     ServiceEvenement ies = new ServiceEvenement(); /* if (!txt_eventname.getText().equals("") && !txt_description.getText().equals("")
            && !txtdate.getValue().equals("") &&!txtdatefin.getValue().equals("") && !imgName.equals(""))*/
     Image image1 = imagee.getImage();
     String nameImage1 = saveToFileImageNormal(image1);
     String  nompep=listTheme.getValue();
     //  lnompep.setText(nompep);
     matricule = ies.retourthemeId(nompep);
     Evenement e = new Evenement(
             
             user,
             matricule,
             txt_eventname.getText(),
             nameImage1,
             txt_description.getText(),
             
             Integer.valueOf(txt_nbPlaces.getText()),
                java.sql.Date.valueOf(txtdate.getValue()),
                  java.sql.Date.valueOf(txtdatefin.getValue())
             
             
     );
            testDate= testdateDEB_dateFIN(SimpleDateFormat(java.sql.Date.valueOf(txtdate.getValue())),SimpleDateFormat(  java.sql.Date.valueOf(txtdatefin.getValue())));
             //   {
             if(testDate<0){
                      ies.addEvent(e);
            //    }
   //  ies.addEvent(e);
     System.out.println("ajouter1");}
           //  }
             /*else  if(testDate<0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("vérifier la date !");
            alert.setHeaderText("la date de début doit être inférieure ou égale à la date de fin");
            alert.setContentText(" l'ajout a échoué");

            alert.showAndWait();}*/
     
     //  afficher();
                
//             SendMail.sendmail(TableEvent.getSelectionModel().getSelectedItem().getUser().getMail(),
//                    "ajout evenement", "votre evenement a été crée, nom evenement: " + e.toString());
/*} else {
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("erreur champs vides");
alert.setHeaderText("il ya des champs vides");
Optional<ButtonType> result = alert.showAndWait();
}*/

    }
   Parent root =FXMLLoader.load(getClass().getResource("/views/ListEvenement.fxml"));
    
    
    }
  
  
@FXML   
private void ajouter101Evenement(ActionEvent event) throws SQLException, IOException, ParseException {
     /*   if (txt_eventname.getText().equals("")) {
            labeltitre.setText("Veuillez insérer un nom");
        } else if (txt_description.getText().equals("")) {
            labelquantite.setText("Veuillez indiquer une quantité");
        } else if (Integer.parseInt(txt_nbPlaces.getText()) <= 0) {
            labelquantite.setText("Veuillez indiquer une valeur positive");
        } else if (txtprix.getText().equals("")) {
            labelprix.setText("Veuillez choisir un prix");
        } else if (txtcat.getText().equals("")) {
            labelcategorie.setText("Veuillez choisir une catégorie");
        } else if (imagee.getImage() == null) {
            labelimage.setText("Veuillez choisir une image");
        } else {*/
          //  int user = user;
          ServiceEvenement ies = new ServiceEvenement();
             String  nompep=listTheme.getValue();
               matricule = ies.retourthemeId(nompep);
               int idtheme = (matricule);
            
            String nomEvnement = txt_eventname.getText();
               Image image1 = imagee.getImage();
            String nameImage1 = saveToFileImageNormal(image1);
                String discription = txt_description.getText();
            String nombreDePlaces = txt_nbPlaces.getText();
            int iqte = Integer.parseInt(nombreDePlaces);
               Date dateDebut =java.sql.Date.valueOf(txtdate.getValue());
               Date datefin =java.sql.Date.valueOf(txtdatefin.getValue());
            Evenement a = new Evenement();
            a.setUser_id(user);
            a.setTheme_id(idtheme);
            a.setNom(nomEvnement);
            a.setPhoto(nameImage1);
            a.setDiscription(discription);
            a.setNombreplaces(iqte);
            a.setDatedebut(dateDebut);
            a.setDatefin(datefin);
            
           // a.setCategorie(categorie);
           /* sendsms(titre_article);
            TrayNotification tray = new TrayNotification("Information", "Produit Ajouté", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));
            ProduitService af = new ProduitService();*/
           
            testDate= testdateDEB_dateFIN(SimpleDateFormat(java.sql.Date.valueOf(txtdate.getValue())),SimpleDateFormat(  java.sql.Date.valueOf(txtdatefin.getValue())));
             //   {
             if(testDate<0){
                   //   ies.addEvent(e);
            //    }
   //  ies.addEvent(e);
    ies.ajouterProduit(a);
     System.out.println("ajouter1");}
           
              Stage stage=new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
        
        Scene scene = new Scene(root);
    
        
        stage.setScene(scene);
        stage.show();
        }
    


        
        public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/Pi_Dev0.1/web/img/uploads");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
@FXML
        private void insert_image(ActionEvent event) throws IOException {

          FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagee.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
        
       


    public int testdateDEB_dateFIN(String d1, String d2) throws ParseException {
        java.util.Date dateEMB = convert(d1);
        java.util.Date dateEXP = convert(d2);
        return dateEMB.compareTo(dateEXP);
    }

    public java.util.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);

        return date1;
    }
     private String SimpleDateFormat(java.sql.Date d) {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;    }
  }
    
    


