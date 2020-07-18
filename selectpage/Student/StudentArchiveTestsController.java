/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AnswersModel;
import Model.QuestionsModel;
import Model.SurveyModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentArchiveTestsController implements Initializable {

    ObservableList List = FXCollections.observableArrayList();
    @FXML
    private Label Answer;
    @FXML
    private Hyperlink Address;
    @FXML
    private Label Question;
    @FXML
    private VBox TestAns;
    @FXML
    private RadioButton AnswerA;
    @FXML
    private ToggleGroup Answer1;
    @FXML
    private RadioButton AnswerB;
    @FXML
    private RadioButton AnswerC;
    @FXML
    private RadioButton AnswerD;
    @FXML
    private VBox TrueFalse;
    @FXML
    private RadioButton AnswerTrue;
    @FXML
    private RadioButton AnswerFalse;
    @FXML
    private ChoiceBox<?> Survey;

    @FXML
    private Label UserScore;

    private int QuestionIndex;
    @FXML
    private Label Score;

    
    ArrayList<QuestionsModel> myQuestionsModels;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myQuestionsModels = Api.User_GetQuestions(Api.CurrentQuizId);
        loadData();
    }

    @FXML
    private void NextQuestion(ActionEvent event) {

        try {
            QuestionIndex++;
            if(QuestionIndex >= myQuestionsModels.size())
            {
                QuestionIndex =  myQuestionsModels.size();
            }

            loadData();
        } catch (Exception ex) {
            System.out.println("Can't Open Student Archive Tests Page");

        }
    }

    @FXML
    private void Preview(ActionEvent event) {

        try {
           
             QuestionIndex--;
            if(QuestionIndex < 0)
            {
                QuestionIndex =  0;
            }

            loadData();
            
        } catch (Exception ex) {
            System.out.println("Can't Open Student Archive Tests Page");

        }
    }

    @FXML
    private void OpenFile(ActionEvent event) {
    }

    private void loadData() {

        QuestionsModel questionsModel = myQuestionsModels.get(QuestionIndex);
        AnswersModel answersModel = Api.Review_GetUserAnswer(questionsModel.Id);

        Question.setText(questionsModel.QuestionText);

        switch (questionsModel.QuestionType) 
        {
            case Tashrihi:
                TestAns.setVisible(false);
                TrueFalse.setVisible(false);
                Answer.setVisible(true);
                Address.setVisible(true);

                Answer.setText(answersModel.Answer);
                Address.setText(answersModel.FileAddress);

                break;
                
            case Testi:
                TestAns.setVisible(true);
                TrueFalse.setVisible(false);
                Answer.setVisible(false);
                Address.setVisible(false);

                if (answersModel.Answer.equals("0")) {
                    AnswerA.setSelected(true);
                } else if (answersModel.Answer.equals("1")) {
                    AnswerB.setSelected(true);
                } else if (answersModel.Answer.equals("2")) {
                    AnswerC.setSelected(true);
                } else if (answersModel.Answer.equals("3")) {
                    AnswerD.setSelected(true);
                }

                break;
                
            case TrueFalse:
                TestAns.setVisible(false);
                TrueFalse.setVisible(true);
                Answer.setVisible(false);
                Address.setVisible(false);

                if (answersModel.Answer.equals("True")) {
                    AnswerTrue.setSelected(true);
                } else {
                    AnswerFalse.setSelected(true);
                }
				break;
			default:
				break;

           
        }


        Score.setText(String.valueOf(questionsModel.Grade));
        UserScore.setText(String.valueOf(answersModel.UserGrade));

        List.removeAll(List);
        String Score1 = "Easy";
        String Score2 = "Normalr";
        String Score3 = "Hard";

        List.addAll(Score3, Score2, Score1);
        Survey.getItems().addAll(List);

        ArrayList<QuestionsModel> myQuestionsModels1 = Api.Review_GetQuestions(Api.CurrentQuizId);

    }

    @FXML
    private void SetSurvey(MouseEvent event) {

        if (Survey.getValue() != null) {
            SurveyModel mysSurveyModel = new SurveyModel();
            mysSurveyModel.QuizId = Api.CurrentQuizId;
            if (Survey.getValue().equals("Easy")) {
                mysSurveyModel.QuizLevel = 1;
            } else if (Survey.getValue().equals("Normal")) {
                mysSurveyModel.QuizLevel = 2;
            } else if (Survey.getValue().equals("Hard")) {
                mysSurveyModel.QuizLevel = 3 ;
            }
        }
    }

}


