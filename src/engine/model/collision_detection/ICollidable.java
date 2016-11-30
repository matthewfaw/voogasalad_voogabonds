package engine.model.collision_detection;

import utility.Point;

public interface ICollidable{
	public Point getLocation();
	public double getRadius();
	public void collideInto(ICollidable unmovedCollidable);
}
