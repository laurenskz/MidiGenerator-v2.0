package generation.configuration;

import contributors.Contributor;
import generationData.data.Data;
import generationData.data.DataList;

/**
 * Created by Laurens on 13-1-2016.
 */
public class DataIsland {

    private DataList<Contributor> workers = new DataList<>(Contributor.class);
    private Data<Contributor> systems = new Data<>(Contributor.class);
    private DataList<Object> inputData = new DataList<>(Object.class);
    private DataList<Object> generationData = new DataList<>(Object.class);

    public DataList<Contributor> getWorkers() {
        return workers;
    }

    public Data<Contributor> getSystems() {
        return systems;
    }

    public DataList<Object> getInputData() {
        return inputData;
    }

    public DataList<Object> getGenerationData() {
        return generationData;
    }

    public int size(){
        return workers.size()+systems.size()+inputData.size()+generationData.size();
    }
}
