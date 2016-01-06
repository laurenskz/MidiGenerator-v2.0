package generation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laurens on 5-1-2016.
 */
public class Contributor {

    private Class type;

    public Contributor(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public boolean belongsHere(Object obj) {
        return type.isInstance(obj);
    }
}
