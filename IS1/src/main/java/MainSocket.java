import utils.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

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

            if (cmd.contains("-m") && !cmd.contains("-l")) {
                //expected format: -tp [host:port] -m ["message"] (-l) (-i 1) (-max 10)

                String[] tmp = cmd.split(" ");
                String serverAddr = tmp[1];

                if (IpCheckerUtil.validate(serverAddr)) {

                    String msg = tmp[3].split("\"")[1];
                    List<String> conf = new ArrayList<String>();
                    String i = "0";
                    String max = "1";

                    if (cmd.contains("-i") && cmd.contains("-max")){

                        i = tmp[5];
                        max = tmp[7];


                    }

                    conf.add(i);
                    conf.add(max);
                    conf.add(serverAddr);
                    conf.add(msg);

                    ITimerTask timer;

                    if (cmd.startsWith("-tp")){
                         timer = new TimerTCPTask(conf);
                    }
                    else {
                        timer = new TimerUDPTask(conf);
                    }

                    timer.run();

                }
                else {
                    System.out.println("Invalid address. Try again.");
                }

            }
            else if (cmd.contains("-l")) {
                String[] tmp = cmd.split(" ");
                String serverAddr = tmp[1];

                if (IpCheckerUtil.validate(serverAddr)) {

                    if (cmd.contains("-tp")){
                        ms.lintenTCP(serverAddr);
                    }
                    if (cmd.contains("-up")){
                        ms.listenUDP(serverAddr);
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

    public void lintenTCP(String serverAddr) {

        String port = serverAddr.split(":")[1];

        System.out.println("Starting to listen for TCP messages");

        TCPConnectionAccepter connectionAccepter = new TCPConnectionAccepter(port);
        connectionAccepter.start();
    }

    public void listenUDP(String serverAddr) {

        String port = serverAddr.split(":")[1];

        System.out.println("Starting to listen as UDP server.");

        UDPServer udpServer = new UDPServer(port);
        udpServer.start();
    }
}
