package utils;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.util.List;

import decoder.IDataProcessingUtil;
import org.apache.commons.io.FileUtils;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class FileReaderUtil implements IDataProcessingUtil {

    private String msgPath;
    private String message;

    public void process() {
        try {
            message = FileUtils.readFileToString(FileUtils.getFile("src","main", "resources", "messages", msgPath), Charsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No message file found.");
            e.printStackTrace();
        }
    }

    public void setMsgPath(String msgPath) {
        this.msgPath = msgPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {}

    public List<String> getAllCombinations() {
        return null;
    }
}
