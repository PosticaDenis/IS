package decoder;

import utils.DictionaryUtil;
import utils.StatisticsCollectorUtilI;
import utils.StatsAnalyzerUtil;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class DataAnalysisUtilsFactory extends AbstractFactory {

    @Override
    public IDataAnalysisUtil getDataAnalysisUtil(String dataAnalysisUtilType) {
        if(dataAnalysisUtilType == null){
            return null;
        }

        if(dataAnalysisUtilType.equalsIgnoreCase("DICTIONARY")){
            return new DictionaryUtil();

        }else if(dataAnalysisUtilType.equalsIgnoreCase("STATSC")){
            return new StatisticsCollectorUtilI();

        }else if(dataAnalysisUtilType.equalsIgnoreCase("STATSA")){
            return new StatsAnalyzerUtil();
        }

        return null;
    }

    @Override
    IDataProcessingUtil getProcessingUtil(String purpose) {
        return null;
    }
}
