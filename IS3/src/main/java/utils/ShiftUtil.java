package utils;

/**
 * Created by c-denipost on 01-Dec-17.
 *
 * Supports russian language, without Ё,ё
 * Supports latin based languages, without special characters (à, á, â, Ì, Ò, Ù, Î, Ă, Ș, Ț, etc)
 **/
public class ShiftUtil {

    public String shift(String message, String alphabet, int times) {
        String bar = "";

        if (alphabet.equals("cyrillic")) {

            for (char c : message.toCharArray()) {
                if (isCyrillicLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        bar += Character.toString((char) (((c - 'А' + times) % 32) + 'А'));
                    }
                    else {
                        bar += Character.toString((char) (((c - 'а' + times) % 32) + 'а'));
                    }
                }
                else {
                    bar += c;
                }
            }
        }
        else {
            for (char c : message.toCharArray()) {
                if (isLatinLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        bar += Character.toString((char) (((c - 'A' + times) % 26) + 'A'));
                    }
                    else {
                        bar += Character.toString((char) (((c - 'a' + times) % 26) + 'a'));
                    }
                }
                else {
                    bar += c;
                }
            }
        }
        return bar;
    }

    private static boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') ||
                (c >= 'А' && c <= 'Я');
    }

    private static boolean isLatinLetter(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }
}
