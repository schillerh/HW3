package accountManager.controller;
import accountManager.model.Model;
import accountManager.view.View;

/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	void setModel(Model model);
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	Model getModel();
	
	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	View getView();
	
	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	void setView(View view);
}
