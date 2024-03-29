/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import Model.AllowQuizList;
import Model.QuizesModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
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
import javax.print.DocFlavor;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerArchiveController implements Initializable {

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
        selection();

        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerArchiveTest.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();

            stage.setTitle(" Manager Archive Test Page");
            stage.setScene(new Scene(root1));
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Manager Archive Test page");

        }

    }

    private void selection() {
        int index = ArchiveList.getSelectionModel().getSelectedIndex();

        Api.CurrentQuizId = index;

        System.out.println(Api.CurrentQuizId);
    }

    private void loadData() {
        ArrayList<QuizesModel> MyList = Api.Admin_getQuizArchive();
        for (int i = 0; i < MyList.size(); i++) {
            ArchiveList.getItems().add(MyList.get(i).QuizName);
        }
    }

}
