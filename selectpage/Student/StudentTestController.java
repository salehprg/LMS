/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentTestController implements Initializable {

    @FXML
    private TextField Address;
    @FXML
    private RadioButton AmsewrtestA;
    @FXML
    private ToggleGroup AnswerTest;
    @FXML
    private RadioButton AmsewrtestB;
    @FXML
    private RadioButton AmsewrtestD;
    @FXML
    private RadioButton AmsewrtestC;
    @FXML
    private RadioButton AnswerTrue;
    @FXML
    private ToggleGroup TrueFalse;
    @FXML
    private RadioButton AnswerFalse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
