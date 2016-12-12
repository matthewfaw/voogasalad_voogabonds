package engine.model.strategies;

import engine.model.strategies.factories.MovementStrategyFactory;
import javafx.util.Pair;
import utility.Point;

abstract public class AbstractMovementStrategy implements IMovementStrategy {
	
	public AbstractMovementStrategy(MovementStrategyFactory creator) {
	}
	
	@Override
	public  Pair<Double, Point> nextMove(IMovable m, IPhysical p) {
		if (m.getGoal() != null)
			return nextMoveWithGoal(m, p);
		else
			return nextMoveNoGoal(m, p);
	}

	/**
	 * The movement strategy's algorithm for finding the Movable's next location when the movable doesn't provide a goal.
	 * @param m Movable to be moved
	 * @return a javafx Pair containing the intended new heading and new position for the movable (double, point)
	 */
	abstract protected Pair<Double, Point> nextMoveNoGoal(IMovable m, IPhysical p);

	/**
	 * The movement strategy's algorithm for finding the Movable's next location when the movable provides a goal point.
	 * @param m Movable to be moved
	 * @return a javafx Pair containing the intended new heading and new position for the movable (double, point)
	 */
	abstract protected Pair<Double, Point> nextMoveWithGoal(IMovable m, IPhysical p);
	
	protected double newHeadingTowards(Point goal, IMovable m, IPhysical p) {
		double newHeading;
		double deltaToTarget = p.getPosition().towards(goal) -  p.getHeading();
		if (p.getHeading() == 1.0) {
			System.out.println("here");
		}
		System.out.println(String.format("%f\t%f\t%f", p.getPosition().towards(goal), p.getHeading(), deltaToTarget));
		
		
		while (Math.abs(deltaToTarget) > 180) {
			deltaToTarget -= 360 * (deltaToTarget / Math.abs(deltaToTarget));
		}
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = p.getPosition().towards(goal);
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = p.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));
		
		System.out.println(String.format("%f\t%f", p.getHeading(), newHeading));
		return newHeading;
	}

}
