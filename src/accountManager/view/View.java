package accountManager.view;
import accountManager.controller.Controller;
import accountManager.model.Model;

/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	Controller getController();
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	void setController(Controller controller);
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	Model getModel();
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	void setModel(Model model);
}
