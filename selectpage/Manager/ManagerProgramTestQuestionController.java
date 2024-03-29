/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.QuestionsModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerProgramTestQuestionController implements Initializable {

    @FXML
    private TextArea Question;
    @FXML
    private TextField QuestionTime;
    @FXML
    private TextField QuestionAnswer;

    private static int QuestionIndex;
//    private ArrayList<QuizesModel> mymModels = Api.Admin_getQuizProgram();
    private ArrayList<QuestionsModel> myQuestionsModels = Api.Admin_getQuestions_Grading(Api.CurrentQuizId);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Next(ActionEvent event) {
        try {

            QuestionIndex++;
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerProgramTestQuestion.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Program Test Question Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Program Test Question Page");

        }
    }

    @FXML
    private void Preview(ActionEvent event) {
        try {
            QuestionIndex--;
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerProgramTestQuestion.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Program Test Question Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Program Test Question Page");

        }
    }

    private void loadData() {
        Question.setText(myQuestionsModels.get(QuestionIndex).QuestionText);
        QuestionAnswer.setText(String.valueOf(myQuestionsModels.get(QuestionIndex).Duration));

    }

}
