import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import decoder.Decoder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import utils.FileReaderUtil;
import utils.ShiftUtil;

import java.io.IOException;

/**
 * Created by c-denipost on 01-Dec-17.
 **/
public class TestDecryption {

    @Test
    public void testShiftUtil() {

        ShiftUtil shiftUtil = new ShiftUtil();

        assertFalse(shiftUtil.shift("Привет!", "cyrillic", 7).equals("Привет!"));
        assertTrue(shiftUtil.shift("Hello Darkness, my old friend!", "latin", 25).equals("Gdkkn Czqjmdrr, lx nkc eqhdmc!"));
    }

    @Test
    public void testDecoder() {

        String[] msg = {"m1.txt", "m2.txt", "m3.txt", "m4.txt", "m5.txt"};


        for (String m: msg) {
            new Decoder(m);

            try {
                String decryptedMsg = FileUtils.readFileToString(FileUtils.getFile("src","test", "resources", "messages", m), Charsets.UTF_8);
                assertEquals(Decoder.getDecodedMsg(), decryptedMsg);
                System.out.println("Key: " + Decoder.getCipherKey() + ";\nMessage: " + Decoder.getDecodedMsg() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
