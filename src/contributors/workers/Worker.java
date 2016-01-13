package contributors.workers;

import generationData.data.Data;
import generationData.data.DataList;

/**
 * A worker is a participant in a song generation.
 * Created by Laurens on 6-1-2016.
 */
public interface Worker {

    /**
     *
     * @param data The data on which this simulation will be based.
     * @return
     */
    boolean initialize(DataList<Object> data);
}
