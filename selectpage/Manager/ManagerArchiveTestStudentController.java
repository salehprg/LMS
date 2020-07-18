/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.AllowQuizList;
import Model.UserModel;

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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerArchiveTestStudentController implements Initializable {

    @FXML
    private ListView<String> StudentsList;

    ArrayList<UserModel> users;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    void AutoGrade(ActionEvent event) {
        Api.Admin_AutoGrading(Api.CurrentQuizId);
    }

    @FXML
    private void OpenAnswer(ActionEvent event) {
        
         try {

                selection();

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
    
    private void loadData() {

        users = Api.Admin_getUsersInQuiz(Api.CurrentQuizId);
        for (int i = 0; i < users.size(); i++) {
            StudentsList.getItems().add(users.get(i).FirstName + " " + users.get(i).LastName);
        }

    }
    
    private void selection() {

        int index = StudentsList.getSelectionModel().getSelectedIndex();
        Api.CurrentStudentId = users.get(index).Id;

        System.out.println(Api.CurrentStudentId);

    }
    
}
