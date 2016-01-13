package contributors.workers;

import generationData.types.ClassedObject;
import generationData.types.systems.Dependant;

import java.util.List;

/**
 * Every worker in the simulation probably wants some sort of data. It would be convenient to allow workers to share their data.
 * Therefore each DataSupplier gets the chance to do this. They get input from the start of the program. This can be anything.
 * A song, array of songs, a file containing some constraints or even the name of a sql db. The DataSupplier then creates a Custom
 * Object. Tells the class with which he wishes other contributors to find it. And then gives the object. NOTE: the given object
 * must be an instance of this class. Else it will not be added to the data.
 *
 * To receive input. A method called "initialize" has to be in the object. At runtime will be checked which methods are present to receive input.
 * A receive method can be called twice. So multiple Objects can be supplied to the data Class. This is because a generation session
 * may receive multiple input types.
 *
 * The created objects will be gotten with the get method.
 *
 * A datasupplier may also supply data for a running generation. If for example a song is being generated a datasupplier may create an
 * object which holds track of the notes sounding together at that current moment.
 *
 * To receive input a method called "update" has to be in the object. This will be checked at runtime and called during the generation.
 * A datasupplier may change its own objects already created.(just hold a reference to it). Also new objects can be created. These will
 * be gotten with the get method.
 *
 * It is recommended to let the finding types be an interface so that multiple input sources can supply instances of this interface.
 * In this way workers can work with very flexible input.
 *
 * Created by Laurens on 7-1-2016.
 */
public interface DataSupplier extends Dependant{


    List<ClassedObject> get();
}
