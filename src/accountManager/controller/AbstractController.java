package accountManager.controller;
import accountManager.model.Model;
import accountManager.view.View;

/**
 * The Class AbstractController.
 */
public abstract class AbstractController implements Controller {
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/* (non-Javadoc)
	 * @see accountManager.controller.Controller#setModel(accountManager.model.Model)
	 */
	public void setModel(Model model){this.model = model;}
	
	/* (non-Javadoc)
	 * @see accountManager.controller.Controller#getModel()
	 */
	public Model getModel(){return model;}
	
	/* (non-Javadoc)
	 * @see accountManager.controller.Controller#getView()
	 */
	public View getView(){return view;}
	
	/* (non-Javadoc)
	 * @see accountManager.controller.Controller#setView(accountManager.view.View)
	 */
	public void setView(View view){this.view = view;}

}
