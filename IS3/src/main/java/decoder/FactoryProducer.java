package decoder;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){

        if(choice.equalsIgnoreCase("ANALYSIS")){
            return new DataAnalysisUtilsFactory();

        }else if(choice.equalsIgnoreCase("PROCESSING")){
            return new DataProcessingUtilsFactory();
        }

        return null;
    }
}
