/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.plaf.synth.Region;
import selectpage.SelectPage;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentRegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnLogIn(MouseEvent event) {
         try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentLogIn.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Student Log In Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Log In page");

            }
    }

    @FXML
    private void BackToRolls(MouseEvent event) {
        try {
             
                URL test = SelectPage.class.getResource("FXMLDocument.fxml");
                
                FXMLLoader fxmlloader = new FXMLLoader(test);
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Smanae Shiba Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Label)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Smanae Shiba Page ");

            }
    }

    @FXML
    private void BtnReister(ActionEvent event) {
        
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentPage.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Student Student Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Page");

            }
    }
    
}
