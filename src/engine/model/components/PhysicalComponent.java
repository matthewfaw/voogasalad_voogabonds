package engine.model.components;

import java.util.ArrayList;
import java.util.List;

import authoring.model.Hide;
import engine.IObserver;
import engine.IViewable;
import engine.model.entities.IEntity;
import engine.model.strategies.IPhysical;
import engine.model.systems.CollisionDetectionSystem;
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
public class PhysicalComponent implements IComponent, IPhysical, IViewable {
	@Hide
	private List<IObserver<IViewable>> myObservers;
	@Hide
	private IEntity myEntity;
	@Hide
	private Point myPosition;
	
	private double myHeading;
	private String myImagePath;
	private double myImageSize;
	@Hide
	private List<String> myValidTerrains;
	
	private CollisionDetectionSystem myCollisionDetectionSystem;
	
	@Hide
	private Router myRouter;
	
	public PhysicalComponent (CollisionDetectionSystem collisionDetectionSystem, Router router) {
		myCollisionDetectionSystem = collisionDetectionSystem;
		myCollisionDetectionSystem.attachComponent(this);
		myObservers = new ArrayList<IObserver<IViewable>>();
		myRouter = router;
		myRouter.distributeViewableComponent(this);
	}
	
	/******** Setters ********/
	public void setPosition(Point position) {
		myPosition = position;
		/*  ask collision detection system if this physical 
		 *  component collides with anything
		 */
		myCollisionDetectionSystem.checkCollision(this);
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