/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.workshop;
import services.GestionWorkshop;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.listeworkshop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import static services.GestionWorkshop.cnx;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherWorkshopsController implements Initializable {

    @FXML
    private JFXListView<workshop> table;
    @FXML
    private JFXTextField search;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXDatePicker debut;
    @FXML
    private JFXDatePicker fin;
    @FXML
    private JFXTextField text_id;
    @FXML
    private JFXTextField des;
    private int selectIndex;

    ListWorkshop l = new ListWorkshop();
    public static Connection cnx;

    public AfficherWorkshopsController() throws SQLException {
        cnx = DbConnexion.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            GestionWorkshop gw = new GestionWorkshop();

            table.getItems().setAll(gw.FetchAll());
            table.setCellFactory(lv -> new ListWorkshop());

            table.setOnMouseClicked(event -> {
                workshop w = table.getSelectionModel().getSelectedItem();
                selectIndex = table.getSelectionModel().getSelectedIndex();
                nom.setText(w.getNom());
                des.setText(w.getDiscription());
                nbr.setText(w.getNombreplaces() + "");
                int i = w.getNombreplaces();
                debut.setValue(LocalDate.parse(w.getDatedebut().toString()));
                fin.setValue(LocalDate.parse(w.getDatefin().toString()));
            });

        } catch (SQLException ex) {
            Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        search.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerWorkshop((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        if (search.getText() == null) {
            try {
                GestionWorkshop gw = new GestionWorkshop();

                l.getTxt_nom().setUserData(new PropertyValueFactory<>("nom"));

            } catch (SQLException ex) {
                Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        l.getTxt_nom().setUserData(TextFieldTableCell.forTableColumn());

    }

    @FXML
    private void inscrit(ActionEvent event) throws SQLException {

        String workshop = nom.getText();
        String user = "AKRAM";
        int i = Integer.parseInt(nbr.getText());
        listeworkshop lworkshop = new listeworkshop(workshop, user);

        GestionWorkshop gw = new GestionWorkshop();
        gw.inscrit(lworkshop);
        workshop w = new workshop();

        System.out.println(i);
        int y = i - 1;
        System.out.println(y);
        w.setNombreplaces(y);
        String req = "update `workshop` SET `nombreplaces`=?  where nom ='" + nom.getText() + "' ";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, w.getNombreplaces());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Alert(Alert.AlertType.INFORMATION, "inscrit fait").show();
    }

    @FXML
    private void retour(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherClubs.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherWorkshopsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Telecharger(ActionEvent event) throws SQLException {

        try {

            String nomf = "C:\\Users\\HCHAICHI\\Downloads\\PDFJAVA\\ListeWorkshops.pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(nomf));

            doc.open();
            Font R = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
            doc.add(new Paragraph(new java.util.Date().toGMTString(), R));
            Paragraph p10 = new Paragraph("\n");
            doc.add(p10);
            Font bold = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.BLUE);
            Font b = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Paragraph p1 = new Paragraph("LES WORKSHOPS :", b);
            doc.add(p1);
            Paragraph p0 = new Paragraph("\n");
            doc.add(p0);
            Statement stm = cnx.createStatement();
            String req = "SELECT * FROM workshop  ";
            ResultSet resultat = stm.executeQuery(req);
            while (resultat.next()) {
                Paragraph p2 = new Paragraph("Nom:" + resultat.getString("nom"), bold);
                doc.add(p2);
                Paragraph p3 = new Paragraph("nombre places:" + resultat.getInt("nombreplaces"), bold);
                doc.add(p3);
                Paragraph p4 = new Paragraph("date debut:" + resultat.getDate("datedebut") + "", bold);
                doc.add(p4);
                Paragraph p5 = new Paragraph("date fin:" + resultat.getDate("datefin") + "", bold);
                doc.add(p5);
                Paragraph p6 = new Paragraph("description:" + resultat.getString("discription"), bold);
                doc.add(p6);
                Paragraph p7 = new Paragraph("\n");
                doc.add(p7);

            }

            System.out.println("test");
            doc.close();

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void filtrerWorkshop(String oldValue, String newValue) throws SQLException {
        GestionWorkshop gw = new GestionWorkshop();

        ObservableList<workshop> workshop = FXCollections.observableArrayList(gw.FetchAll());
        ObservableList<workshop> filteredList = FXCollections.observableArrayList();

        if (search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(workshop);
        } else {
            newValue = newValue.toUpperCase();
            for (workshop w : table.getItems()) {
                String nom = w.getNom();

                if (nom.toUpperCase().contains(newValue)) {
                    filteredList.add(w);
                }
            }
            table.setItems(filteredList);
        }
    }

}
