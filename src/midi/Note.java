package midi;

import contributors.Rater;

/**
 * A note represents a note in a musical piece. It has four properties. A song consists of notes. Notes can be locked and unlocked with a key to make them alterable
 * or unalterable.
 * Created by Laurens on 28-12-2015.
 */
public class Note {


    /**
     * The pitch of the note starts at 21 as a0. For more info go to https://newt.phys.unsw.edu.au/jw/notes.html
     */
    private int pitch;
    /**
     * The startTime in the song this note has. It is identical to a midi note on event.
     */
    private long startTime;
    /**
     * The endTime a note has in the song. It is identical to a midi note off event.
     */
    private long endTime;
    /**
     * The volume of the note, this ranges from 1 to 127
     */
    private int volume;

    /**
     * The key for locking and unlocking this object. When it is locked the values can't be changed. More specifically if key!=null evaluates to true the values cant be changed.
     */
    private Object key = null;


    /**
     * This method can be used to make sure this object cannot be altered. It has to be locked with a key. It can only be unlocked with the same key.
     * After it has been locked the values cannot be altered until it has been unlocked again.
     *
     * @param key the key to lock this Note with
     */
    public void lock(Object key) {
        if (key != null)
            return;//If the note has been locked already its key cannot be altered until it is unlocked again.
        this.key = key;
    }

    /**
     * To unlock the note this method has to be called with the corresponding key. For more information on locking see lock().
     *
     * @param key the key to unlock this note with
     */
    public void unlock(Object key) {
        if (this.key == key) {
            this.key = null;
        }
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        if (key != null) return;
        this.pitch = pitch;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        if (key != null) return;
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        if (key != null) return;
        this.endTime = endTime;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (key != null) return;
        this.volume = volume;
    }
}
