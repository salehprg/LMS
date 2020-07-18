/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectpage.Manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Chat;
import Model.ChatView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import selectpage.Api.Api;
import selectpage.ChatRoom.ChatServer;

/**
 * FXML Controller class
 *
 * @author sajad cj
 */
public class ManagerChatPageController implements Initializable {

    @FXML
    private ListView<String> ChatLits;
    @FXML
    private Button SendMessage;

    @FXML
    private TextField txtMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    void SendMessage(ActionEvent event) {
        
        ChatServer.SendMessage(txtMessage.getText());

    }

    private void loadData() {

        ChatServer.StartChat(Api.userModel.FirstName , this);
        ArrayList<ChatView> chats = Api.User_GetMessages(1);
        for (ChatView chatView : chats) {
            ChatLits.getItems().addAll(chatView.Message);
        }
    }
    
    public void WriteMessage(String message)
    {
        ChatLits.getItems().addAll(message);
    }
}
