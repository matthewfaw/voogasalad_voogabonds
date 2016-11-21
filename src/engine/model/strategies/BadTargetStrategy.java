package engine.model.strategies;

import java.util.List;

import engine.model.machine.Machine;
import utility.Point;

public class BadTargetStrategy implements ITargetStrategy {

	@Override
	public Machine target(List<Machine> targets, double heading, Point position) {
		return targets.get(0);
	}

}
