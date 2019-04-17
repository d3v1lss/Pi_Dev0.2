/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXTextField;
import entities.club;
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
public class MonClub extends ListCell<club> {

    private final GridPane gridPane = new GridPane();

    private final JFXTextField description = new JFXTextField();
    private final JFXTextField Mail = new JFXTextField();
    private final JFXTextField Nom = new JFXTextField();
    private final JFXTextField Nbre = new JFXTextField();
    private final JFXTextField Status = new JFXTextField();
    private final JFXTextField Activite = new JFXTextField();
    private final JFXTextField descriptionLabel = new JFXTextField();

    private final Label txt_Mail = new Label();
    private final Label txt_Activite = new Label();
    private final Label txtNom = new Label();
    private final Label txt_Status = new Label();
    private final Label txt_desc = new Label();

    private final Label txt_NbrPlace = new Label();

    private final AnchorPane content = new AnchorPane();

    /**
     * public JFXTextField getNom() { return Nom; }
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    public JFXTextField getDescription() {
        return description;
    }

    public JFXTextField getMail() {
        return Mail;
    }

    public JFXTextField getNbre() {
        return Nbre;
    }

    public JFXTextField getStatus() {
        return Status;
    }

    public JFXTextField getActivite() {
        return Activite;
    }

    public JFXTextField getDescriptionLabel() {
        return descriptionLabel;
    }

    public Label getTxt_Mail() {
        return txt_Mail;
    }

    public Label getTxt_Activite() {
        return txt_Activite;
    }

    public Label getTxtNom() {
        return txtNom;
    }

    public Label getTxtStatus() {
        return txt_Status;
    }

    public Label getTxt_desc() {
        return txt_desc;
    }

    public Label getTxt_NbrPlace() {
        return txt_NbrPlace;
    }

    public AnchorPane getContent() {
        return content;
    }

    public MonClub() {
        /*  description.setStyle("-fx-font-style: italic; -fx-font-size: 0.9em; -fx-opacity: 0.5;-fx-border-style: none;"); */

        GridPane.setConstraints(txtNom, 0, 0);
        GridPane.setConstraints(txt_Mail, 0, 1);
        GridPane.setConstraints(txt_desc, 0, 2);
        GridPane.setConstraints(txt_NbrPlace, 0, 3);
        GridPane.setConstraints(txt_Activite, 0, 4);

        GridPane.setConstraints(txt_Status, 0, 5);
        txtNom.setText("Nom : ");
        txt_Mail.setText("Mail : ");
        txt_desc.setText("Description : ");
        txt_NbrPlace.setText("Nbre : ");
        txt_Activite.setText("Activite : ");

        txt_Status.setText("status : ");

        GridPane.setConstraints(Nom, 1, 0);
        GridPane.setConstraints(Mail, 1, 1);
        GridPane.setConstraints(description, 1, 2);
        GridPane.setConstraints(Nbre, 1, 3);
        GridPane.setConstraints(Activite, 1, 4);

        GridPane.setConstraints(Status, 1, 5);

        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));

        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

        gridPane.setHgap(3);
        gridPane.setVgap(3);

        gridPane.getChildren().setAll(txtNom, txt_Mail, txt_desc, txt_NbrPlace, txt_Activite, txt_Status, Nom, Mail, description, Nbre, Activite, Status);

        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);

        content.getChildren().add(gridPane);

    }

    protected void updateItem(club item, boolean empty) {

        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {

            Nom.setText(String.valueOf(item.getNom()));
            Mail.setText(String.valueOf(item.getMail()));
            description.setText(String.valueOf(item.getDiscription()));
            Nbre.setText(String.valueOf(item.getNbrparticipant()));
            Activite.setText(String.valueOf(item.getActivite()));

            Status.setText(String.valueOf(item.getStatut()));
            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

    }

}
