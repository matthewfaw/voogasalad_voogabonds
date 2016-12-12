package engine.model.strategies;

import java.util.List;

import javafx.util.Pair;
import utility.Point;

public interface IPhysical extends IPosition {

	public Point getPosition();
	public double getHeading();
	public List<String> getValidTerrains();
	public void setPosition(Pair<Double, Point> p);
	public void setPosition(Point position);
	
}
