/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Produit;
import Entities.Tva;
import Services.ProduitService;
import Services.TvaService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nomProduit;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    @FXML
    private CheckBox disponible;
    @FXML
    private ImageView photo;
    @FXML
    private Button ajouterProduit;
    @FXML
    private ChoiceBox<Tva> tva;
    @FXML
    private Button annuler;
    @FXML
    private Button photoB;
    String image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Tva> tvas = new ArrayList<Tva>();
        TvaService tv = new TvaService();
        tv.getTva().forEach(x -> {
            tvas.add(x);
        });

        tva.setItems(FXCollections.observableArrayList(tvas));

    }

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {

        Produit p = new Produit(nomProduit.getText(), Double.valueOf(prix.getText()), description.getText(), tva.getSelectionModel().selectedItemProperty().get().getIdTva(), image, disponible.isSelected());
        ProduitService produitService = new ProduitService();
        produitService.insert(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherProduits.fxml"));
        Parent root;
        root = loader.load();
        ajouterProduit.getScene().setRoot(root);
        /*/
        Parent root=FXMLLoader.load(getClass().getResource("./List.fxml"));
        Scene scene =new  Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        //*/
        new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }

    @FXML
    private void Annuler() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherProduits.fxml"));
        Parent root;
        root = loader.load();
        annuler.getScene().setRoot(root);
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(annuler.getScene().getWindow());
        if (file != null) {
            openFile(file);
        }

        image = file.getName();
        HttpPost httpPost;
        try {
            httpPost = new HttpPost(new URL("http://127.0.0.1/upload.php"));
            httpPost.setFileNames(new String[]{file.toPath().toString()});
            httpPost.post();
            System.out.println("=======");
            System.out.println(httpPost.getOutput());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void openFile(File file) {
        FileInputStream input;
        try {
            File dest = new File("D:/wamp64/www/img/" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            String url = dest.toPath().toString();

            input = new FileInputStream(url);
            WritableImage photoProduit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            photo.setImage(photoProduit);
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(
                new File("C:\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.JPG"),
                new FileChooser.ExtensionFilter("JPEG", "*.JPEG"),
                new FileChooser.ExtensionFilter("PNG", "*.PNG")
        );
    }

}
