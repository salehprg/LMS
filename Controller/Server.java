package Controller;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
    }

    	// thread manager
	public void acceptClients() throws IOException {
		// for keeping track of client number
		int clientNumber = 0;

		// create an open-ended thread pool
		ExecutorService threadPool = Executors.newCachedThreadPool();
		try {
			while (!Thread.currentThread().isInterrupted()) {
				// wait for client to connect
				System.out.println("Up and listening on port " + serverSocket.getLocalPort() + "...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());

				// create new client handler and fork to background thread
				threadPool.submit(new RequestHandler(clientSocket, clientNumber++ , serverSocket));
			}
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			// shut down thread pool when done
			threadPool.shutdown();
		}
	}
    public static void main(String[] args) {
        int port = 9876;
		try {
			new Server(port).acceptClients();
		} catch(IOException e) {
			e.printStackTrace();
		}
    }
}