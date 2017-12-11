package utils;

/**
 * Created by c-denipost on 11-Dec-17.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpCheckerUtil {

    private static final Pattern p = Pattern.compile("^"
                         + "(((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}" // Domain name
                         + "|"
                         + "localhost" // localhost
                         + "|"
                         + "(([0-9]{1,3}\\.){3})[0-9]{1,3})" // Ip
                         + ":"
                         + "[0-9]{1,5}$"); // Port

    public static boolean validate(final String address){
        Matcher matcher = p.matcher(address);
        return matcher.matches();
    }
}