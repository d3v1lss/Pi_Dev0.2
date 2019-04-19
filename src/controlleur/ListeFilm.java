/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Film;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Houssem
 */
public class ListeFilm extends ListCell<Film> {

    private final GridPane gridPane = new GridPane();

    private final Label Nom = new Label();
    private final Label discription = new Label();
    private final Label duree = new Label();
    private final Label datesotie = new Label();
    //private final Label image = new Label();
    private final ImageView image = new ImageView();
    private final Label txtNom = new Label();
    private final Label txtdiscription = new Label();
    private final Label txtduree = new Label();
    private final Label txtdatesotie = new Label();
    private final Label txtimage = new Label();

    private final AnchorPane content = new AnchorPane();

    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getNom() {
        return Nom;
    }

    public Label getDiscription() {
        return discription;
    }

    public Label getDuree() {
        return duree;
    }

    public Label getDatesotie() {
        return datesotie;
    }

    public ImageView getImage() {
        return image;
    }

    public Label getTxtNom() {
        return txtNom;
    }

    public Label getTxtdiscription() {
        return txtdiscription;
    }

    public Label getTxtduree() {
        return txtduree;
    }

    public Label getTxtdatesotie() {
        return txtdatesotie;
    }

    public Label getTxtimage() {
        return txtimage;
    }

    @Override
    public String toString() {
        return "listeFilm{" + "gridPane=" + gridPane + ", Nom=" + Nom + ", discription=" + discription + ", duree=" + duree + ", datesotie=" + datesotie + ", image=" + image + ", txtNom=" + txtNom + ", txtdiscription=" + txtdiscription + ", txtduree=" + txtduree + ", txtdatesotie=" + txtdatesotie + ", txtimage=" + txtimage + '}';
    }

    public ListeFilm() {

        GridPane.setConstraints(txtimage, 0, 0);
        GridPane.setConstraints(txtNom, 0, 1);
        GridPane.setConstraints(txtduree, 0, 2);
        GridPane.setConstraints(txtdatesotie, 0, 3);
        GridPane.setConstraints(txtdiscription, 0, 4);

        txtimage.setText("image : ");
        txtNom.setText("Nom : ");
        txtduree.setText("discription : ");
        txtdatesotie.setText("duree : ");
        txtdiscription.setText("datesotie : ");

        GridPane.setConstraints(image, 1, 0);
        GridPane.setConstraints(Nom, 1, 1);
        GridPane.setConstraints(duree, 1, 2);
        GridPane.setConstraints(datesotie, 1, 3);
        GridPane.setConstraints(discription, 1, 4);

        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));

        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

        gridPane.setHgap(3);
        gridPane.setVgap(3);

        gridPane.getChildren().setAll(txtimage, txtNom, txtduree, txtdatesotie, txtdiscription, image, Nom, duree, datesotie, discription);

        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);

        content.getChildren().add(gridPane);

    }
    
    protected void updateItem(Film item, boolean empty) {

        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {

            Nom.setText(String.valueOf(item.getNom()));
            discription.setText(String.valueOf(item.getDiscription()));
            duree.setText(String.valueOf(item.getDuree()));
            datesotie.setText(String.valueOf(item.getDatesotie()));
            image.setFitWidth(50);
            image.setFitHeight(50);
            image.setImage(new Image("http://localhost/film/images/"+item.getImage())); 
            

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

    }

}
