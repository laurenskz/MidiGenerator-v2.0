package contributors.workers;

import midi.Song;

/**
 * A worker is a participant in a song generation.
 * Created by Laurens on 6-1-2016.
 */
public interface Worker {

    /**
     * @param existingSongs The songs which already exist on which the new song will be based.
     * @return true if this worker still wishes to participate in the generation. False if it wishes to stop.
     */
    boolean initialize(Song[] existingSongs);
}
