import tcp.TCPConnectionAccepter;
import udp.UDPServer;
import utils.*;
import utils.timer.ITimerTask;
import utils.timer.TimerTCPTask;
import utils.timer.TimerUDPTask;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by c-denipost on 11-Dec-17.
 **/
public class MainSocket {

    private static TCPConnectionAccepter connectionAccepter = null;
    private static UDPServer udpServer = null;

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
                        ms.listenTCP(serverAddr);
                    }
                    if (cmd.contains("-up")){
                        ms.listenUDP(serverAddr);
                    }
                }
                else {
                    System.out.println("Invalid address. Try again.");
                }
            }
            else if (cmd.contains("scan")) {
                //scan <127.0.0.1> -r 1-65535
                String[] tmp = cmd.split(" ");
                String ip = tmp[1];

                if (cmd.contains("-r")) {
                    int i = Integer.parseInt(tmp[3].split("-")[0]);
                    int j = Integer.parseInt(tmp[3].split("-")[1]);

                    scanPorts(ip, i, j);
                }
                else {
                    scanPorts(ip, 1, 65535);
                }

            }
            else if (cmd.contains("exit")) {
                System.out.println("Shutting down ...");
                System.exit(1);
            }
            else {
                System.out.println("Invalid command. Please try again!");
            }

        }
    }

    public void listenTCP(String serverAddr) {

        String port = serverAddr.split(":")[1];

        System.out.println("Starting to listen for TCP messages");

        if (connectionAccepter == null) {
            connectionAccepter = new TCPConnectionAccepter(port);
            connectionAccepter.start();
        } else {
            System.out.println("You are already a TCP Server.");
        }
    }

    public void listenUDP(String serverAddr) {

        String port = serverAddr.split(":")[1];

        System.out.println("Starting to listen as UDP server.");

        if (udpServer == null) {
            udpServer = new UDPServer(port);
            udpServer.start();
        } else {
            System.out.println("You are already a UDP Server.");
        }
    }

    private static void scanPorts(String ip, int bottm, int top) {
        final ExecutorService es = Executors.newFixedThreadPool(100);
        final int timeout = 200;
        final List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();

        if (top > 65535) {
            top = 65535;
        }

        for (int port = bottm; port <= top; port++) {
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        es.shutdown();
        int openPorts = 0;

        for (final Future<Boolean> f : futures) {
            try {
                if (f.get()) {
                    openPorts++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of " + timeout + "ms)");
    }

    private static Future<Boolean> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
        return es.submit(new Callable<Boolean>() {

            public Boolean call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }
}
