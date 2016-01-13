package generation;

import contributors.workers.DataSupplier;
import debug.DebugLevel;
import debug.Logger;
import generation.configuration.Configuration;
import generationData.types.ClassedObject;
import generationData.types.systems.DependencyOrderer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Laurens on 5-1-2016.
 */
public class Creator {

    public static final String INITIALIZE_NAME = "initialize";
    private Configuration configuration;

    public Creator(Configuration configuration) {
        this.configuration = configuration;
    }

    public void start(){
        Runnable runnable = configuration.getDataIsland().getSystems().get(Runnable.class);
        if(runnable==null){
            Logger.getLogger().d(DebugLevel.ERROR,"Creator","No runnable to start generation found");
            System.exit(0);
        }
        List<DataSupplier> suppliers = order();//Order the suppliers to solve dependencies.
        initializeDataSuppliers(suppliers);


    }

    private void initializeDataSuppliers(List<DataSupplier> suppliers) {
        for(DataSupplier supplier : suppliers){//Initialize the data suppliers with the parameters given by the Configuration.
            invoke(supplier,INITIALIZE_NAME,configuration.getParameters(),configuration.getValues());
            List<ClassedObject> classedObjects = supplier.get();
            configuration.getDataIsland().getInputData().addAll(classedObjects);
        }
    }

    private Object invoke(Object object, String methodName, Class[] declaredParameters, Object[] parameterValues){
        try {
            Method m = object.getClass().getMethod(methodName, declaredParameters);
            return m.invoke(object,parameterValues);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            Logger.getLogger().d(DebugLevel.ERROR,"Creator",e.toString());
        }
        return null;
    }

    private List<DataSupplier> order() {
        List<DataSupplier> suppliers = configuration.getDataIsland().getWorkers().get(DataSupplier.class);
        DependencyOrderer orderer = configuration.getDataIsland().getSystems().get(DependencyOrderer.class);
        orderer.order(suppliers);
        return suppliers;
    }


}
