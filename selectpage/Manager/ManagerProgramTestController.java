/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.QuizesModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerProgramTestController implements Initializable {

    @FXML
    private TextField TestName;
    @FXML
    private TextArea Students;
    @FXML
    private TextField TestDuration;
    @FXML
    private DatePicker DataPicker;
    @FXML
    private TextField StartMinute;
    @FXML
    private TextField StartHour;
    @FXML
    private TextField EndMinute;
    @FXML
    private TextField EndHour;
    @FXML
    private TextArea BlockedStudents;
    @FXML
    private TextField TestMethod;
    @FXML
    private TextField ArrangeMethod;
    @FXML
    private TextField AnswerMethod;
    @FXML
    private TextField TestReview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenQuestions(ActionEvent event) {
        
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerProgramTestQuestion.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Program Test Question Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Program Test Question Page");

            }
    }

    @FXML
    private void AddBlockStudent(ActionEvent event) {
    }
    
    private void loadData()
    {
//        Api.GetQuizes(Api.CurrentQuizId) ;
        QuizesModel myqModel = new QuizesModel();
//        myqModel.QuizName
        QuizesModel quizesModel = new QuizesModel();
         TestName.setText(quizesModel.QuizName);
         TestDuration.setText(String.valueOf(quizesModel.Duration));
        quizesModel.StartTime = Date.from(DataPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        quizesModel.StartTime.setHours(Integer.valueOf(StartHour.getText()));
        quizesModel.StartTime.setMinutes(Integer.valueOf(StartMinute.getText()));
        
        quizesModel.EndTime = Date.from(DataPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        quizesModel.EndTime.setHours(Integer.valueOf(EndHour.getText()));
        quizesModel.EndTime.setMinutes(Integer.valueOf(EndMinute.getText()));
        quizesModel.Random = RandomArrangement.selectedProperty().get();
    }
    
}
