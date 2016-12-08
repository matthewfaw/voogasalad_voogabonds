package engine.model.strategies;

import javafx.util.Pair;
import utility.Point;

abstract public class AbstractMovementStrategy implements IMovementStrategy {
	
	@Override
	public  Pair<Double, Point> nextMove(IMovable m) {
		if (m.getGoal() != null)
			return nextMoveWithGoal(m);
		else
			return nextMoveNoGoal(m);
	}

	/**
	 * The movement strategy's algorithm for finding the Movable's next location when the movable doesn't provide a goal.
	 * @param m Movable to be moved
	 * @return a javafx Pair containing the intended new heading and new position for the movable (double, point)
	 */
	abstract protected Pair<Double, Point> nextMoveNoGoal(IMovable m);

	/**
	 * The movement strategy's algorithm for finding the Movable's next location when the movable provides a goal point.
	 * @param m Movable to be moved
	 * @return a javafx Pair containing the intended new heading and new position for the movable (double, point)
	 */
	abstract protected Pair<Double, Point> nextMoveWithGoal(IMovable m);

}
