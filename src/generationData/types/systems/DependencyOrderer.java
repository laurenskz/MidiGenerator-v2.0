package generationData.types.systems;

import java.util.List;

/**
 * Created by Laurens on 10-1-2016.
 * The task of this interface is to order the datasuppliers, some DataSuppliers may need objects initialized by other dataSuppliers
 * For example one DataSupplier may generate a collection of all the notes which play at the same time. Another DataSupplier may need
 * this to give the name of those chords. Or one DataSupplier may supply a connection to a database. This interface specializes the order
 * of the initializing of data and thus to what data DataSuppliers have access when being initialized.
 */
public interface DependencyOrderer {

    void order(List<? extends Dependant> dataSuppliers);
}
