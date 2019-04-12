/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devils_0;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Info-Sys
 */
public class Devils_0 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherClub.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherSalle.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherMonClub.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/views/AjouterClub.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AjouterWorkshop.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherWorkshop.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherMesWorkshop.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/views/AccueilClub.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AjouterSalle.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CLUB");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
