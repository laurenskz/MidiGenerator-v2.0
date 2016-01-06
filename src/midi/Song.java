package midi;

import generalUtils.ByteUtils;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laurens on 28-12-2015.
 */
public class Song {


    public static final int MICROSECONDS_QUARTER_NOTE = 60000000;
    public static final int TIMESTAMP = 9600;
    /**
     * The sequence which holds all data
     */
    private Sequence sequence;
    /**
     * A miditrack containing many meta events (like tempo etc)
     */
    private Track metaTrack;
    /**
     * A List containing all the tracks in this song.
     */
    private List<Track> tracks = new ArrayList<>();

    public Song() {
        initialize();
    }

    public Song(Sequence sequence) {
        for (javax.sound.midi.Track track : sequence.getTracks()) {
            tracks.add(new Track(track));
        }
    }

    private void initialize() {
        try {
            sequence = new Sequence(Sequence.PPQ, TIMESTAMP);
            metaTrack = getNewTrack();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public Track getNewTrack() {
        Track track = new Track(sequence.createTrack());
        tracks.add(track);
        return track;
    }

    public void save(String fileName) {
        try {
            int[] types = MidiSystem.getMidiFileTypes(sequence);
            int type = 0;
            if (types.length == 2 || types[0] == 1) {
                type = 1;
            }
            MidiSystem.write(sequence, type, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTempo(float tempo) {
        int microSecondsQuarterNote = Math.round(MICROSECONDS_QUARTER_NOTE / tempo);
        byte[] data = ByteUtils.stripZeroBytes(microSecondsQuarterNote);
        try {
            MetaMessage metaMessage = new MetaMessage(0x51, data, data.length);
            metaTrack.add(new MidiEvent(metaMessage, 0));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }


    public void lockNotes(Object key) {
        for (Track track : tracks) {
            track.lockNotes(key);
        }
    }

    public void unlockNotes(Object key) {
        for (Track track : tracks) {
            track.unlockNotes(key);
        }
    }

    public void play() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            try {
                sequencer.open();
                sequencer.setSequence(sequence);
                sequencer.start();
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Song song = new Song();
        song.setTempo(87f);
        Track two = song.getNewTrack();
        two.setInstrument(52);
        fill(two);
        song.play();
        song.save("bab.mid");
    }

    private static void fill(Track track) {
        float volume = 127f / 27;
        for (int i = 0; i < 27; i++) {
            Note note = new Note();
            note.setStartTime(i * TIMESTAMP);
            note.setEndTime((i + 1) * TIMESTAMP);
            note.setPitch(i + 50);
            note.setVolume(Math.round(79));
            track.add(note);
        }
    }


}
