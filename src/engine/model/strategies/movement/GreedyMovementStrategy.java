package engine.model.strategies.movement;

import engine.model.strategies.IMovable;
import javafx.util.Pair;
import utility.Point;

public class GreedyMovementStrategy extends AbstractMovementStrategy {

	/**
	 * Move as close to straight towards your goal as possible.
	 */
	protected  Pair<Double, Point> nextMoveWithGoal(IMovable m) {
		/*
		double newHeading;
		double deltaToTarget = m.getHeading() - m.getPosition().towards(m.getGoal());
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = m.getHeading() + deltaToTarget;
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = m.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));

		double distance = Math.min(m.getMoveSpeed(), m.getPosition().euclideanDistance(m.getGoal()));
		
		return new Pair<Double, Point>(newHeading, m.getPosition().moveAlongHeading(distance, newHeading));
		*/
		return null;
	}
	
	/**
	 * Just move straight ahead.
	 */
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m) {
		/*
		double heading = m.getHeading();
		double speed = m.getMoveSpeed();
		
		Point newLoc = m.getPosition().moveAlongHeading(speed, heading);
		
		return new Pair<Double, Point>(heading, newLoc);
		*/
		return null;
	}

}
