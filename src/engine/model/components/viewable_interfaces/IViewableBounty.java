package engine.model.components.viewable_interfaces;

import engine.IObservable;

public interface IViewableBounty extends IViewable, IObservable<IViewableBounty>{
	/**
	 * Gets fields that are viewable for front end to display.
	 * @return
	 */
	public int getBounty();
}
