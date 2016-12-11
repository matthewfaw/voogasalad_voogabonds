package engine.model.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engine.model.components.IComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.game_environment.paths.PathManager;
import utility.Point;

public class PhysicalSystem implements ISystem {
	List<PhysicalComponent> myComponents;
	MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myComponents = new ArrayList<PhysicalComponent>();
		myMap = map;
		
	}
	
	public PhysicalComponent get(IComponent c) {
		return get(c.getEntity());
	}

	public MapMediator getMap() {
		return myMap;
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(PhysicalComponent aComponent) {
		myComponents.add(aComponent);
	}
	public void detachComponent(PhysicalComponent aComponent) {
		myComponents.remove(aComponent);
	}

	public PhysicalComponent get(IEntity entity) {
		for (PhysicalComponent p: myComponents)
			if (p.getEntity().equals(entity))
				return p;
		return null;
	}

	public List<PhysicalComponent> withinRange(Point pos, double radius, double heading, double width) {
		return myComponents.stream().filter(p -> isWithin(p, pos, radius, heading, width)).collect(Collectors.toList());
	}

	public boolean isWithin(PhysicalComponent p, Point pos, double radius, double heading, double width) {
		return pos.getDistanceTo(p.getPosition()) <= radius &&
				Math.abs(pos.towards(p.getPosition()) - heading) <= width;
	}
	
	public PathManager findPath(MoveableComponent move) {
		return myMap.constructPaths(get(move), move);
	}
}
