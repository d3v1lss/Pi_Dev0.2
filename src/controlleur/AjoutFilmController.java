/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Film;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import services.FilmServices;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class AjoutFilmController implements Initializable {

    /**
     * Initializes the controller class.
     */
        Connection cnx;
    @FXML
    private ImageView pic1;
    @FXML
    private TextField txt_Nom;
    @FXML
    private TextField txt_duree;
    @FXML
    private DatePicker txt_dateP;
    @FXML
    private TextArea txt_desc;
    public AjoutFilmController() {
        cnx = DbConnexion.getInstance().getConnection();
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
     public static String saveToFileImageNormal(Image image)throws SQLException, IOException  {

      String ext = "jpg";
        File dir = new File("C:/wamp64/www/Film/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
       ImageIO.write(bImage, "png", outputFile);
        return name;
    }
   
    
    @FXML
    private void addImage(MouseEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void AddMovies(ActionEvent event) throws SQLException, IOException {
        Image image1 = pic1.getImage();
              
                     String nameImage1 = saveToFileImageNormal(image1);
                     String nom= txt_Nom.getText();
                     String duree= txt_duree.getText();
                     String description= txt_desc.getText();
                    
                     java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(txt_dateP.getValue());
                     
                     
                     Film F =new Film(nom, description, duree, gettedDatePickerDate,nameImage1);
                     
                     FilmServices Fs =new FilmServices();
                       Fs.ajouterFilm(F);
    }
}
