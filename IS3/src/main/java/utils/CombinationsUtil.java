package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class CombinationsUtil {

    private List<String> allCombinations;
    private ShiftUtil shiftUtil;

    private static boolean isCyrillic = false;

    public CombinationsUtil(String message) {

        allCombinations = new ArrayList<String>();
        shiftUtil = new ShiftUtil();

        calculateCombinations(message);
    }

    private void calculateCombinations(String message) {

        int alphabetLength = 26;
        String alphabet = "latin";

        if (isCyrillic(message)) {
            alphabetLength = 32;
            alphabet = "cyrillic";

            isCyrillic = true;
        }

        for (int i = 0; i < alphabetLength; i++) {
            allCombinations.add(shiftUtil.shift(message, alphabet, i));
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
}
