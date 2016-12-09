package engine.model.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import authoring.model.ComponentData;
import engine.model.entities.IEntity;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;
/**
 * Creates all the components
 * @author owenchung
 *
 */
public class ComponentFactory {
	private List<ISystem> mySystems;
	private Router myRouter;

	public ComponentFactory(List<ISystem> systems, Router router) {
		mySystems = systems;
		myRouter = router;
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
		List<Object> objectsToAttach = new ArrayList<Object>();
		for (Class<?> arg : arguments) {
			// attach appropriate system to argument
			objectsToAttach.add(getSystemToAttach(arg));
			// check if arg is Router, if so, attach myRouter
			objectsToAttach.add(getRouterToAttach(arg));
		}
		
		return (IComponent) constructors[0].newInstance( new Object[] { objectsToAttach.toArray() } );
		
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
	
	private Router getRouterToAttach(Class<?> arg) {
		if (arg.isInstance(myRouter)) {
			return myRouter;
		}
		return null;
	}
	
	public void addSystem(ISystem systemToAdd) {
		mySystems.add(systemToAdd);
	}
}
