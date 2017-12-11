package utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class FileReaderUtil {

    private String message;

    public FileReaderUtil(String msgPath) {
        try {
            message = FileUtils.readFileToString(FileUtils.getFile("src","main", "resources", "messages", msgPath), Charsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No message file found.");
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }
}
