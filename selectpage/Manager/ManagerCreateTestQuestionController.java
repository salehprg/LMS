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
    private ChoiceBox<String> AnswerMethod;
    @FXML
    private VBox TestAns;
    @FXML
    private RadioButton AmswerA;
    @FXML
    private ToggleGroup Answer;
    @FXML
    private RadioButton AmswerB;
    @FXML
    private RadioButton AmswerC;
    @FXML
    private RadioButton AmswerD;
    @FXML
    private VBox TrueFalse;
    @FXML
    private RadioButton AmswerTrue;
    @FXML
    private RadioButton AmswerFalse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
       
    }

    @FXML
    private void Next(ActionEvent event) {
        try {

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
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTestQuestion.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagerCreateTestQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Preview(ActionEvent event) {

        try {

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
    
    private void CreateQuestion()
    {
        
      
        
        QuestionsModel MyquestionsModel = new QuestionsModel();
        MyquestionsModel.QuestionText = Question.getText();
        MyquestionsModel.Duration = Float.parseFloat(QuestionTime.getText());
//        MyquestionsModel.QuizId = userID.TestID ;
        
        if (AnswerMethod.getValue().equals("Test")) {
//           MyquestionsModel.QuestionType = 1 ;
        } else if (AnswerMethod.getValue().equals("True Or False")) {
//            MyquestionsModel.QuestionType =2 ;
        } else {
//            MyquestionsModel.QuestionType = 0 ;
        }
        Api.addQuestionToQuiz(MyquestionsModel);
    }

    
}
