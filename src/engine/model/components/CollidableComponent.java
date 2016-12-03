package engine.model.components;

import java.util.List;

import engine.IObserver;
import engine.model.collision_detection.ICollidable;
import engine.model.entities.IEntity;
import engine.model.machine.Machine;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.projectiles.Projectile;
import javafx.util.Pair;
import utility.Point;

public class CollidableComponent implements IComponent, ICollidable {
	private List<IObserver<IComponent>> myObservers;
	private PhysicalComponent myPhysical;
	
	private boolean myDestroyOnCollision;
	

	//*******************IObservable interface***********//
	@Override
	public void attach(IObserver<IComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IObserver<IComponent> aObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}
	
	//*******************IComponent interface***********//
	@Override
	public IEntity getEntity() {
		return myPhysical.getEntity();
	}

	//*******************IPhysical interface***********//
	@Override
	public Point getPosition() {
		return myPhysical.getPosition();
	}

	@Override
	public double getHeading() {
		return myPhysical.getHeading();
	}

	@Override
	public double getCollisionRadius() {
		return myPhysical.getCollisionRadius();
	}

	@Override
	public List<String> getValidTerrains() {
		return myPhysical.getValidTerrains();
	}

	@Override
	public IModifiablePlayer getOwner() {
		return myPhysical.getOwner();
	}

	@Override
	public void setPosition(Pair<Double, Point> p) {
		myPhysical.setPosition(p);
	}

	//*******************ICollidable interface***********//
	@Override
	public void collideInto(ICollidable movedCollidable) {
		myObservers.forEach(o -> o.update(this));
		if (myDestroyOnCollision) {
			getEntity().delete();
		}
	}
	
	/*
	 * The two following methods are unneeded using components
	 * 
	 */
	@Override
	public void collideInto(Machine unmovedCollidable) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void collideInto(Projectile unmovedCollidable) {
		// TODO Auto-generated method stub
		
	}

}
