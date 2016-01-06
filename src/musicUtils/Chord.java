package musicUtils;

import java.util.Arrays;

public enum Chord {

    MAJOR(new int[]{0, 4, 7}, "", 1),
    MINOR(new int[]{0, 3, 7}, "m", 2),
    FIVE(new int[]{0, 7}, "5", 3),
    SUSPENDED_TWO(new int[]{0, 2, 7}, "sus2", 4),
    SUSPENDED_FOUR(new int[]{0, 5, 7}, "sus4", 5),
    DOMINANT_SEVENTH(new int[]{0, 4, 7, 10}, " 7", 6),
    MAJOR_SIXTH(new int[]{0, 4, 7, 9}, "maj6", 7),
    AUGMENTED(new int[]{0, 4, 8}, "aug", 8),
    DIMINISHED(new int[]{0, 3, 6}, "dim", 9),
    SIXTH_9TH(new int[]{0, 4, 7, 9, 2}, "69", 10),
    MINOR_SIXTH(new int[]{0, 3, 7, 9}, "min6", 11),
    MINOR_SEVENTH(new int[]{0, 3, 7, 10}, "m7", 12),
    MAJOR_SEVENTH(new int[]{0, 4, 7, 11}, "maj7", 13),
    SEVENTH_FLAT_FIVE(new int[]{0, 4, 6, 10}, "7b5", 14),
    AUGMENTED_SEVEN(new int[]{0, 4, 8, 10}, "aug7", 15),
    HALF_DIMINISHED(new int[]{0, 3, 6, 10}, "m7b5", 16),
    SEVENTH_FLAT_9TH(new int[]{0, 4, 7, 10, 1}, "7b9", 17),
    NINETH(new int[]{0, 4, 7, 10, 2}, "9", 18),
    MINOR_NINETH(new int[]{0, 3, 7, 10, 2}, "m9", 19),
    MAJOR_NINETH(new int[]{0, 4, 7, 11, 2}, "maj9", 20),
    ADDED_NINETH(new int[]{0, 4, 7, 2}, "add9", 21),
    MINOR_MAJOR_SEVENTH(new int[]{0, 3, 7, 11}, "mM7", 22);

    private int[] notes;
    private String representation;
    private int ranking;

    private Chord(int[] notes, String representation, int ranking) {
        this.notes = notes;
        this.representation = representation;
        this.ranking = ranking;
    }

    public int[] getNotes() {
        Arrays.sort(notes);
        return notes;
    }

    public String getRepresentation() {
        return representation;
    }

    /**
     * @return the ranking
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

}
