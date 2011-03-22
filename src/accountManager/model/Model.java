package accountManager.model;

/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * Notify changed.
	 *
	 * @param e the ModelEvent that is being monitored
	 */
	void notifyChanged(ModelEvent e);
}
