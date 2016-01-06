package midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Laurens on 28-12-2015.
 */
public class Track {

    /**
     * The track which holds our notes, it is also in the javax.sound.midi.Sequence.
     */
    private javax.sound.midi.Track midiTrack;

    /**
     * The list which holds the notes as some kind of metadata. It is very useful to contributors.
     */
    private List<Note> notes = new ArrayList<>();

    /**
     * The channel on which this track plays.
     */
    private int channel = 0;

    private static int CHANNEL = 0;

    /**
     * Constructs a new Track, which is essentially a wrapper for a javax.sound.midi.Track. If the miditrack already contains note events these will be added to
     * This objects List of Notes.
     *
     * @param midiTrack
     */
    public Track(javax.sound.midi.Track midiTrack) {
        this.midiTrack = midiTrack;
        channel = CHANNEL++;
        notes.addAll(getExistingNotes(midiTrack));
    }

    public boolean add(MidiEvent event) {
        return midiTrack.add(event);
    }

    /**
     * Sets the instrument of this track(channel at 0). For more information about available instruments see : http://www.midi.org/techspecs/gm1sound.php.
     * NOTE : Indici in this table have to be decremented by 1.
     *
     * @param instrument
     */
    public void setInstrument(int instrument) {
        try {
            ShortMessage message = new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
            add(new MidiEvent(message, 0));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void lockNotes(Object key) {
        for (Note note : notes) {
            note.lock(key);
        }
    }

    public void unlockNotes(Object key) {
        for (Note note : notes) {
            note.unlock(key);
        }
    }

    public boolean add(Note note) {
        notes.add(note);
        try {
            ShortMessage onMessage = new ShortMessage(ShortMessage.NOTE_ON, channel, note.getPitch(), note.getVolume());
            ShortMessage offMessage = new ShortMessage(ShortMessage.NOTE_OFF, channel, note.getPitch(), note.getVolume());
            MidiEvent onEvent = new MidiEvent(onMessage, note.getStartTime());
            MidiEvent offEvent = new MidiEvent(offMessage, note.getEndTime());
            return add(onEvent) && add(offEvent);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<MidiEvent> getNormalEvents(javax.sound.midi.Track track) {
        if (track == null) {
            return null;
        }
        ArrayList<MidiEvent> listToReturn = new ArrayList<>();
        for (int i = 0; i < track.size(); i++) {
            MidiEvent tempMidiEvent = track.get(i);
            if (tempMidiEvent.getMessage() instanceof ShortMessage) {
                listToReturn.add(tempMidiEvent);
            }
        }
        return listToReturn;
    }

    public static ArrayList<Note> getExistingNotes(javax.sound.midi.Track track) {
        //The hashMap to which note on events will be added
        HashMap<Integer, Note> unclosedNotes = new HashMap<>();
        //The arrayList containing all note data
        ArrayList<Note> allNotes = new ArrayList<>();
        if (track == null)
            return allNotes;//Just return an empty arraylist because we don't want to make other parts crash easily
        for (MidiEvent event : getNormalEvents(track)) {
            long eventTime = event.getTick();
            ShortMessage shortMessage = (ShortMessage) event.getMessage();
            int velocity = shortMessage.getData2();

            if (isNoteOn(shortMessage, velocity)) {
                onNoteOn(unclosedNotes, eventTime, shortMessage, velocity);
            }
            if (isNoteOff(shortMessage, velocity)) {
                onNoteOff(unclosedNotes, allNotes, eventTime, shortMessage);
            }
        }
        return allNotes;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    private static void onNoteOff(HashMap<Integer, Note> unclosedNotes, ArrayList<Note> allNotes, long eventTime, ShortMessage shortMessage) {
        int note = shortMessage.getData1();
        Note noteToAddEndTime = unclosedNotes.get(note);
        unclosedNotes.remove(note);
        if (noteToAddEndTime != null) {
            noteToAddEndTime.setEndTime(eventTime);
            allNotes.add(noteToAddEndTime);
        }
    }

    private static void onNoteOn(HashMap<Integer, Note> unclosedNotes, long eventTime, ShortMessage shortMessage, int velocity) {
        int note = shortMessage.getData1();
        Note newNote = new Note();
        newNote.setPitch(note);
        newNote.setStartTime(eventTime);
        newNote.setVolume(velocity);
        unclosedNotes.put(note, newNote);
    }

    private static boolean isNoteOff(ShortMessage shortMessage, int velocity) {
        return shortMessage.getCommand() == ShortMessage.NOTE_OFF || (shortMessage.getCommand() == ShortMessage.NOTE_ON && velocity == 0);
    }

    private static boolean isNoteOn(ShortMessage shortMessage, int velocity) {
        return shortMessage.getCommand() == ShortMessage.NOTE_ON && velocity > 0;
    }

}
