package engine.model.machine.weapon;

import java.util.List;

import engine.model.machine.Machine;
import utility.Point;

public interface IWeapon {
	public void fire(List<Machine> targets, double initialDirectionRadians, Point initialLocation);
}
