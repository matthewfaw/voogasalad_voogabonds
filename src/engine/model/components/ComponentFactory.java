package engine.model.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tracing.dtrace.ArgsAttributes;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.model.entities.IEntity;
import engine.model.strategies.IPosition;
import engine.model.systems.ISystem;
import gamePlayerView.gamePlayerView.Router;
import utility.Point;
/**
 * Creates all the components
 * @author owenchung
 *
 */
public class ComponentFactory {
        @Hide
	private static final String COMPONENT_PATH = "engine.model.components.concrete.";
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
	public IModifiableComponent constructComponent(ComponentData compdata, IPosition location) 
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> tmpclass =  Class.forName(COMPONENT_PATH+compdata.getComponentName());
		Constructor<?>[] constructors = tmpclass.getConstructors();
		
		// Note: Assuming only one constructor
		Class<?> [] arguments = constructors[0].getParameterTypes();
		List<Object> objectsToAttach = new ArrayList<Object>();
		for (Class<?> arg : arguments) {
			// attach appropriate system to argument
			ISystem sysToAdd = getSystemToAttach(arg);
			if (sysToAdd != null) {
				objectsToAttach.add(getSystemToAttach(arg));
				continue;
			}
			// check if arg is Router, if so, attach myRouter
			Router routerToAdd = getRouterToAttach(arg);
			if (routerToAdd != null) {
				objectsToAttach.add(getRouterToAttach(arg));
				continue;
			}
			if (IPosition.class.isAssignableFrom(arg)) {
				objectsToAttach.add(location);
				continue;
			}
			objectsToAttach.add(compdata);
			
		}
		return (IModifiableComponent) constructors[0].newInstance(objectsToAttach.toArray());
		
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
