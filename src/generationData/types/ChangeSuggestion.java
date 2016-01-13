package generationData.types;

/**
 * Created by Laurens on 6-1-2016.
 */
public class ChangeSuggestion<T> {

    /**
     * The object in the current situation.
     */
    private T current;
    /**
     * The object in the state wished.
     */
    private T desired;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public T getDesired() {
        return desired;
    }

    public void setDesired(T desired) {
        this.desired = desired;
    }
}
