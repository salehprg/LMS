/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerCreateTestController implements Initializable {

    ObservableList List = FXCollections.observableArrayList() ;
    @FXML
    private TextField TestName;
    @FXML
    private TextArea Students;
    @FXML
    private TextField TestDuration;
    @FXML
    private ChoiceBox<?> TestMethod;
    @FXML
    private ChoiceBox<?> ArrangeMethod;
    @FXML
    private CheckBox TestReviw;
    @FXML
    private DatePicker DataPicker;
    
    @FXML
    private Label lbDdate;
    @FXML
    private Button CreateTest;
    @FXML
    private ChoiceBox<?> AnswerMethod;
    @FXML
    private TextField StartMinute;
    @FXML
    private TextField StartHour;
    @FXML
    private TextField EndMinute;
    @FXML
    private TextField EndHour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }
    
    @FXML
    private void SelectDate(ActionEvent event) {
        lbDdate.setText(DataPicker.getValue().toString());
    }

    @FXML
    private void DoCreate(ActionEvent event) {
          try {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTestQuestion.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Create Test Manager Create Test Question Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Create Test Manager Create Test Question page");

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

        //TestMethod check box
        List.removeAll(List);
        String TestMethod1 = "Complet";
        String TestMethod2 = "One By One";

        List.addAll(TestMethod1, TestMethod2);
        TestMethod.getItems().addAll(List);

        //ArrangeMethod check box
        List.removeAll(List);
        String ArrangeMethod1 = "Orderly";
        String ArrangeMethod2 = "Random";

        List.addAll(ArrangeMethod1, ArrangeMethod2);
        ArrangeMethod.getItems().addAll(List);
    }

}