package engine.model.components;

import engine.IObservable;

public interface IViewableHealth extends IObservable<IViewableHealth>{
	public double getMaxHealth();
	public double getCurrHealth();
}
