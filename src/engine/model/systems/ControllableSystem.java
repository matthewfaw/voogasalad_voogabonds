package engine.model.systems;

import engine.model.components.concrete.ControllableComponent;

/**
 * 
 * @author owenchung
 *
 */
public class ControllableSystem extends AbstractSystem<ControllableComponent>{
	
	
	public void move(String movement) {
		for (ControllableComponent component : getComponents() ){
			component.move(movement);
		}
	}
	
	public void fire() {
		for (ControllableComponent component : getComponents() ){
			component.fire();
		}
	}
	
	
}
