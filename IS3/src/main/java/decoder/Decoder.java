package decoder;

import utils.DictionaryUtil;
import utils.FileReaderUtil;
import utils.CombinationsUtil;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class Decoder {

    private static String decodedMsg;
    private static int cipherKey;

    public Decoder(String msgPath) {

        FileReaderUtil fileReaderUtil = new FileReaderUtil(msgPath);
        CombinationsUtil combinationsUtil = new CombinationsUtil(fileReaderUtil.getMessage());
        new DictionaryUtil(combinationsUtil.getAllCombinations());
    }

    public static void setDecodedMsg(String decodedMsg) {
        Decoder.decodedMsg = decodedMsg;
    }

    public static void setCipherKey(int cipherKey) {
        Decoder.cipherKey = cipherKey;
    }

    public static String getDecodedMsg() {
        return decodedMsg;
    }

    public static int getCipherKey() {
        return cipherKey;
    }
}
