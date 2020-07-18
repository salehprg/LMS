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
    private TextArea Answer;
    @FXML
    private TextField Address;
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
    private VBox TrueFalse;
    @FXML
    private RadioButton AmswerTrue;
    @FXML
    private RadioButton AmswerFalse;
    @FXML
    private ChoiceBox<?> Survey;

    private int QuestionIndex ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void NextQuestion(ActionEvent event) {
        
          try {
                QuestionIndex ++ ;
              
              
              FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentArchiveTests.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Archive Tests Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Archive Tests Page");

            }
    }

    @FXML
    private void Preview(ActionEvent event) {
        
        try {
                QuestionIndex -- ;
              
              
              FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentArchiveTests.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Archive Tests Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Archive Tests Page");

            }
    }

    @FXML
    private void OpenFile(ActionEvent event) {
    }
    
    
    private void loadData()
    {
        
        ArrayList<QuestionsModel> myQuestionsModels = Api.User_GetQuestions(Api.CurrentQuizId);
        
        Question.setText(myQuestionsModels.get(QuestionIndex).QuestionText);
        
      
        List.removeAll(List);
        String Score1 = "Easy";
        String Score2 = "Normalr";
        String Score3 = "Hard";

        List.addAll(Score3, Score2,Score1);
        Survey.getItems().addAll(List);

 
    }
    
}
