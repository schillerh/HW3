package accountManager.view;
import javax.swing.JFrame;

import accountManager.controller.Controller;
import accountManager.model.AbstractModel;
import accountManager.model.Model;
import accountManager.model.ModelListener;



/**
 * The Class JFrameView.
 */
abstract public class JFrameView extends JFrame implements View, ModelListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8138303214009086149L;

	/** The model. */
	private Model model;
	
	/** The controller. */
	private Controller controller;
	
	/**
	 * Instantiates a new j frame view.
	 *
	 * @param model the model
	 * @param controller the controller
	 */
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
	}
	
	/**
	 * Register with model.
	 */
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	
	/* (non-Javadoc)
	 * @see accountManager.view.View#getController()
	 */
	public Controller getController(){return controller;}
	
	/* (non-Javadoc)
	 * @see accountManager.view.View#setController(accountManager.controller.Controller)
	 */
	public void setController(Controller controller){this.controller = controller;}
	
	/* (non-Javadoc)
	 * @see accountManager.view.View#getModel()
	 */
	public Model getModel(){return model;}
	
	/* (non-Javadoc)
	 * @see accountManager.view.View#setModel(accountManager.model.Model)
	 */
	public void setModel(Model model) {
		this.model = model;
		registerWithModel();
	}
	
}
