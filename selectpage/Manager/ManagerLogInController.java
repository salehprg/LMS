/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import selectpage.Api.Api;
import selectpage.SelectPage;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerLogInController implements Initializable {

    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void CreateAcc(MouseEvent event) {
        try {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerRegister.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Manager Register Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Label)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Register page");

            }
    }

    @FXML
    private void BackToRolls(MouseEvent event) {
       
        try {
                URL test = SelectPage.class.getResource("FXMLDocument.fxml");
                
                FXMLLoader fxmlloader = new FXMLLoader(test);
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Samane Shiba");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Label)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Samane Shiba page");

            }
    }

    @FXML
    private void LogIn(ActionEvent event) {
        
        try {
                int userId = Api.Login(UserName.getText(), Password.getText());
                if(userId != -1)
                {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerPage.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
                }
            } catch (IOException ex) {
                System.out.println("Can't Open Manager page");

            }
            
    }
    
   
}
