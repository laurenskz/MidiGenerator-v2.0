package contributors.workers;

import generationData.types.ChangeSuggestion;
import midi.Note;
import midi.Song;

import java.util.Collection;

/**
 * Created by Laurens on 6-1-2016.
 */
public interface ChangeSuggestor extends Worker {

    Collection<ChangeSuggestion<Note>> getChangeSuggestions(Song song);
}
