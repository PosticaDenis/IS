package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by c-denipost on 11-Dec-17.
 **/
public class TCPServer extends Thread {

    private Socket s;

    public TCPServer(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        String clientSentence;

        while (true) {

            try {
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
                clientSentence = inFromClient.readLine();

                if (clientSentence != null) {
                    System.out.println("[TCP]RECEIVED: " + clientSentence);
                }
            } catch (IOException e) {

                //e.printStackTrace();
                System.out.println("A client disconnected.");

                try {
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                return;
            }
        }
    }
}
