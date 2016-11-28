package engine.model.machine;

import java.util.List;

import engine.IViewable;
import engine.model.components.IComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.terrain.Terrain;
import engine.model.playerinfo.IModifiablePlayer;
import engine.model.strategies.IMovable;
import engine.model.weapons.DamageInfo;
import utility.Damage;
import utility.Point;

public abstract class Machine implements IViewable, IViewableMachine, IModifiableMachine, IEntity, IMovable {

	private IModifiablePlayer myModifiablePlayer;
	private IHealth health;
	
	public Machine (int initialHealth) {
		health = new Health(initialHealth);
	}
	
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IHealth getHealth() {
		return health;
	}
	
	@Override
	public void modifyHealth(IHealth deltaHealth) {
		health.updateHealth(deltaHealth);
	}
	
	public double getDistanceTo(Point p) {
		return getPosition().euclideanDistance(p); //Minus collision Radius
	}
	
	public IModifiablePlayer getModifiablePlayerInfo() {
		return myModifiablePlayer;
	}

	abstract public DamageInfo takeDamage(Damage toDeal);

	@Deprecated
	public int getId()
	{
		return 0;
	}

	@Deprecated
	public void addComponent(IComponent aComponent)
	{
		
	}

	public boolean onMap() {
		return true;
	}
	
	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Point getGoal() {
		return null;
	}
	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMoveSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCollisionRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Terrain> getValidTerrains() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setLocation(Point aLocation) {
		// TODO Auto-generated method stub
	}
	
}
