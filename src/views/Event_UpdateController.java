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
import java.awt.BorderLayout;
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
import static views.Evenement_ajoutController.saveToFileImageNormal;

/**
 * FXML Controller class
 *
 * @author Info-Sys
 */
public class Event_UpdateController implements Initializable {

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
    private JFXTextField txt_nbPlaces;

      String imgName  ;
     static String getImageUrl;
     int user=1;
     int matricule;
    @FXML
    private ImageView imagee;
    @FXML
    private JFXButton updateEvButton;
    int idEv;
 //   int nombreDeplaces;
    @FXML
    private ImageView imagee1;


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
    
    
    
        public void showinItems(int id,String nomEvenement, String dsc, int nb){
 
        idEv=id;
        this.txt_eventname.setText(nomEvenement);
                this.txt_description.setText(dsc);
              //  this.txt_nbPlaces.setText(nb);
           txt_nbPlaces.setText(String.valueOf(nb));
      // java.sql.Date.valueOf(txtdatefin.setValue(SimpleDateFormat(db)));
          
           
//    txtdate.setValue(SimpleDateFormat(db));
      //  Date DFf = java.sql.Date.valueOf(txtdatefin.setValue(SimpleDateFormat(DF)));

               //      Integer.valueOf(txt_nbPlaces.getText()),

         //      this.Integer.valueOf(txt_nbPlaces.setText(nb);
            //   String nombreDePlaces = txt_nbPlaces.getText();
         //   int iqte = Integer.parseInt(nombreDePlaces);
         //  nb= Integer.parseInt(txt_nbPlaces.getText());
       // (this.txt_nbPlaces.getText(Integer.valueOf(nb));
     //   this.txt_description.setText(discription);
      //  txt_nbPlaces.setText(String.valueOf(nombreDeplaces));
            System.out.println("champ id recuperer="+idEv);
               
              
         
    //        return idEv;
        
    }

      /*  public int recupererId(int id)
        {showinItems(id);
            
            
            return id;
        }
  */
   // @FXML
        /*
    private void modifierbdateEvenement(ActionEvent event) throws ParseException {
        ServiceEvenement cons = new  ServiceEvenement();

                String  nompep=listTheme.getValue();
                
                //  lnompep.setText(nompep);
              //  matricule = cons.retourthemeId(nompep);
                Evenement c1 = new Evenement(
                        
                        
                      //  matricule,
                        txt_eventname.getText(),
                        txt_description.getText(),
                        
                        Integer.valueOf(txt_nbPlaces.getText()),
                        java.sql.Date.valueOf(txtdate.getValue()),
                        java.sql.Date.valueOf(txtdatefin.getValue()));

        cons.modifierEvenement(idEv,c1.getNom(),c1.getDiscription(),c1.getNombreplaces(),c1.getDatedebut(),c1.getDatefin());
    }*/
    @FXML
    private void modifierEvenement(ActionEvent event) throws ParseException, IOException {
        ServiceEvenement cons = new  ServiceEvenement();

                String  nompep=listTheme.getValue();
                Image image1 = imagee.getImage();
     String nameImage1 = saveToFileImageNormal(image1);
     /*String*/  nompep=listTheme.getValue();
     
     matricule = cons.retourthemeId(nompep);
                
                //  lnompep.setText(nompep);
              //  matricule = cons.retourthemeId(nompep);
                Evenement c1 = new Evenement(
                        idEv,
                        matricule,
                      //  matricule,
                        txt_eventname.getText(),
                        nameImage1,
                        txt_description.getText(),
                     //  Integer.valueOf(txt_nbPlaces.getText()),

                        
                        Integer.valueOf(txt_nbPlaces.getText()));
                
                       // java.sql.Date.valueOf(txtdate.getValue()),
                       // java.sql.Date.valueOf(txtdatefin.getValue()));

        cons.modifierbladateEvenement(idEv,c1.getTheme_id(),c1.getNom(),c1.getPhoto(),c1.getDiscription(),c1.getNombreplaces());
       // System.out.println("brabi chnia l id"+idEv);
                         // Parent root =FXMLLoader.load(getClass().getResource("/views/ListEvenement.fxml"));

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

       
  }