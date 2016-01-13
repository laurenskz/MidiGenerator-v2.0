package contributors;

import generation.configuration.Configuration;
import generation.configuration.DataIsland;

/**
 * Created by Laurens on 13-1-2016.
 */
public interface Contributor {

    /**
     * This method will be called at the start of the generation. It will be called at least twice.
     * @return True if all the resources needed to participate in the generation are present at Data Island. False if
     * participation in generation is not desired anymore.
     */
    boolean participate(DataIsland dataIsland);
}
