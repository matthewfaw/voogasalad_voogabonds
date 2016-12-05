package engine.model.strategies;

import java.util.List;

import engine.model.playerinfo.IModifiablePlayer;
import javafx.util.Pair;
import utility.Point;

public interface IPhysical {

	abstract public Point getPosition();
	abstract public double getHeading();
	abstract public double getCollisionRadius();
	abstract public List<String> getValidTerrains();
	abstract public IModifiablePlayer getOwner();
	abstract public void setPosition(Pair<Double, Point> p);
	
}
