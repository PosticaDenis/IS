package utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class FileReaderUtil {

    private String message;

    public FileReaderUtil(String msgPath) {

        URL url = Resources.getResource(msgPath);
        try {
            message = Resources.toString(url, Charsets.UTF_8);
            //System.out.println("encoded message: " + message);
        } catch (IOException e) {
            System.out.println("No message file found.");
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }
}
