package engine.model.components;

import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.IViewable;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import engine.model.systems.PhysicalSystem;
import gamePlayerView.gamePlayerView.Router;
import javafx.util.Pair;
import utility.Point;

/**
 * Manages the information relevant to the physical information of an entity
 * Physical components contain information relevant to existing on a grid, and being displayed
 * 
 * @author matthewfaw
 *
 */
public class PhysicalComponent extends AbstractComponent implements IPhysical, IViewable {
	@Hide
	private List<IObserver<IViewable>> myObservers;
	@Hide
	private Point myPosition;
	
	private double myHeading;
	private String myImagePath;
	private double myImageSize;
	//TODO: Talk to authoring about lists
	@Hide
	private List<String> myValidTerrains;
	
	/*
	public PhysicalComponent (CollisionDetectionSystem collisionDetectionSystem, Router router) {
		myCollisionDetectionSystem = collisionDetectionSystem;
		myCollisionDetectionSystem.attachComponent(this);
		myObservers = new ArrayList<IObserver<IViewable>>();
		myRouter = router;
		myRouter.distributeViewableComponent(this);
	}
	*/
	
	public PhysicalComponent (PhysicalSystem PhysicalSystem, Router router, ComponentData data) {
		myObservers = new ArrayList<IObserver<IViewable>>();
		
		myHeading = Double.parseDouble(data.getFields().get("myHeading"));
		myImagePath = data.getFields().get("myImagePath");
		myImageSize = Double.parseDouble(data.getFields().get("myImageSize"));
		//myValidTerrains = Arrays.asList(data.getFields().get("myValidTerrains").split(", "));
		
		router.distributeViewableComponent(this);
	}

	
	/******** Setters ********/
	public void setPosition(Point position) {
		myPosition = position;
	}
	
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
	
	@Override
	public void setPosition(Pair<Double, Point> p) {
		myHeading = p.getKey();
		myPosition = p.getValue();
		notifyObservers();
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