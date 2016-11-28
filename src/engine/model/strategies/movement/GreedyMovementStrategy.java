package engine.model.strategies.movement;

import engine.model.strategies.IMovable;
import engine.model.strategies.IMovementStrategy;
import javafx.util.Pair;
import utility.Point;

public class GreedyMovementStrategy implements IMovementStrategy {

	@Override
	public Pair<Double, Point> nextMove(IMovable m) {
		double newHeading;
		double deltaToTarget = m.getHeading() - m.getLocation().towards(m.getGoal());
		
		if (Math.abs(deltaToTarget) <= Math.abs(m.getTurnSpeed()))
			newHeading = m.getHeading() + deltaToTarget;
		else
			//heading = currHeading + turnSpeed + (delta/|delta|)
			newHeading = m.getHeading() + m.getTurnSpeed() * (deltaToTarget/Math.abs(deltaToTarget));

		double distance = Math.min(m.getMoveSpeed(), m.getLocation().euclideanDistance(m.getGoal()));
		
		return new Pair<Double, Point>(newHeading, m.getLocation().moveAlongHeading(distance, newHeading));
	}

}
