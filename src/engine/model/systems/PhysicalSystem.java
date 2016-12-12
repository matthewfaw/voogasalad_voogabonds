package engine.model.systems;

import java.util.List;
import java.util.stream.Collectors;

import engine.model.components.IComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import utility.Point;

public class PhysicalSystem extends AbstractSystem<PhysicalComponent> {
	private transient MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myMap = map;
	}
	
	public PhysicalComponent get(IEntity entity) {
		return getComponent(entity);
	}
	
	public PhysicalComponent get(IComponent c) {
		return getComponent(c);
	}
	
	public MapMediator getMap() {
		return myMap;
	}


	public List<PhysicalComponent> withinRange(Point pos, double radius, double heading, double width) {
		return getComponents()
							.stream()
							.filter(p -> isWithin(p, pos, radius, heading, width))
							.collect(Collectors.toList());
	}

	public boolean isWithin(PhysicalComponent p, Point pos, double radius, double heading, double width) {
		return pos.getDistanceTo(p.getPosition()) <= radius &&
				Math.abs(pos.towards(p.getPosition()) - heading) <= width;
	}
}
