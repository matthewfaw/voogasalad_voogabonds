package engine.model.strategies;

import java.util.List;

import engine.model.playerinfo.IModifiablePlayer;
import utility.Point;

public interface IPhysical {

	abstract public Point getPosition();
	abstract public double getHeading();
	abstract public double getCollisionRadius();
	abstract public List<String> getValidTerrains();
	abstract public void setPosition(Point p);
	abstract public IModifiablePlayer getOwner();
	
}
