package utils;

import decoder.IDataProcessingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class CombinationsUtil implements IDataProcessingUtil {

    private List<String> allCombinations;
    private ShiftUtil shiftUtil;
    private static boolean isCyrillic = false;

    private String message;

    public CombinationsUtil() {

        allCombinations = new ArrayList<String>();
        shiftUtil = new ShiftUtil();

        //process();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void process() {

        int alphabetLength = 26;
        String alphabet = "latin";

        if (isCyrillic(message)) {
            alphabetLength = 32;
            alphabet = "cyrillic";

            isCyrillic = true;
        }

        for (int i = 0; i < alphabetLength; i++) {

            shiftUtil.setMessage(message);
            shiftUtil.setAlphabet(alphabet);
            shiftUtil.setTmes(i);
            shiftUtil.process();
            allCombinations.add(shiftUtil.getShiftedMsg());
        }
    }

    private boolean isCyrillic(String message) {

        for(int i = 0; i < message.length(); i++) {
            if(Character.UnicodeBlock.of(message.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAllCombinations() {
        return allCombinations;
    }

    public static boolean isIsCyrillic() {
        return isCyrillic;
    }

    public String getMessage() {
        return message;
    }

    public void setMsgPath(String msgPath){}
}
