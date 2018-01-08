package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by c-denipost on 11-Dec-17.
 **/

//TODO make it single-tone
public class UDPServer extends Thread {

    private String port;

    public UDPServer(String port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(port));
            byte[] receiveData = new byte[1024];

            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                System.out.println("[UDP]RECEIVED: " + sentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
