package engine.model.strategies;

import java.util.List;

import engine.model.playerinfo.IModifiablePlayer;
import javafx.util.Pair;
import utility.Point;

public interface IPhysical {

	public Point getPosition();
	public double getHeading();
	public List<String> getValidTerrains();
	public void setPosition(Pair<Double, Point> p);
	
}
