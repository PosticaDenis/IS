package decoder;

/**
 * Created by Dennis on 13-Dec-17.
 **/
public abstract class AbstractFactory implements Cloneable {

    protected String type;

    abstract IDataProcessingUtil getProcessingUtil(String purpose);
    abstract IDataAnalysisUtil getDataAnalysisUtil(String purpose) ;

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
