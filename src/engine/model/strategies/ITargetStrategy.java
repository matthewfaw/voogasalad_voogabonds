package engine.model.strategies;

import java.util.List;

import engine.model.machine.Machine;
import utility.Point;

public interface ITargetStrategy {

	Machine target(List<Machine> targets, double heading, Point position);

}
