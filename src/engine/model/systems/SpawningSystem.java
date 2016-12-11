package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.ICreator;
import engine.model.components.concrete.CreatorComponent;

public class SpawningSystem implements ISystem, IObserver<TimelineController>{
	private List<CreatorComponent> myComponents;
	private TargetingSystem myTargeting;
	private PhysicalSystem myPhysical;
	
	public SpawningSystem (PhysicalSystem physical, TargetingSystem targeting) {
		myComponents = new ArrayList<CreatorComponent>();
		myPhysical = physical;
		myTargeting = targeting;
	}
	
	public ICreator get(IComponent c) {
		for (CreatorComponent p: myComponents)
			if (p.getEntity().equals(c.getEntity()))
				return p;
		return null;
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(CreatorComponent aComponent) {
		myComponents.add(aComponent);
	}
	public void detachComponent(CreatorComponent aComponent) {
		myComponents.remove(aComponent);
	}

	/************Observer methods ************/
	@Override
	public void update(TimelineController aChangedObject) {
		spawnIfReady();
	}

	private void spawnIfReady() {
		for (CreatorComponent c: myComponents) {
			c.setTarget(myTargeting.getTarget(c));
			c.spawn(myPhysical.get(c));
		}
	}
}
