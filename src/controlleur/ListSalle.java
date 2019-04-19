/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;


import entities.Salle;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author HCHAICHI
 */
public class ListSalle extends ListCell<Salle> {

    private final GridPane gridPane = new GridPane();

    private final Label nom = new Label();
    private final Label capacite = new Label();

    private final Label txt_capacite = new Label();
    private final Label txtNom = new Label();

    private final AnchorPane content = new AnchorPane();

    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getNom() {
        return nom;
    }

    public Label getCapacite() {
        return capacite;
    }

    public Label getTxt_capacite() {
        return txt_capacite;
    }

    public Label getTxtNom() {
        return txtNom;
    }

    public AnchorPane getContent() {
        return content;
    }

    public ListSalle() {

        GridPane.setConstraints(txtNom, 0, 0);
        GridPane.setConstraints(txt_capacite, 0, 1);

        txtNom.setText("Nom : ");
        txt_capacite.setText("capacite : ");

        GridPane.setConstraints(nom, 1, 0);
        GridPane.setConstraints(capacite, 1, 1);

        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));

        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

        gridPane.setHgap(3);
        gridPane.setVgap(3);

        gridPane.getChildren().setAll(txtNom, txt_capacite, nom, capacite);

        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);

        content.getChildren().add(gridPane);
    }

    protected void updateItem(Salle item, boolean empty) {

        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {

            nom.setText(String.valueOf(item.getNom()));
            capacite.setText(String.valueOf(item.getCapacite()));

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

    }
}
