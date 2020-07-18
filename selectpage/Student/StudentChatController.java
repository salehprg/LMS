/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Student;

import Model.AllowQuizList;
import Model.Chat;
import Model.ChatView;
import Model.QuizesModel;
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
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import selectpage.Api.Api;
import selectpage.ChatRoom.ChatServer;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class StudentChatController implements Initializable {

    @FXML
    private ListView<String> ChatLits;

    
    @FXML
    private TextField txtMessage;

    @FXML
    void SendMessage(ActionEvent event) {
        ChatServer.SendMessage(txtMessage.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void selection() {
         Api.CurrentQuizId = ChatLits.getSelectionModel().getSelectedIndex();
        System.out.println(Api.CurrentQuizId);
        //inja dg mitoni SelectedTest o be sql pass bedi
    }

    private void loadData() {

        ChatServer.StartChat(Api.userModel.FirstName , this);
        ArrayList<ChatView> chats = Api.User_GetMessages(1);
        for (ChatView chatView : chats) {
            ChatLits.getItems().addAll(chatView.Message);
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
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println("Can't Open Student Chat Selected");

        }

    }

    public void WriteMessage(String message)
    {
        Chat chatModel = new Chat();
        chatModel.Message = message;
        chatModel.RoomId = 1;
        chatModel.UserId = Api.ActiveUserId;

        Api.User_SaveMessage(chatModel);
        ChatLits.getItems().addAll(message);
    }
}
