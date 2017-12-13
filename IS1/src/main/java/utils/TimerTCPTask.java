package utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class TimerTCPTask extends Thread implements ITimerTask {

    private String i = "0";
    private String max = "0";

    private String serverAddr;
    private String msg;
    private Socket s;
    private DataOutputStream toServer;

    public TimerTCPTask(List<String> conf) {

        this.i = conf.get(0);
        this.max = conf.get(1);
        this.serverAddr = conf.get(2);
        this.msg = conf.get(3);

        setUp();
    }

    private void setUp() {
        String host = serverAddr.split(":")[0];
        String port = serverAddr.split(":")[1];

        try {
            s = new Socket(host, Integer.parseInt(port));

            Thread.sleep(500);

            toServer = new DataOutputStream(s.getOutputStream());

        } catch (Exception e) {
            System.err.println("Mostly probable Server is down.");
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

            sendTCP();
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

        try {
            s.close();
        } catch (IOException e) {
            System.out.println("Error closing the socket to the server.");
            e.printStackTrace();
        }
    }

    private void sendTCP() {

        //System.out.println("Sending TCP message to the " + serverAddr);

        try {
            toServer.writeBytes(msg + "\r");
        } catch (IOException e) {
            System.err.println("Mostly probable Server is down.");
        }
    }
}
