/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.OptionsModel;
import Model.QuestionsModel;
import Model.QuizesModel;
import Model.QuestionsModel.QType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerCreateTestQuestionController implements Initializable {

    ObservableList List = FXCollections.observableArrayList();
    @FXML
    private TextArea Question;
    @FXML
    private TextField QuestionTime;
    @FXML
    private TextField TextGrade;

    @FXML
    private ChoiceBox<String> AnswerMethod;
    @FXML
    private VBox TestAns;
    @FXML
    private RadioButton AnswerA;
    @FXML
    private ToggleGroup Answer;
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

    private ArrayList<QuestionsModel> questionsModels = new ArrayList<>();
    private static int QuestionIndex;
    @FXML
    private Label Grade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        try {
            QuestionsModel model = questionsModels.get(QuestionIndex);
            Question.setText(model.QuestionText);
            QuestionTime.setText(String.valueOf(model.Duration));
            TextGrade.setText(String.valueOf(model.Grade));

            switch (model.QuestionType) {
                case Testi:
                    ArrayList<OptionsModel> options = Api.GetOptionss(model.Id);
                    if (model.Answer.equals(String.valueOf(options.get(0).Id))) {
                        AnswerA.setSelected(true);
                    } else if (model.Answer.equals(String.valueOf(options.get(1).Id))) {
                        AnswerB.setSelected(true);
                    } else if (model.Answer.equals(String.valueOf(options.get(2).Id))) {
                        AnswerC.setSelected(true);
                    } else if (model.Answer.equals(String.valueOf(options.get(3).Id))) {
                        AnswerD.setSelected(true);
                    }

                    break;

                case TrueFalse:
                    if (model.Answer.equals("true")) {
                        AnswerTrue.setSelected(true);
                    } else {
                        AnswerFalse.setSelected(true);
                    }
                    break;

                default:
                    break;

            }
        } catch (Exception e) {

        }

    }

    @FXML
    private void Next(ActionEvent event) {
        try {

            QuestionIndex++;
            if(QuestionIndex > questionsModels.size())
            {
                QuestionIndex =  questionsModels.size();
            }

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTestQuestion.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Create Test Question Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Create Test Question page");

        }
    }

    @FXML
    private void CreateTest(ActionEvent event) {

        try {

            if (CreateQuestion()) {
                Next(event);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerCreateTestQuestionController.class.getName()).log(Level.SEVERE, null, ex);
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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTestQuestion.fxml"));

            Parent root1 = (Parent) fxmlloader.load();

            Stage stage = new Stage();

            stage.setTitle("Manager Create Test Question Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Create Test Question page");

        }
    }

    private void loadData() {

        questionsModels = Api.Admin_GetQuestions(Api.CurrentQuizId);
        //AnswerMethod check box
        List.removeAll(List);
        String AnswerMethod1 = "Test";
        String AnswerMethod2 = "Long Answer";
        String AnswerMethod3 = "True Or False";

        List.addAll(AnswerMethod1, AnswerMethod2, AnswerMethod3);
        AnswerMethod.getItems().addAll(List);
    }

    @FXML
    private void kir8(MouseEvent event) {

        if (AnswerMethod.getValue().equals("Test")) {
            TestAns.setVisible(true);
            TrueFalse.setVisible(false);
        } else if (AnswerMethod.getValue().equals("True Or False")) {
            TestAns.setVisible(false);
            TrueFalse.setVisible(true);
        } else {
            TestAns.setVisible(false);
            TrueFalse.setVisible(false);
        }
    }

    private boolean CreateQuestion() {
        QuestionsModel questionsModel = new QuestionsModel();
        questionsModel.QuestionText = Question.getText();
        questionsModel.Duration = Float.parseFloat((QuestionTime.getText() != null ? QuestionTime.getText() : "0"));
        questionsModel.QuizId = Api.CurrentQuizId;
        questionsModel.Grade = Float.valueOf(TextGrade.getText());

        if (AnswerMethod.getValue().equals("Test")) {
            questionsModel.QuestionType = QType.Testi;
            if (AnswerA.isSelected()) {
                questionsModel.Answer = "0";
            } else if (AnswerB.isSelected()) {
                questionsModel.Answer = "1";
            } else if (AnswerC.isSelected()) {
                questionsModel.Answer = "2";
            } else if (AnswerD.isSelected()) {
                questionsModel.Answer = "3";
            }
        } else if (AnswerMethod.getValue().equals("True Or False")) {
            questionsModel.QuestionType = QType.TrueFalse;
            if (AnswerTrue.isSelected()) {
                questionsModel.Answer = String.valueOf(true);
            } else {
                questionsModel.Answer = String.valueOf(false);
            }
        } else {
            questionsModel.QuestionType = QType.Tashrihi;
        }

        return Api.addQuestionToQuiz(questionsModel);
    }

}
