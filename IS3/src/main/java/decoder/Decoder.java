package decoder;

/**
 * Created by Dennis on 02-Dec-17.
 **/
public class Decoder {

    private static Decoder instance = null;

    private static String decodedMsg;
    private static int cipherKey;
    private String msgPath = "";

    private static AbstractFactory dataAnalysisUtilsFactory;// = FactoryProducer.getFactory("ANALYSIS");
    private static AbstractFactory dataProcessingUtilsFactory;// = FactoryProducer.getFactory("PROCESSING");

    private Decoder() {

        AbstractFactoryCache.loadCache();
        dataAnalysisUtilsFactory = AbstractFactoryCache.getFactory("ANALYSIS");
        dataProcessingUtilsFactory = AbstractFactoryCache.getFactory("PROCESSING");
    }

    public static Decoder getInstance() {
        if(instance == null) {
            instance = new Decoder();
        }
        return instance;
    }

    public void decode() {

        //dataAnalysisUtilsFactory = FactoryProducer.getFactory("ANALYSIS");
        //dataProcessingUtilsFactory = FactoryProducer.getFactory("PROCESSING");


        IDataProcessingUtil fileReaderUtil = dataProcessingUtilsFactory.getProcessingUtil("FILER");//new FileReaderUtil(msgPath);
        fileReaderUtil.setMsgPath(msgPath);
        fileReaderUtil.process();

        IDataProcessingUtil combinationsUtil = dataProcessingUtilsFactory.getProcessingUtil("COMBINATIONS");//new CombinationsUtil(fileReaderUtil.getMessage());
        combinationsUtil.setMessage(fileReaderUtil.getMessage());
        combinationsUtil.process();

        IDataAnalysisUtil dictionaryUtil = dataAnalysisUtilsFactory.getDataAnalysisUtil("DICTIONARY");
        dictionaryUtil.setAllCombinations(combinationsUtil.getAllCombinations());
        dictionaryUtil.generateData();

        //new DictionaryUtil(combinationsUtil.getAllCombinations());
    }

    public void setMsgPath(String msgPath) {
        this.msgPath = msgPath;
    }

    public static void setDecodedMsg(String decodedMsg) {
        Decoder.decodedMsg = decodedMsg;
    }

    public static void setCipherKey(int cipherKey) {
        Decoder.cipherKey = cipherKey;
    }

    public static String getDecodedMsg() {
        return decodedMsg;
    }

    public static int getCipherKey() {
        return cipherKey;
    }

    public static IDataAnalysisUtil getDataAnalysisUtil(String type) {
        return dataAnalysisUtilsFactory.getDataAnalysisUtil(type);
    }

    public static IDataProcessingUtil getDataProcessingUtil(String type) {
        return dataProcessingUtilsFactory.getProcessingUtil(type);
    }
}
