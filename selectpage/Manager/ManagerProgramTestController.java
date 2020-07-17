/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerProgramTestController implements Initializable {

    @FXML
    private TextField TestName;
    @FXML
    private TextArea Students;
    @FXML
    private TextField TestDuration;
    @FXML
    private DatePicker DataPicker;
    @FXML
    private TextField StartMinute;
    @FXML
    private TextField StartHour;
    @FXML
    private TextField EndMinute;
    @FXML
    private TextField EndHour;
    @FXML
    private TextArea BlockedStudents;
    @FXML
    private TextField TestMethod;
    @FXML
    private TextField ArrangeMethod;
    @FXML
    private TextField AnswerMethod;
    @FXML
    private TextField TestReview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenQuestions(ActionEvent event) {
    }

    @FXML
    private void AddBlockStudent(ActionEvent event) {
    }
    
}
