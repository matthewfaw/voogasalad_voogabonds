package engine.model.strategies;

import java.util.List;

import engine.model.game_environment.terrain.Terrain;
import utility.Point;

public interface IPhysical {

	abstract public Point getPosition();
	abstract public double getHeading();
	abstract public double getCollisionRadius();
	abstract public List<Terrain> getValidTerrains();
	abstract public void setPosition(Point p);
	
}
