package engine.model.entities;

import java.util.ArrayList;

import engine.model.components.IComponent;

public class ConcreteEntity implements IEntity {
	private ArrayList<IComponent> myComponents;
	
	/**
	 * This object should only be constructed in this
	 * package, since components should be constructed 
	 * in the Factory
	 */
	ConcreteEntity()
	{
		myComponents = new ArrayList<IComponent>();
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addComponent(IComponent aComponent) {
		// TODO Auto-generated method stub
		
	}

}
