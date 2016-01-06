package contributors.workers;

/**
 * Created by Laurens on 5-1-2016.
 */
public interface Rater extends Worker {


    /**
     * The weight symbolizes how important this rater is.
     *
     * @return the weight of this Rater
     */
    int getWeight();

    /**
     * @param weight the weight of this Rater to set.
     */
    void setWeight(int weight);
}
