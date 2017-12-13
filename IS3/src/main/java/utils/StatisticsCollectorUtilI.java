package utils;

import com.google.common.base.CharMatcher;
import decoder.IDataAnalysisUtil;

import java.util.List;
import java.util.Set;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class StatisticsCollectorUtilI implements IDataAnalysisUtil {

    private String language;
    private List<String> allCombinations;
    private Set<String> dictionary;
    private String stats;

    public StatisticsCollectorUtilI() {
    }

    public void generateData() {

        int stat = 0;
        int key = 0;

        int tmpStat;
        int counter = -1;

        for (String combination: allCombinations) {
            counter ++;
            tmpStat =0;

            String charsToRemove = "\uFEFF~!@#$%^&*1234567890-_=+[{]};:'|?/>.<,";

            String filtered = CharMatcher.anyOf(charsToRemove).removeFrom(combination);

            String[] words = filtered.split("\\s+");

            for (String word: words) {

                if (!CombinationsUtil.isIsCyrillic()) {
                    if (dictionary.contains(word.toLowerCase())) {
                        tmpStat += word.length();
                    }
                } else {

                    for (String w: dictionary) {
                        if (w.equals(word.toLowerCase())) {
                            tmpStat += word.length();
                        }
                    }
                }
            }

            if (stat < tmpStat) {

                key = counter;
                stat = tmpStat;
            }
        }

        stats = stat + ":" + key + ":" + language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDictionary(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public String getStats() {
        return stats;
    }

    public void setAllCombinations(List<String> allCombinations) {
        this.allCombinations = allCombinations;
    }

    public void setStats(List<String> stats) {}
}
