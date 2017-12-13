package utils;

import decoder.IDataAnalysisUtil;
import decoder.Decoder;

import java.util.List;
import java.util.Set;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class StatsAnalyzerUtil implements IDataAnalysisUtil {

    private List<String> allCombinations;
    private List<String> stats;

    public StatsAnalyzerUtil() {
    }

    public void generateData() {

        String bingo = stats.get(0);

        for (int i = 0; i < stats.size() - 1; i++) {

            if (Integer.parseInt(stats.get(i+1).split(":")[0]) > Integer.parseInt(stats.get(i).split(":")[0])) {

                bingo = stats.get(i+1);
            }
        }

        //String lang = bingo.split(":")[2];
        int cipherKey = Integer.parseInt(bingo.split(":")[1]);
        String decodedMsg = allCombinations.get(cipherKey);

        Decoder.setCipherKey(cipherKey);
        Decoder.setDecodedMsg(decodedMsg);
    }

    public void setAllCombinations(List<String> combinations) {
        this.allCombinations = combinations;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }
    public String getStats() {
        return null;
    }
    public void setDictionary(Set<String> dictionary) {}
    public void setLanguage(String language) {}
}
