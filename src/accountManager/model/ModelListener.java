package accountManager.model;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving model events.
 * The class that is interested in processing a model
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addModelListener<code> method. When
 * the model event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ModelEvent
 */
public interface ModelListener {
	
	/**
	 * Model changed.
	 *
	 * @param event The event that has been changed
	 */
	public void modelChanged(ModelEvent event);
}
