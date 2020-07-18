/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AnswersModel;
import Model.QuestionsModel;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentTestController implements Initializable {

    @FXML
    private TextField Address;
    @FXML
    private VBox TrueFalse;
    @FXML
    private TextArea Answer;
    @FXML
    private TextArea Question;
    @FXML
    private VBox TestAns;
    @FXML
    private RadioButton AmswerA;
    @FXML
    private ToggleGroup Answer1;
    @FXML
    private RadioButton AmswerB;
    @FXML
    private RadioButton AmswerC;
    @FXML
    private RadioButton AmswerD;
    @FXML
    private RadioButton AmswerTrue;
    @FXML
    private RadioButton AmswerFalse;

    private String imgAddress;
    private int QuestionIndex;

    ArrayList<QuestionsModel> myQuestionsModels = Api.User_GetQuestions(Api.CurrentQuizId);
    @FXML
    private Label lblAddress;
    @FXML
    private Button BtnAddress;

    private ArrayList<AnswersModel> myAnswersModels;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void NextQuestion(ActionEvent event) {
setData();
        try {

            QuestionIndex++;
            if (QuestionIndex > myQuestionsModels.size()) {
                QuestionIndex = myQuestionsModels.size();
            }

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentTest.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Student Test Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Student Test page");

        }
    }

    @FXML
    private void Preview(ActionEvent event) {
setData();
        try {
            QuestionIndex--;
            if (QuestionIndex < 0) {
                QuestionIndex = 0;
            }

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentTest.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Student Test Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Student Test page");

        }

    }

    @FXML
    private void OpenFile(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files (.jpg)", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            imgAddress = selectedFile.getAbsolutePath();
        }
    }

    private void loadData() {

        Question.setText(myQuestionsModels.get(QuestionIndex).QuestionText);

        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Tashrihi)) {
            TestAns.setVisible(false);
            TrueFalse.setVisible(false);
            Answer.setVisible(true);
            lblAddress.setVisible(true);
            BtnAddress.setVisible(true);
            Address.setVisible(true);
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Testi)) {
            TestAns.setVisible(true);
            TrueFalse.setVisible(false);
            Answer.setVisible(false);
            lblAddress.setVisible(false);
            BtnAddress.setVisible(false);
            Address.setVisible(false);
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.TrueFalse)) {
            TestAns.setVisible(false);
            TrueFalse.setVisible(true);
            Answer.setVisible(false);
            lblAddress.setVisible(false);
            BtnAddress.setVisible(false);
            Address.setVisible(false);
        }

    }

    private void setData() {

        myAnswersModels.get(QuestionIndex).QuestionId = Api.CurrentQuizId;

        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Tashrihi)) {

            myAnswersModels.get(QuestionIndex).Answer = Answer.getText();
            myAnswersModels.get(QuestionIndex).FileAddress = Address.getText();
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Testi)) {
            if (AmswerA.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "0";
            }
            if (AmswerB.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "1";
            }
            if (AmswerC.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "2";
            }
            if (AmswerD.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "3";
            }
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.TrueFalse)) {
            if (AmswerTrue.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "True";
            }
            if (AmswerFalse.isSelected()) {
                myAnswersModels.get(QuestionIndex).Answer = "False";
            }
        }
        myAnswersModels.get(QuestionIndex).QuestionId = Api.CurrentQuizId;
        myAnswersModels.get(QuestionIndex).UserId = Api.ActiveUserId;
        
        Api.SubmitAnswer(myAnswersModels.get(QuestionIndex));
    }

    @FXML
    private void Finish(ActionEvent event) {
        
        
        setData();
        Stage stage = new Stage();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
