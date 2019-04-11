/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.club;
import entities.workshop;
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
public class ListWorkshop extends ListCell<workshop> {

    private final GridPane gridPane = new GridPane();

    private final Label user_id = new Label();
    private final Label club_id = new Label();
    private final Label nom = new Label();
    private final Label nombreplaces = new Label();
    private final Label datedebut = new Label();
    private final Label datefin = new Label();
    private final Label discription = new Label();

    private final Label txt_user_id = new Label();
    private final Label txt_club_id = new Label();
    private final Label txt_nom = new Label();
    private final Label txt_nombreplaces = new Label();
    private final Label txt_datedebut = new Label();
    private final Label txt_datefin = new Label();
    private final Label txt_discription = new Label();

    private final AnchorPane content = new AnchorPane();

    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getUser_id() {
        return user_id;
    }

    public Label getClub_id() {
        return club_id;
    }

    public Label getNom() {
        return nom;
    }

    public Label getNombreplaces() {
        return nombreplaces;
    }

    public Label getDatedebut() {
        return datedebut;
    }

    public Label getDatefin() {
        return datefin;
    }

    public Label getDiscription() {
        return discription;
    }

    public Label getTxt_user_id() {
        return txt_user_id;
    }

    public Label getTxt_club_id() {
        return txt_club_id;
    }

    public Label getTxt_nom() {
        return txt_nom;
    }

    public Label getTxt_nombreplaces() {
        return txt_nombreplaces;
    }

    public Label getTxt_datedebut() {
        return txt_datedebut;
    }

    public Label getTxt_datefin() {
        return txt_datefin;
    }

    public Label getTxt_discription() {
        return txt_discription;
    }

    public AnchorPane getContent() {
        return content;
    }

    public ListWorkshop() {

        GridPane.setConstraints(txt_user_id, 0, 0);
        GridPane.setConstraints(txt_club_id, 0, 1);
        GridPane.setConstraints(txt_nom, 0, 2);
        GridPane.setConstraints(txt_nombreplaces, 0, 3);
        GridPane.setConstraints(txt_datedebut, 0, 4);
        GridPane.setConstraints(txt_datefin, 0, 5);
        GridPane.setConstraints(txt_discription, 0, 6);

        txt_user_id.setText("user : ");
        txt_club_id.setText("club : ");
        txt_nom.setText("Nom : ");
        txt_nombreplaces.setText("Nbre : ");
        txt_datedebut.setText("date debut : ");
        txt_datefin.setText("date fin : ");
        txt_discription.setText("Description : ");

        GridPane.setConstraints(user_id, 1, 0);
        GridPane.setConstraints(club_id, 1, 1);
        GridPane.setConstraints(nom, 1, 2);
        GridPane.setConstraints(nombreplaces, 1, 3);
        GridPane.setConstraints(datedebut, 1, 4);
        GridPane.setConstraints(datefin, 1, 5);
        GridPane.setConstraints(discription, 1, 6);

        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));

        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

        gridPane.setHgap(3);
        gridPane.setVgap(3);

        gridPane.getChildren().setAll(
                txt_user_id, txt_club_id, txt_nom, txt_nombreplaces, txt_datedebut, txt_datefin, txt_discription,
                user_id, club_id, nom, nombreplaces, datedebut, datefin, discription);

        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);

        content.getChildren().add(gridPane);
    }

    protected void updateItem(workshop item, boolean empty) {

        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {

            user_id.setText(String.valueOf(item.getUser_id()));
            club_id.setText(String.valueOf(item.getClub_id()));
            nom.setText(String.valueOf(item.getNom()));
            nombreplaces.setText(String.valueOf(item.getNombreplaces()));
            datedebut.setText(String.valueOf(item.getDatedebut()));
            datefin.setText(String.valueOf(item.getDatefin()));
            discription.setText(String.valueOf(item.getDiscription()));

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

    }
}
