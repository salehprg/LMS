/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.QuizesModel;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerArchiveTestController implements Initializable {

    @FXML
    private TextField TestName;
    @FXML
    private TextArea Students;
    @FXML
    private TextField TestDuration;
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
    private CheckBox TestReview;
    @FXML
    private Label Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void OpenStudentsAnswer(ActionEvent event) {

        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerArchiveTestStudent.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle(" Manager Archive Test Student Page");
            stage.setScene(new Scene(root1));
            stage.show();

//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();

        } catch (IOException ex) {
            System.out.println("Can't Open Manager Archive Test Student Page");
        }
    }

    
    private void loadData()
    {
        
        ArrayList<QuizesModel> mylist = Api.Admin_getQuizArchive();
        QuizesModel myqModel = mylist.get(Api.CurrentQuizId);
        
        TestName.setText(myqModel.QuizName);
        TestDuration.setText(String.valueOf(myqModel.Duration));
        Date.setText(myqModel.StartTime.getYear() + "/" + myqModel.StartTime.getMonth() + "/" + myqModel.StartTime.getDay() );
        StartHour.setText(String.valueOf(myqModel.StartTime.getHours()));
        StartMinute.setText(String.valueOf(myqModel.StartTime.getMinutes()));
        
        EndHour.setText(String.valueOf(myqModel.EndTime.getHours()));
        EndMinute.setText(String.valueOf(myqModel.EndTime.getMinutes()));

        TestReview.setSelected(myqModel.CanReview);
        if(myqModel.Random)
        {
            ArrangeMethod.setText("Random");
        }else{
            ArrangeMethod.setText(("Normal"));
        }

        Api.CurrentQuizId = myqModel.Id;
    }
}
