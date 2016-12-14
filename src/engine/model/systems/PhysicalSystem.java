package engine.model.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engine.model.components.IComponent;
import engine.model.components.concrete.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import utility.Point;

public class PhysicalSystem implements ISystem<PhysicalComponent> {
	private List<PhysicalComponent> myComponents;
	private transient MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myComponents = new ArrayList<PhysicalComponent>();
		setMapMediator(map);
	}
	
	public void setMapMediator(MapMediator aMapMediator)
	{
		myMap = aMapMediator;
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
	/***********ISystem interface*******/
	@Override
	public List<PhysicalComponent> getComponents() {
		return myComponents;
	}
	@Override
	public PhysicalComponent getComponent(IComponent component) {
		return component == null ? null : getComponent(component.getEntity());
	}
	@Override
	public PhysicalComponent getComponent(IEntity entity) {
		for (PhysicalComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(PhysicalComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(PhysicalComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
