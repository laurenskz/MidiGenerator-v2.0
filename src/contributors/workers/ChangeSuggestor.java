package contributors.workers;

import midi.Song;

import java.util.Collection;

/**
 * Created by Laurens on 6-1-2016.
 */
public interface ChangeSuggestor extends Worker {

    Collection<ChangeSuggestor> getChangeSuggestions(Song song);
}
