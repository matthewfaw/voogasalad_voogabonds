package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.BountyComponent;
import engine.model.entities.IEntity;

public class BountySystem implements ISystem<BountyComponent> {
	private List<BountyComponent> myComponents;
	
	public BountySystem()
	{
		myComponents = new ArrayList<BountyComponent>();
	}
	
	public int collectBounty(IComponent c) {
		BountyComponent b = getComponent(c);
		return (b == null) ? 0 : b.getBounty();
	}

	/***********ISystem interface*******/
	@Override
	public List<BountyComponent> getComponents() {
		return myComponents;
	}
	@Override
	public BountyComponent getComponent(IComponent component) {
		return getComponent(component.getEntity());
	}
	@Override
	public BountyComponent getComponent(IEntity entity) {
		for (BountyComponent component: myComponents) {
			if (component.getEntity().equals(entity)) {
				return component;
			}
		}
		return null;
	}
	@Override
	public void attachComponent(BountyComponent aComponet) {
		myComponents.add(aComponet);
	}
	@Override
	public void detachComponent(BountyComponent aComponent) {
		myComponents.remove(aComponent);
	}
}
