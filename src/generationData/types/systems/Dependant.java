package generationData.types.systems;

import java.util.List;

/**
 * Created by Laurens on 13-1-2016.
 */
public interface Dependant {

    List<Class<?>> dependantOn();
}
