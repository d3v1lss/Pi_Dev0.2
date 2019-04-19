/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class StatistiquePController implements Initializable {

    @FXML
    private BarChart<String,Integer> StatBar;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button fermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       XYChart.Series set1 = new XYChart.Series<>();
        UserService us = new UserService();
        set1.getData().add(new XYChart.Data("January", us.StatsNbrUser(1)));
        set1.getData().add(new XYChart.Data("February", us.StatsNbrUser(2)));
        set1.getData().add(new XYChart.Data("March", us.StatsNbrUser(3)));
        set1.getData().add(new XYChart.Data("April", us.StatsNbrUser(4)));
        set1.getData().add(new XYChart.Data("May", us.StatsNbrUser(5)));
        set1.getData().add(new XYChart.Data("June", us.StatsNbrUser(6)));
        set1.getData().add(new XYChart.Data("July", us.StatsNbrUser(7)));
        set1.getData().add(new XYChart.Data("August", us.StatsNbrUser(8)));
        set1.getData().add(new XYChart.Data("September", us.StatsNbrUser(9)));
        set1.getData().add(new XYChart.Data("October", us.StatsNbrUser(10)));
        set1.getData().add(new XYChart.Data("November", us.StatsNbrUser(11)));
        set1.getData().add(new XYChart.Data("December", us.StatsNbrUser(12)));
        
        StatBar.getData().addAll(set1);
    }    

    @FXML
    private void fermer(ActionEvent event) {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();
    }
    
}
