package selectpage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Choice;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author sajad cj
 */
public class FXMLDocumentController implements Initializable {

    ObservableList List = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> Rolls;
//
//    @FXML
//    private 
    @FXML
    private Button btnConfirm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }
    
    //Stage stage ;
    @FXML
    void Confirm(ActionEvent event) {

        if ("Student".equals(Rolls.getValue())) {

            try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Student/StudentLogIn.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Log In Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Log In");

            }
            

        } else if ("manager".equals(Rolls.getValue())) 
        {
            try 
            {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Manager/ManagerLogIn.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Log In Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) 
            {
                System.out.println("Can't Open Student Log In");
            }
        }

    }

    private void loadData() {
        List.removeAll(List);
        String Roll1 = "Student";
        String Roll2 = "manager";
        //String Roll3 = "manager2";
        List.addAll(Roll1, Roll2);
        Rolls.getItems().addAll(List);

    }
}
