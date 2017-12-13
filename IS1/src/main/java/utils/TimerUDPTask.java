package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class TimerUDPTask extends Thread implements ITimerTask {

    private String i = "0";
    private String max = "0";

    private String serverAddr;
    private String msg;
    private DatagramSocket clientSocket;
    private DatagramPacket sendPacket;

    public TimerUDPTask(List<String> conf) {

        this.i = conf.get(0);
        this.max = conf.get(1);
        this.serverAddr = conf.get(2);
        this.msg = conf.get(3);

        setUp();
    }

    private void setUp() {
        String host = serverAddr.split(":")[0];
        String port = serverAddr.split(":")[1];

        byte[] sendData = msg.getBytes();

        try {
            InetAddress IPAddress = InetAddress.getByName(host);
            clientSocket = new DatagramSocket();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(port));
        } catch (Exception e) {
            System.err.println("Mostly probable Server is down.11");
        }
    }

    @Override
    public void run() {

        int i = 0;
        int max = 0;
        try {
            i = Integer.parseInt(this.i);
            max = Integer.parseInt(this.max);
        }
        catch (Exception e) {
            System.out.println("You introduced an invalid value for interval and/or counter!");
            closeS();
            return;
        }

        while (max > 0) {

            sendUDP();
            max --;

            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        closeS();
        return;
    }

    private void closeS() {
        clientSocket.close();
    }

    private void sendUDP() {

        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
