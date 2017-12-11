import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import decoder.Decoder;
import org.junit.Test;
import static org.junit.Assert.*;
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

        String[] msg = {"messages/m1.txt", "messages/m2.txt", "messages/m3.txt", "messages/m4.txt", "messages/m5.txt"};


        for (String m: msg) {
            new Decoder(m);

            try {
                String decryptedMsg = Resources.toString(Resources.getResource(m), Charsets.UTF_8);

                assertEquals(Decoder.getDecodedMsg(), decryptedMsg);
                System.out.println("Key: " + Decoder.getCipherKey() + ";\nMessage: " + Decoder.getDecodedMsg() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
