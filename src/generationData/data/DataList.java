package generationData.data;

import debug.DebugLevel;
import debug.Logger;
import generation.configuration.Configuration;
import generationData.types.ClassedObject;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Laurens on 11-1-2016.
 */
public class DataList<T>{

    private HashMap<Class<?>,List<T>> data = new HashMap<>();
    private Class<T> tClass;

    public DataList(Class<T> tClass){
        this.tClass = tClass;
    }

    public<T> List<T> get(Class<T> clazz){
        try {
            return (List<T>) data.get(clazz);
        } catch (ClassCastException e) {
            return null;
        }
    }

    HashMap<Class<?>, List<T>> getData() {
        return data;
    }

    public void add(Class<?> clazz,T t){
        if(!clazz.isInstance(t)){
            Logger.getLogger().d(DebugLevel.INFO.ERROR,"DataList", "Did not add "+t+" to collection "+ t + " is not an instance of "+ clazz.getName());
            return;//You have to be an instance of the type you say you want to be found under
        }
        List<T> list = data.get(clazz);
        if(list==null){
            list = new ArrayList<>();
            data.put(clazz,list);
        }else{
            if(list.contains(t))return;//We only want one instance of each object in the list, else strange things can happen
        }
        list.add(t);
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

    public int size(){
        int size = 0;
        for(List<T> l : data.values()){
            size+=l.size();
        }
        return size;
    }

    public static void main(String[] args) {

    }

}
