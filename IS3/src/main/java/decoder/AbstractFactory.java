package decoder;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public abstract class AbstractFactory {

    abstract IDataProcessingUtil getProcessingUtil(String purpose);
    abstract IDataAnalysisUtil getDataAnalysisUtil(String purpose) ;
}
