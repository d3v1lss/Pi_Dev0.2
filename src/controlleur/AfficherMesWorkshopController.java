/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.workshop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import static org.apache.xalan.xsltc.compiler.util.Type.Int;
import services.GestionWorkshop;
import static services.GestionWorkshop.cnx;
import utils.DbConnexion;

/**
 * FXML Controller class
 *
 * @author HCHAICHI
 */
public class AfficherMesWorkshopController implements Initializable {

    private int selectIndex;
    Connection cnxC;
    @FXML
    private ListView<workshop> table;
    @FXML
    private JFXTextField search;

    @FXML
    private JFXTextField txt_nbr;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextArea txt_dis;

    public AfficherMesWorkshopController() throws SQLException {
        cnxC = DbConnexion.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            GestionWorkshop gs = new GestionWorkshop();

            table.getItems().setAll(gs.MesWorkshop());
            table.setCellFactory(lv -> new ListWorkshop());

            table.setOnMouseClicked(event -> {
                workshop w = table.getSelectionModel().getSelectedItem();
                selectIndex = table.getSelectionModel().getSelectedIndex();
                txt_nom.setText(w.getNom());
                txt_nbr.setText(w.getNombreplaces() + "");
                // datedebut.setDate(w.getDatedebut());
                // datefin.setText(dateFormat.format(w.getDatefin()));
                txt_dis.setText(w.getDiscription());

            });
        } catch (SQLException ex) {
            Logger.getLogger(AfficherMesWorkshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterworkshop(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterWorkshop.fxml"));
        Parent root;
        try {
            root = loader.load();
            table.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherMonClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        workshop w = new workshop();

        w.setNom(txt_nom.getText());
        w.setNombreplaces(Integer.parseInt(txt_nbr.getText()));
        w.setDiscription(txt_dis.getText());

        String req = "update `workshop` SET `nom`=?, `nombreplaces`=?,`discription`=? where nom ='" + txt_nom.getText() + "' ";

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, w.getNom());
            preparedStatement.setInt(2, w.getNombreplaces());
            preparedStatement.setString(3, w.getDiscription());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {

        String query = "DELETE FROM workshop where nom ='" + txt_nom.getText() + "' ";
        PreparedStatement pstm = cnxC.prepareStatement(query);
        pstm.executeUpdate();
        getAll(event);

    }

    public void getAll(ActionEvent event) throws SQLException {

        GestionWorkshop gs = new GestionWorkshop();

        table.getItems().setAll(gs.MesWorkshop());
        table.setCellFactory(lv -> new ListWorkshop());

        workshop d = table.getSelectionModel().getSelectedItem();
        selectIndex = table.getSelectionModel().getSelectedIndex();

    }
}
