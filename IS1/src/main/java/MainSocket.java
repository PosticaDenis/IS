import utils.IpCheckerUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by c-denipost on 11-Dec-17.
 **/
public class MainSocket {

    public static void main(String[] args) {

        MainSocket ms = new MainSocket();

        while (true) {
            System.out.println("Enter a command: ");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();

            if (cmd.contains("-tp") && cmd.contains("-m")) {
                //expected format: -tp [host:port] -m ["message"]

                String[] tmp = cmd.split(" ");
                String serverAddr = tmp[1];

                if (IpCheckerUtil.validate(serverAddr)) {

                    String msg = tmp[3].split("\"")[1];

                    if (cmd.contains("-l") && !cmd.contains("-m")){
                        ms.listenUDP();
                    }
                    else {
                        ms.sendTCP(serverAddr, msg);
                    }
                }
                else {
                    System.out.println("Invalid address. Try again.");
                }

            }
            else if (cmd.contains("-up") && cmd.contains("-m")) {

                String[] tmp = cmd.split(" ");
                String serverAddr = tmp[1];

                if (IpCheckerUtil.validate(serverAddr)) {

                    String msg = tmp[3].split("\"")[1];

                    if (cmd.contains("-l") && !cmd.contains("-m")){
                        ms.listenUDP();
                    }
                    else {
                        ms.sendUDP(serverAddr, msg);
                    }
                }
                else {
                    System.out.println("Invalid address. Try again.");
                }

            }
            else {

            }

        }
    }

    private void sendTCP(String serverAddr, String msg) {

        String host = serverAddr.split(":")[0];
        String port = serverAddr.split(":")[1];

        System.out.println("Sending TCP message to the " + host + ":" + port);

        try {
            Socket s = new Socket(host, Integer.parseInt(port));
            DataOutputStream toServer = new DataOutputStream(s.getOutputStream());
            toServer.writeBytes(msg);
        } catch (IOException e) {
            System.err.println("Mostly probable Server is down.");
            //e.printStackTrace();
        }
    }

    private void sendUDP(String serverAddr, String msg) {

        String host = serverAddr.split(":")[0];
        String port = serverAddr.split(":")[1];

        byte[] sendData = msg.getBytes();

        System.out.println("Sending UDP message to the " + host + ":" + port);

        try {
            InetAddress IPAddress = InetAddress.getByName(host);
            DatagramSocket clientSocket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(port));

            clientSocket.send(sendPacket);
            clientSocket.close();
        } catch (Exception e) {
            System.err.println("Mostly probable Server is down.");
            //e.printStackTrace();
        }
    }

    public void lintenTCP() {

        System.out.println("Starting to listen for TCP messages");

    }

    public void listenUDP() {

    }
}
