/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AllowQuizList;
import Model.QuizesModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentArchiveController implements Initializable {

    @FXML
    private ListView<String> ArchiveList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void OpenTest(ActionEvent event) {
    }

    private void selection() {        
        Api.CurrentQuizId = ArchiveList.getSelectionModel().getSelectedIndex();
        System.out.println(Api.CurrentQuizId);

    }

    private void loadData() {
        ArrayList<AllowQuizList> MyList = Api.GetQuizes();
        for (int i = 0; i < MyList.size(); i++) {
            ArchiveList.getItems().add(MyList.get(1).QuizName);

        }
    }

}
