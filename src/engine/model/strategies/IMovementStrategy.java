package engine.model.strategies;

import javafx.util.Pair;
import utility.Point;

public interface IMovementStrategy {

	/**
	 * Finds a next heading and location for the movable, based on the movable's goal and the strategy's algorithm.
	 * @param m Movable to be moved
	 * @return a javafx pair containng the new heading (double) and position (Point)
	 */
	public Pair<Double, Point> nextMove(IMovable m, IPhysical p);
}
