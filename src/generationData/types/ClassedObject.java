package generationData.types;

/**
 * Created by Laurens on 13-1-2016.
 */
public class ClassedObject {

    private Class<?> clazz;
    private Object object;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
