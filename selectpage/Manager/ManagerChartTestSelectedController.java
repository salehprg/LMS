/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerChartTestSelectedController implements Initializable {

    @FXML
    private TextField TestName;
    @FXML
    private BarChart<?, ?> TestChart;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
