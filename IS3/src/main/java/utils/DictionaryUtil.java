package utils;

import com.google.common.annotations.Beta;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class DictionaryUtil {

    private static List<String> availableLanguages = Arrays.asList("eng", "ro", "ru");

    private URL url = this.getClass().getClassLoader().getResource("languages");

    private StatsAnalyzerUtil statsAnalyzerUtil;

    public DictionaryUtil(List<String> allCombinations) {

        //availableLanguages = new ArrayList<String>();
        //setAvailableLanguages();
        statsAnalyzerUtil = new StatsAnalyzerUtil(allCombinations);

        analyze(allCombinations);
    }

    private void analyze(List<String> allCombinations) {

        List<String> stats = new ArrayList<String>();

        for (String lang: availableLanguages) {

            StatisticsCollectorUtil statisticsCollectorUtil = new StatisticsCollectorUtil(lang, allCombinations, getDictionary(lang));
            stats.add(statisticsCollectorUtil.generateStats());
        }

        statsAnalyzerUtil.process(stats);
    }

    @Beta
    private void setAvailableLanguages() {

        try {
            File directory = new File(url.toURI());

            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isFile()) {
                    availableLanguages.add(file.getName().split(".txt")[0]);
                }
            }
        } catch (Exception e) {

            System.out.println("Error reading languages resources!");
            e.printStackTrace();
        }
    }

    private Set<String> getDictionary(String language) {
        Scanner scanner;
        LinkedHashSet<String> dictionary = new LinkedHashSet<String>();

        try {
            scanner = new Scanner(new File(url.toString().substring(5) + "/" + language + ".txt"));


            while (scanner.hasNext()) {
                dictionary.add(scanner.next().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            //System.out.println("Dictionary for language " + language + " was not found.");
            System.err.println("Dictionary for language " + language + " was not found.");
            e.printStackTrace();
        }

        return dictionary;
    }
}
