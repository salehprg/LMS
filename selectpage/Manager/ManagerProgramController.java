/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.AllowQuizList;
import Model.QuestionsModel;
import Model.QuizesModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class ManagerProgramController implements Initializable {

    @FXML
    private ListView<String> TestsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void OpenTest(ActionEvent event) {
        try {
            selection();

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerProgramTest.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle("Manager Program Test Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Program Test Page");

        }
    }

    private void selection() {

        int index = TestsList.getSelectionModel().getSelectedIndex();

        ArrayList<AllowQuizList> quizLists = Api.GetQuizes();
        Api.CurrentQuizId = TestsList.getSelectionModel().getSelectedIndex();
        System.out.println(Api.CurrentQuizId);

    }

    private void loadData() {
//        int index = TestsList.getSelectionModel().getSelectedIndex();
        ArrayList<QuizesModel> MymModels = Api.Admin_getQuizProgram();

        for (int i = 0; i < 10; i++) {
//            MyList.add(MymModels.get(i).QuizName); 
        }
        //ezafe kardn be list
        for (int i = 0; i < 10; i++) {
            TestsList.getItems().add(i + "dick");
        }
    }
}
