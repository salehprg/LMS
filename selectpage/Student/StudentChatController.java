/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

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

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentChatController implements Initializable {

    @FXML
    private ListView<String> ChatLits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    
    private void selection()
    {
        String SelectedTest = "";
        ObservableList listOfItems = ChatLits.getSelectionModel().getSelectedItems();
        
        for(Object item : listOfItems)
        {
            SelectedTest += String.format("%s%n", (String)item) ;
        }
        
        System.out.println(SelectedTest);
        //inja dg mitoni SelectedTest o be sql pass bedi
    }
    private void loadData()
    {
        
        ArrayList<String> MyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyList.add(i, "test " + i); 
        }
        //ezafe kardn be list
        for (int i = 0; i < 10; i++) {
           ChatLits.getItems().add(MyList.get(i)) ;
        }
        
        
    }

    @FXML
    private void OpneChat(ActionEvent event) {
        
        selection();
        
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("StudentChatSelected.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Student Chat Selected Page");
                stage.setScene(new Scene(root1));
                stage.show();
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Student Chat Selected");

            }
        
    }
}
