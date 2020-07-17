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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//import jdk.nashorn.internal.ir.Labels;
import selectpage.SelectPage;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CreateTest(MouseEvent event) {
        
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCreateTest.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Create Test  Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Create Test page");

            }
    }

    @FXML
    private void Correction(MouseEvent event) {
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCorrection.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Correction Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Correction page");

            }
    }

    @FXML
    private void ChartPage(MouseEvent event) {
        try {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerCharts.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Manager Charts Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Charts page");

            }
    }

    @FXML
    private void Chat(MouseEvent event) {
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerChatPage.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle("Manager Charts Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Charts page");

            }
    }

    @FXML
    private void Program(MouseEvent event) {
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerProgram.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Manager Program Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Program page");

            }
    }

    @FXML
    private void Archive(MouseEvent event) {
        try {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ManagerArchive.fxml"));
                Parent root1 = (Parent) fxmlloader.load();
                Stage stage = new Stage();

                stage.setTitle(" Manager Archive Page");
                stage.setScene(new Scene(root1));
                stage.show();
//                stage = (Stage)((Lab)event.getSource()).getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("Can't Open Manager Archive page");

            }
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
    
}
