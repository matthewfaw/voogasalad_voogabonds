package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.components.concrete.DamageDealingComponent;
import engine.model.components.concrete.HealthComponent;
import engine.model.entities.IEntity;
import utility.Damage;

public class DamageDealingSystem implements ISystem{
	private List<DamageDealingComponent> myDamageDealingComponents;
	
	public DamageDealingSystem() {
		myDamageDealingComponents = new ArrayList<DamageDealingComponent>();
	}
	
	public DamageDealingComponent get(IComponent c) {
		return get(c.getEntity());
	}
	
	
	/**
	 * Given the input entity, returns the corresponding damage dealing component.
	 * 
	 * @param entityQuery
	 * @return damage dealing component that the input entity owns
	 */
	private DamageDealingComponent get(IEntity entityQuery) {
		for(DamageDealingComponent ddc: myDamageDealingComponents)
			if (entityQuery.equals(ddc.getEntity()))
				return ddc;
		return null;
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(DamageDealingComponent aComponent) {
		myDamageDealingComponents.add(aComponent);
	}
	public void detachComponent(DamageDealingComponent aComponent) {
		myDamageDealingComponents.remove(aComponent);
	}

	public void dealDamageToTarget(IComponent a, IComponent b) {
		get(a).explode(b);
		
	}
}
