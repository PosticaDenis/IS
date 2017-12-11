package utils;

import decoder.Decoder;

import java.util.List;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class StatsAnalyzerUtil {

    private List<String> combinations;

    public StatsAnalyzerUtil(List<String> combinations) {

        this.combinations = combinations;
    }

    public void process(List<String> stats) {


        String bingo = stats.get(0);

        for (int i = 0; i < stats.size() - 1; i++) {

            if (Integer.parseInt(stats.get(i+1).split(":")[0]) > Integer.parseInt(stats.get(i).split(":")[0])) {

                bingo = stats.get(i+1);
            }
        }

        //String lang = bingo.split(":")[2];
        int cipherKey = Integer.parseInt(bingo.split(":")[1]);
        String decodedMsg = combinations.get(cipherKey);

        Decoder.setCipherKey(cipherKey);
        Decoder.setDecodedMsg(decodedMsg);
    }
}
