package generationData.data;

import debug.DebugLevel;
import debug.Logger;
import generationData.types.ClassedObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Laurens on 11-1-2016.
 */
public class Data<T> {

    private HashMap<Class<?>,T> data = new HashMap<>();
    private Class<T> tClass;

    public Data(Class<T> tClass){
        this.tClass = tClass;
    }
    public<T> T get(Class<T> clazz){
        try {
            return (T) data.get(clazz);
        } catch (ClassCastException e) {
            return null;
        }
    }

    HashMap<Class<?>, T> getData() {
        return data;
    }

    public void add(ClassedObject classedObject){
        if(classedObject==null)return;
        if(!tClass.isInstance(classedObject)){
            return;
        }
        add(classedObject.getClazz(), tClass.cast(classedObject.getObject()));
    }

    public void addAll(List<ClassedObject> list){
        if(list==null)return;
        for(ClassedObject classedObject : list){
            add(classedObject);
        }
    }

    public void add(Class<?> clazz,T t){
        if(!clazz.isInstance(t)){
            Logger.getLogger().d(DebugLevel.INFO.ERROR,"Data", "Did not set "+t+", "+ t + " is not an instance of "+ clazz.getName());
            return;
        }
        data.put(clazz,t);
    }

    public int size(){
        return data.size();
    }
}
