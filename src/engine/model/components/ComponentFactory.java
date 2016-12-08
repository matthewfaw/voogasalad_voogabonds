package engine.model.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import engine.model.entities.IEntity;
import engine.model.systems.ISystem;
/**
 * Creates all the components
 * @author owenchung
 *
 */
public class ComponentFactory {
	private List<ISystem> mySystems;

	public ComponentFactory(List<ISystem> systems) {
		mySystems = systems;
	}
	
	/**
	 * Constructing a component using reflection
	 * @param entity
	 * @param compdata
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public IComponent constructComponent(ComponentData compdata) 
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> tmpclass =  Class.forName(compdata.getComponentName());
		Constructor<?>[] constructors = tmpclass.getConstructors();
		
		// Note: Assuming only one constructor
		Class<?> [] arguments = constructors[0].getParameterTypes();
		List<ISystem> systemsToAttach = new ArrayList<ISystem>();
		for (Class<?> arg : arguments) {
			systemsToAttach.add(getSystemToAttach(arg));
		}
		
		return (IComponent) constructors[0].newInstance( new Object[] { systemsToAttach.toArray() } );
		
	}


	private ISystem getSystemToAttach(Class<?> arg) {
		for (ISystem sys : mySystems) {
			if ( arg.isInstance(sys) ) {
				return sys;
			}
		}
		// SHOULD NOT EVER HAPPEN
		return null;
	}
	
	public void addSystem(ISystem systemToAdd) {
		mySystems.add(systemToAdd);
	}
}
