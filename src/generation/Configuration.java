package generation;

import contributors.workers.Rater;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
    public Configuration(int x,int y, int z) {
        System.out.printf("x = %d y = %d z = %d", x, y, z);
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

    private boolean invoke(Object object, String methodName, Class[] declaredParameters, Object[] parameterValues){
        try {
            Method m = Configuration.class.getMethod("sayYes",declaredParameters);
            m.invoke(object,parameterValues);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Object[] yeah = new Object[]{17,"Bab"};
        Class[] swag = new Class[]{Integer.TYPE,String.class};
        try {
            Method m = Configuration.class.getMethod("sayYes",swag);
            System.out.println(m);
            m.invoke(new Configuration(),yeah);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
