package decoder;

import java.util.List;
import java.util.Set;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public interface IDataAnalysisUtil {

    void generateData();
    void setAllCombinations(List<String> allCombinations);
    void setLanguage(String language);
    void setDictionary(Set<String> dictionary);
    String getStats();
    void setStats(List<String> stats);
}
