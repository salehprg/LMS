/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AllowQuizList;
import java.io.IOException;
import selectpage.*;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import selectpage.Api.Api;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentPageController implements Initializable{

    private ArrayList<AllowQuizList> MyList;

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
    private void LogOut(ActionEvent event) {
        try {
                URL test = SelectPage.class.getResource("FXMLDocument.fxml");
                
                FXMLLoader fxmlloader = new FXMLLoader(test);
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Samane Shiba");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Samane Shiba page");

            }
    }

    @FXML
    private void OpenTest(ActionEvent event) {
        try {
                selection();

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentWaitTest.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Wait Test Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Wait Test Page");

            }
    }

    @FXML
    private void Chat(MouseEvent event) {
    }

    @FXML
    private void StudantChat(MouseEvent event) {
        try{
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentChat.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Chat Page");
                stage.setScene(new Scene(root1));
                stage.show();
                
            } catch (IOException ex) {
                System.out.println("Can't Open Student Chat Page");

            }
    }

    @FXML
    private void Archive(MouseEvent event) {
        
        try{
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentArchive.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Archive Page");
                stage.setScene(new Scene(root1));
                stage.show();
                
            } catch (IOException ex) {
                System.out.println("Can't Open Studen tArchive Page");

            }
    }
    
    private void selection() {        
        Api.CurrentQuizId = TestsList.getSelectionModel().getSelectedIndex();
        System.out.println(Api.CurrentQuizId);

    }

    private void loadData() {
        MyList = Api.GetQuizes();
        for (int i = 0; i < MyList.size(); i++) {
            TestsList.getItems().add(MyList.get(i).QuizName);

        }
    }
}
