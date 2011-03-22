package accountManager.model;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractModel.
 */
public abstract class AbstractModel implements Model {
	
	/** The listeners. */
	@SuppressWarnings("rawtypes")
	private ArrayList listeners = new ArrayList(20);
	
	/* (non-Javadoc)
	 * @see accountManager.model.Model#notifyChanged(accountManager.model.ModelEvent)
	 */
	@SuppressWarnings("rawtypes")
	public void notifyChanged(ModelEvent event){
		ArrayList list = (ArrayList)listeners.clone();
		Iterator it = list.iterator();
		while(it.hasNext()){
			ModelListener ml = (ModelListener)it.next();
			ml.modelChanged(event);
		}
	}
	
	/**
	 * Adds the model listener.
	 *
	 * @param l the ModelListener l
	 */
	@SuppressWarnings("unchecked")
	public void addModelListener(ModelListener l){
		listeners.add(l);
	}
	
	/**
	 * Removes the model listener.
	 *
	 * @param l the ModelListener l
	 */
	public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
