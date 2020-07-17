/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.AvgQuizGrade;
import Model.QuizesModel;
import Model.UserGradesInQuiz;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerChartsTestSelectedController implements Initializable {

    @FXML
    private TextField TestName;
    @FXML
    private BarChart<?, ?> TestChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loadData() {
        
        //test chart
        XYChart.Series myChart = new XYChart.Series<>();
        ArrayList<UserGradesInQuiz> myUserGradesInQuizs = Api.Admin_reportStudentByStudent(Api.CurrentQuizId);
        for (int i = 0; i < myUserGradesInQuizs.size(); i++) {

            myChart.getData().add(new XYChart.Data(myUserGradesInQuizs.get(i).FirstName + myUserGradesInQuizs.get(i).LastName, myUserGradesInQuizs.get(i).UserGrade));
            TestChart.getData().addAll(myChart);
        }
    }
}
