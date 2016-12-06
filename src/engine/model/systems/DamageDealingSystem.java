package engine.model.systems;

import java.util.List;

import engine.model.components.DamageDealingComponent;
import engine.model.entities.IEntity;

public class DamageDealingSystem {
	private List<DamageDealingComponent> myDamageDealingComponents;
	
	/**
	 * Given the input entity, returns the corresponding damage dealing component.
	 * 
	 * @param entityQuery
	 * @return damage dealing component that the input entity owns
	 */
	private DamageDealingComponent findDamageDealingComponent(IEntity entityQuery) {
		DamageDealingComponent found = null;
		for(DamageDealingComponent ddc: myDamageDealingComponents) {
			if (entityQuery == ddc.getEntity()) {
				found = ddc;
			}
		}
		return found;
	}
	
	/**
	 * Gets the damage dealt by a certain entity.
	 * @param entity
	 * @return damage dealt by entity; 0 if entity 
	 * is not a damage dealing component
	 */
	public double getDamageDealt(IEntity entity) {
		DamageDealingComponent damageDealer = findDamageDealingComponent(entity);
		return damageDealer == null ? 0 : damageDealer.getDamage();
	}
}
