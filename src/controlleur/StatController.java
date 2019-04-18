/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class StatController implements Initializable {
Connection cnx;
    @FXML
    private BarChart<String,Integer> bar_chart;
 
    
    private void LoadChart(ActionEvent event) throws SQLException {
        String query="SELECT avischoix, COUNT(*) FROM avis GROUP BY id  ";
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        ResultSet rs=cnx.createStatement().executeQuery(query);
        while (rs.next())
                    {            
                           series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                          bar_chart.getData().add(series);
                    }
        
    
    
    }
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
