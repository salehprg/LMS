package selectpage.ChatRoom;

import java.net.*;
import java.io.*;
import java.util.*;

import Model.Chat;
import selectpage.Api.Api;
import selectpage.Manager.ManagerChatPageController;
import selectpage.Student.StudentChatController;

public class ChatServer {
    private static final String TERMINATE = "Exit";
    private static String name;
    private static MulticastSocket socket;
    private static InetAddress group;

    private static int port = 5646;
    private static String host = "239.0.0.0";

    static volatile boolean finished = false;
    public static StudentChatController chatController;
    public static ManagerChatPageController adminchatController;


    public static boolean SendMessage(String Message) {

        try {
            Message = name + ": " + Message;

            byte[] buffer = Message.getBytes();
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);

            socket.send(datagram);

            Chat chatModel = new Chat();
            chatModel.Message = Message;
            chatModel.RoomId = 1;
            chatModel.UserId = Api.ActiveUserId;
            
            Api.User_SaveMessage(chatModel);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void StartChat(String username , ManagerChatPageController chatController){
        try {
            ChatServer.adminchatController = chatController;
            name = username;

            group = InetAddress.getByName(host);

            socket = new MulticastSocket(port);

            // Since we are deploying
            socket.setTimeToLive(0);

            socket.joinGroup(group);

            Thread t = new Thread(new ReadThread(socket, group, port));

            // Spawn a thread for reading messages
            t.start();

        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }

    public static void StartChat(String username , StudentChatController chatController){
        try {
            ChatServer.chatController = chatController;
            name = username;

            group = InetAddress.getByName(host);

            socket = new MulticastSocket(port);

            // Since we are deploying
            socket.setTimeToLive(0);

            socket.joinGroup(group);

            Thread t = new Thread(new ReadThread(socket, group, port));

            // Spawn a thread for reading messages
            t.start();

        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }

    public static void EndChat() {
        try {
            finished = true;
            socket.leaveGroup(group);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        socket.close(); 
    }
} 
class ReadThread implements Runnable 
{ 
	private MulticastSocket socket; 
	private InetAddress group; 
	private int port; 
	private static final int MAX_LEN = 1000; 
	ReadThread(MulticastSocket socket,InetAddress group,int port) 
	{ 
		this.socket = socket; 
		this.group = group; 
		this.port = port; 
	} 
	
	@Override
	public void run() 
	{ 
		while(!ChatServer.finished) 
		{ 
            byte[] buffer = new byte[ReadThread.MAX_LEN]; 
            DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port); 

            String message; 
			try
			{ 
				socket.receive(datagram); 
                message = new String(buffer,0,datagram.getLength(),"UTF-8"); 
                
                if(ChatServer.chatController != null)
                {
                    ChatServer.chatController.WriteMessage(message);
                    System.out.println(message); 
                }
                else
                {
                    ChatServer.adminchatController.WriteMessage(message);
                    System.out.println(message); 
                }

                
                
			} 
			catch(IOException e) 
			{ 
				System.out.println("Socket closed!"); 
			} 
		} 
	} 
}
