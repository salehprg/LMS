/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.QuestionsModel;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
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

    private int QuestionIndex ;
    
    ArrayList<QuestionsModel> myQuestionsModels =  Api.User_GetQuestions(Api.CurrentQuizId) ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NextQuestion(ActionEvent event) {
        
        try {

           QuestionIndex++;
            if(QuestionIndex > myQuestionsModels.size())
            {
                QuestionIndex =  myQuestionsModels.size();
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
        
        try {
             QuestionIndex--;
            if(QuestionIndex < 0)
            {
                QuestionIndex =  0;
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
    }
    
    private void loadData()
    {
        

        Question.setText(myQuestionsModels.get(QuestionIndex).QuestionText);

        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Tashrihi)) {
            Answer.setText(myQuestionsModels.get(QuestionIndex).Answer);
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.Testi)) {
        }
        if (myQuestionsModels.get(QuestionIndex).QuestionType.equals(QuestionsModel.QType.TrueFalse)) {
        }
        
                 
    }
}
