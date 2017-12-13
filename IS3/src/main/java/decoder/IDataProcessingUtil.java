package decoder;

import java.util.List;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public interface IDataProcessingUtil {

    void process();
    void setMsgPath(String msgPath);
    String getMessage();
    void setMessage(String message);
    List<String> getAllCombinations();
}
