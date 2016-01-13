package generation.configuration;

import contributors.Contributor;
import generationData.data.Data;
import generationData.data.DataList;

/**
 * This class is the start of each generation
 * Created by Laurens on 5-1-2016.
 */
public class Configuration {


    private DataIsland dataIsland = new DataIsland();

    private Class<?>[] parameters;
    private Object[] values;


    public DataIsland getDataIsland() {
        return dataIsland;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public Class<?>[] getParameters() {
        return parameters;
    }

    public void setParameters(Class<?>[] parameters) {
        this.parameters = parameters;
    }
}
