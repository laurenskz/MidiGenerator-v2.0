package generation;

import contributors.workers.Rater;
import generationData.ChangeSuggestion;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the start of each generation
 * Created by Laurens on 5-1-2016.
 */
public class Configuration {

    /**
     * A List containing all the Contributors which have implemented the Rater Interface for more information: @see contributors.workers.Rater
     */
    private final List<Rater> raters = new ArrayList<>();
    /**
     * A List containing all possible contributors, it contains all the Class objects which participate in the generation.
     */
    private final List<Pair<Class, List>> pairs = new ArrayList<>();


    public Configuration() {
        initializePairs();
    }

    private void initializePairs() {
        pairs.add(new Pair(Rater.class, raters));
    }


    public boolean add(Object o) {
        boolean added = false;
        for (Pair<Class, List> pair : pairs) {
            if (pair.getKey().isInstance(o)) {
                added |= pair.getValue().add(o);
            }
        }
        return added;
    }

    public static void main(String[] args) {

    }

}
