package contributors.workers;

/**
 * Every worker in the simulation probably wants some sort of data. It would be convenient to allow workers to share their data.
 * Therefore each DataSupplier gets the chance to do this. They get input from the start of the program. This can be anything.
 * A song, array of songs, a file containing some constraints or even the name of a sql db. The DataSupplier then creates a Custom
 * Object. Tells the class with which he wishes other contributors to find it. And then gives the object. NOTE: the given object
 * must be an instance of this class. Else it will not be added to the data.
 *
 * To receive input. A method called input has to be in the class. At runtime will be checked which methods are present to receive input.
 * A receive method can be called twice. So multiple Objects can be supplied to the data Class. This is because a generation session
 * may receive multiple input types.
 *
 * It is recommended to let the finding types be an interface so that multiple input sources can supply instances of this interface.
 * In this way workers can work with very flexible input.
 *
 * Created by Laurens on 7-1-2016.
 */
public interface DataSupplier {

    Class getDeclaredType();
    Object getInstance();
}
