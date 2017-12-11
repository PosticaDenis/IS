package utils;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by c-denipost on 11-Dec-17.
 **/
public class TCPConnectionAccepter extends Thread {

    private int port;

    public TCPConnectionAccepter(String port) {
        this.port = Integer.parseInt(port);
    }

    @Override
    public void run() {

        try {
            ServerSocket welcomeSocket = new ServerSocket(port);

            while (true) {

                TCPServer tcpServer = new TCPServer(welcomeSocket.accept());
                System.out.println("Someone connected. Yay!!");
                tcpServer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
