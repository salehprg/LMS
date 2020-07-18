/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerCreateTestController implements Initializable {

    ObservableList List = FXCollections.observableArrayList();
    @FXML
    private TextField TestName;
    @FXML
    private TextArea Students;
    @FXML
    private TextField TestDuration;
    @FXML
    private ChoiceBox<String> TestMethod;

    @FXML
    private CheckBox TestReviw;
    @FXML
    private DatePicker DataPicker;
    @FXML
    private Label lbDdate;
    @FXML
    private Button CreateTest;

    @FXML
    private TextField StartMinute;
    @FXML
    private TextField StartHour;
    @FXML
    private TextField EndMinute;
    @FXML
    private TextField EndHour;
    @FXML
    private RadioButton RandomArrangement;

    String fileAddress;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    void OpenFile(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel2007 Files", "*.xlsx")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null)
        {
            fileAddress = selectedFile.getAbsolutePath();
        }
    }

    @FXML
    private void SelectDate(ActionEvent event) {
        lbDdate.setText(DataPicker.getValue().toString());
    }

    @FXML
    private void DoCreate(ActionEvent event) {
        try {

            int QuizId = CreateTest();
            Api.CurrentQuizId = QuizId;
            if(QuizId != -1)
            {
                if(fileAddress != null)
                {
                    Api.AddUserFromExcel(fileAddress , Api.CurrentQuizId);
                }
                else{
                    AssignUserToQuiz(QuizId);
                }

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTestQuestion.fxml"));

                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();
                stage.setTitle("Manager Create Test Manager Create Test Question Page");
                stage.setScene(new Scene(root1));
                stage.show();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }

        } catch (IOException ex) {
            System.out.println("Can't Open Manager Create Test Manager Create Test Question page");

        }
    }

    private void loadData() {

        //TestMethod check box
        List.removeAll(List);
        String TestMethod1 = "Complet";
        String TestMethod2 = "One By One";

        List.addAll(TestMethod1, TestMethod2);
        TestMethod.getItems().addAll(List);

    }

    private int CreateTest() {
        
        QuizesModel quizesModel = new QuizesModel();
        quizesModel.QuizName = TestName.getText();
        quizesModel.Duration = Float.valueOf(TestDuration.getText());
        quizesModel.StartTime = Date.from(DataPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        quizesModel.StartTime.setHours(Integer.valueOf(StartHour.getText()));
        quizesModel.StartTime.setMinutes(Integer.valueOf(StartMinute.getText()));
        
        quizesModel.EndTime = Date.from(DataPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        quizesModel.EndTime.setHours(Integer.valueOf(EndHour.getText()));
        quizesModel.EndTime.setMinutes(Integer.valueOf(EndMinute.getText()));
        quizesModel.Random = RandomArrangement.isSelected();
        quizesModel.CanReview = TestReviw.isSelected();

        return Api.createNewQuiz(quizesModel);
    }

    private boolean AssignUserToQuiz(int QuizId)
    {
        String[] users = Students.getText().split("\\r?\\n");

        for (String user : users) {
            UserModel userModel = new UserModel();
            String[] userDetail = user.split(",");

            userModel.FirstName = userDetail[0];
            userModel.LastName = userDetail[1];
            userModel.IdNumber = userDetail[2];
            userModel.UserName = userDetail[2];
            userModel.Password = userDetail[2];

            Api.AssignUserToQuiz(userModel , QuizId);
        }
        
        return true;
    }
}
