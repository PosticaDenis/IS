package decoder;

import java.util.Hashtable;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class AbstractFactoryCache {

    private static Hashtable<String, AbstractFactory> factoryMap  = new Hashtable<String, AbstractFactory>();

    public static AbstractFactory getFactory(String factoryId) {
        AbstractFactory cachedShape = factoryMap.get(factoryId);
        return (AbstractFactory) cachedShape.clone();
    }

    public static void loadCache() {

        DataAnalysisUtilsFactory dataAnalysisUtilsFactory = new DataAnalysisUtilsFactory();
        dataAnalysisUtilsFactory.setType("ANALYSIS");
        factoryMap.put(dataAnalysisUtilsFactory.getType(), dataAnalysisUtilsFactory);

        DataProcessingUtilsFactory dataProcessingUtilsFactory = new DataProcessingUtilsFactory();
        dataProcessingUtilsFactory.setType("PROCESSING");
        factoryMap.put(dataProcessingUtilsFactory.getType(), dataProcessingUtilsFactory);
    }
}
