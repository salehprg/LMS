/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AllowQuizList;
import Model.QuizesModel;
import java.io.IOException;
import selectpage.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentWaitTestController implements Initializable{

    @FXML
    private Text TestName;
    @FXML
    private Text TestDuration;
    @FXML
    private Text TestStartDate;
    @FXML
    private Text TestStartDate1;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void StartTest(ActionEvent event) {
        
        
        if (Api.EnrolQuiz(Api.CurrentQuizId)) {       
        
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentArchiveTests.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle(" Student Archive Tests Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Student Archive Tests page");

        }
        }
    }
    
    private void loadData ()
    {
        ArrayList<AllowQuizList> myAllowQuizLists = Api.GetQuizes();
        AllowQuizList quizInfo = new AllowQuizList();
        quizInfo = myAllowQuizLists.get(Api.CurrentQuizId);

        TestName.setText(quizInfo.QuizName);
        TestDuration.setText(String.valueOf(quizInfo.Duration));
        TestStartDate.setText(String.valueOf(quizInfo.StartTime));   
         
        Api.CurrentQuizId = quizInfo.QuizId;
    }
}
