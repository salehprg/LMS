/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerChartsController implements Initializable {

    @FXML
    private BarChart<?, ?> TestsChart;
    @FXML
    private ListView<String> TestsList;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis X;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void OpenTestChart(ActionEvent event) {
        try {

            selection();
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerChartsTestSelected.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Charts Test Selected Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Charts Test Selected Page");

        }
    }

    private void loadData() {

        //test chart
        XYChart.Series myChart = new XYChart.Series<>();
        ArrayList<AvgQuizGrade> myAvgQuizGrades = Api.Admin_reportQuizByQuiz();
        for (int i = 0; i < myAvgQuizGrades.size(); i++) {

            myChart.getData().add(new XYChart.Data(myAvgQuizGrades.get(i).QuizName, myAvgQuizGrades.get(i).avgGrade));
            TestsChart.getData().addAll(myChart);
        }
        
        //test list
        ArrayList<QuizesModel> MyList = Api.Admin_getQuizArchive();
        for (int i = 0; i < MyList.size(); i++) {
            TestsList.getItems().add(MyList.get(i).QuizName);

        }
    }

    
    private void selection() {
        int index = TestsList.getSelectionModel().getSelectedIndex();
        ArrayList<AllowQuizList> quizLists = Api.GetQuizes();
        Api.CurrentQuizId = TestsList.getSelectionModel().getSelectedIndex();
        System.out.println(Api.CurrentQuizId);
    }
}
