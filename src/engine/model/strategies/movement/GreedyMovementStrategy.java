package engine.model.strategies.movement;

import engine.model.strategies.AbstractMovementStrategy;
import engine.model.strategies.IMovable;
import engine.model.strategies.IPhysical;
import javafx.util.Pair;
import utility.Point;

public class GreedyMovementStrategy extends AbstractMovementStrategy {

	/**
	 * Move as close to straight towards your goal as possible.
	 */
	protected  Pair<Double, Point> nextMoveWithGoal(IMovable m, IPhysical p) {
		
		if (p.getPosition().equals(m.getGoal()))
			return new Pair<Double, Point>(p.getHeading(), p.getPosition());
		
		double newHeading;
		double deltaToTarget = p.getPosition().towards(m.getGoal()) -  p.getHeading();
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = p.getPosition().towards(m.getGoal());
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = p.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));

		double distance = Math.min(m.getMoveSpeed(), p.getPosition().euclideanDistance(m.getGoal()));
		
		return new Pair<Double, Point>(newHeading, p.getPosition().moveAlongHeading(distance, newHeading));
	}
	
	/**
	 * Just move straight ahead.
	 */
	protected Pair<Double, Point> nextMoveNoGoal(IMovable m, IPhysical p) {
		double heading = p.getHeading();
		double speed = m.getMoveSpeed();
		
		Point newLoc = p.getPosition().moveAlongHeading(speed, heading);
		
		return new Pair<Double, Point>(heading, newLoc);
	}

}
