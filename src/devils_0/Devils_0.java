/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devils_0;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sevices.SalleServices;
import entities.Salle;

/**
 *
 * @author Info-Sys
 */
public class Devils_0 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        SalleServices s = new SalleServices();
        
        try {
            Salle salle = new Salle(254, "salle3");
            //productService.ajouterProduit(p);
            s.ajouterSalle(salle);
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

ex.printStackTrace();        }
    }
    
}
