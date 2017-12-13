package decoder;

import utils.*;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public class DataProcessingUtilsFactory extends AbstractFactory{

    @Override
    public IDataProcessingUtil getProcessingUtil(String dataProcessingUtilType) {
        if(dataProcessingUtilType == null){
            return null;
        }

        if(dataProcessingUtilType.equalsIgnoreCase("COMBINATIONS")){
            return new CombinationsUtil();

        }else if(dataProcessingUtilType.equalsIgnoreCase("FILER")){
            return new FileReaderUtil();

        }else if(dataProcessingUtilType.equalsIgnoreCase("SHIFT")){
            return new ShiftUtil();
        }

        return null;
    }

    @Override
    IDataAnalysisUtil getDataAnalysisUtil(String purpose) {
        return null;
    }


}
