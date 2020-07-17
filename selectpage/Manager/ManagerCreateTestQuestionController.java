/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ChoiceBox<String> QuestionAnswer;

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
        String QuestionAnswer1 = "A";
        String QuestionAnswer2 = "B";
        String QuestionAnswer3 = "C";
        String QuestionAnswer4 = "D";
        String QuestionAnswer5 = "True";
        String QuestionAnswer6 = "False";

        List.addAll(QuestionAnswer1, QuestionAnswer2, QuestionAnswer3, QuestionAnswer4, QuestionAnswer5, QuestionAnswer6);
        QuestionAnswer.getItems().addAll(List);
    }
}
