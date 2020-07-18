/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.AnswersForGrading;
import Model.AnswersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerArchiveTestStudentAnswerController implements Initializable {

    @FXML
    private TextArea Answer;

    @FXML
    private Button SubmitScore;

    @FXML
    private TextArea Question;

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
    private TextField txtScore;

    @FXML
    private Hyperlink Address;

    @FXML
    private Label questionGrade;

    private static int questionIndex = 0;
    private String fileAddr;
    private ArrayList<AnswersForGrading> answersModels;
    private AnswersForGrading answersGrading;

    @FXML
    void SubmitGrade(ActionEvent event) {

        Api.Admin_SubmitGrade(answersGrading.AnswerId , Float.valueOf(txtScore.getText()));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        if(answersGrading != null)
        {
            Question.setText(answersGrading.QuestionText);
            txtScore.setText(String.valueOf(answersGrading.UserGrade));
            questionGrade.setText(String.valueOf(answersGrading.Grade));

            try {
                switch (answersGrading.QuestionType) {
                    case Testi:
                        TrueFalse.setVisible(false);
                        Answer.setVisible(false);
                        TestAns.setVisible(true);

                        if (answersGrading.Answer.equals("0")) {
                            AnswerA.setSelected(true);
                        } else if (answersGrading.Answer.equals("1")) {
                            AnswerB.setSelected(true);
                        } else if (answersGrading.Answer.equals("2")) {
                            AnswerC.setSelected(true);
                        } else if (answersGrading.Answer.equals("3")) {
                            AnswerD.setSelected(true);
                        }

                        break;

                    case TrueFalse:
                        TrueFalse.setVisible(true);
                        Answer.setVisible(false);
                        TestAns.setVisible(false);

                        if (answersGrading.Answer.equals("True")) {
                            AnswerTrue.setSelected(true);
                        } else {
                            AnswerFalse.setSelected(true);
                        }
                        break;

                    case Tashrihi:
                        Address.setText(answersGrading.FileAddress);
                        TrueFalse.setVisible(false);
                        Answer.setVisible(true);
                        TestAns.setVisible(false);
                        break;
                    default:
                        
                        break;

                }
            } catch (Exception e) {

            }
        }

    }    

    @FXML
    private void NextQuestion(ActionEvent event) {
        try {
            questionIndex++;
            if(questionIndex > answersModels.size())
            {
                questionIndex =  answersModels.size();
            }

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerArchiveTestStudentAnswer.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Archive Test Student Answer Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Archive Test Student Answer Page");

        }
    }

    @FXML
    private void Previous(ActionEvent event) {
        
         try {

                questionIndex--;
                if(questionIndex < 0)
                {
                    questionIndex = 0;
                }

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerArchiveTestStudentAnswer.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Archive Test Student Answer Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Archive Test Student Answer Page");

            }
    }

    @FXML
    private void OpenFile(ActionEvent event) {
         
    }
    
    private void loadData()
    {
       answersModels = Api.Admin_getUserAnswers(Api.CurrentStudentId , Api.CurrentQuizId);
       answersGrading = answersModels.get(questionIndex);
       
        Api.CurrentQuestionId = answersGrading.QuestionId;
    }
    
}
