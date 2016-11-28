package engine.model.machine;

import engine.IViewable;
/**
 * Viewable machines
 * TODO: This interface is a duplicate of the more inclusively named "IViewable". Do we need both of them?
 *
 */
public interface IViewableMachine extends IViewable {
	public IHealth getHealth();
}
