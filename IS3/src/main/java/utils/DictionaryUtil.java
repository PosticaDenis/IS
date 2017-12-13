package utils;

import com.google.common.annotations.Beta;
import decoder.Decoder;
import decoder.FactoryProducer;
import decoder.IDataAnalysisUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class DictionaryUtil implements IDataAnalysisUtil {

    private static List<String> availableLanguages = Arrays.asList("eng", "ro", "ru");
    private URL url = this.getClass().getClassLoader().getResource("languages");
    private IDataAnalysisUtil statsAnalyzerUtil = Decoder.getDataAnalysisUtil("STATSA");
    private IDataAnalysisUtil statisticsCollectorUtil = Decoder.getDataAnalysisUtil("STATSC");
    private List<String> allCombinations;

    public DictionaryUtil() {

        //availableLanguages = new ArrayList<String>();
        //setAvailableLanguages();
        //generateData();
    }

    public void generateData() {

        List<String> stats = new ArrayList<String>();

        for (String lang: availableLanguages) {

            //StatisticsCollectorUtilI statisticsCollectorUtil = new StatisticsCollectorUtilI(lang, allCombinations, getDictionary(lang));
            statisticsCollectorUtil.setLanguage(lang);
            statisticsCollectorUtil.setAllCombinations(allCombinations);
            statisticsCollectorUtil.setDictionary(getDictionary(lang));
            statisticsCollectorUtil.generateData();

            stats.add(statisticsCollectorUtil.getStats());
        }

        //statsAnalyzerUtil = new StatsAnalyzerUtil(allCombinations, stats);
        //statsAnalyzerUtil = Decoder.getDataAnalysisUtil("STATSA");
        statsAnalyzerUtil.setAllCombinations(allCombinations);
        statsAnalyzerUtil.setStats(stats);

        statsAnalyzerUtil.generateData();
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

    public void setAllCombinations(List<String> allCombinations) {
        this.allCombinations = allCombinations;
    }

    public void setStats(List<String> stats) {}
    public void setDictionary(Set<String> dictionary) {}
    public void setLanguage(String language) {}
    public String getStats() {
        return null;
    }
}
