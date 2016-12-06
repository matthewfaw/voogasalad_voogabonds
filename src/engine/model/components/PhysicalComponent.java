package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.IViewable;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import utility.Point;

public class PhysicalComponent implements IComponent, IPhysical, IViewable {
	private List<IObserver<IViewable>> myObservers;
	private IEntity myEntity;

	private Point myPosition;
	private double myHeading;
	private String myImagePath;
	private double myImageSize;
	private List<String> myValidTerrains;
	
	/******************IViewable interface********/
	@Override
	public Point getPosition() {
		return myPosition;
	}

	@Override
	public double getHeading() {
		return myHeading;
	}

	@Override
	public double getSize()
	{
		return myImageSize;
	}

	@Override
	public String getImagePath()
	{
		return myImagePath;
	}
	
	/***************IPhysical interface************/
	@Override
	public List<String> getValidTerrains() {
		return myValidTerrains;
	}

	/***************IComponent interface**********/
	@Override
	public IEntity getEntity() {
		return myEntity;
	}

	/******************IObservable interface********/
	@Override
	public void attach(IObserver<IViewable> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewable> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}
}