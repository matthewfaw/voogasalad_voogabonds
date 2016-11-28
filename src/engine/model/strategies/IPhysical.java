package engine.model.strategies;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;
import utility.Point;

public interface IPhysical {

	abstract public Point getLocation();
	abstract public double getHeading();
	abstract public double getCollisionRadius();
	abstract public List<Terrain> getValidTerrains();
	public abstract void setLocation(Point aLocation);
	
}
