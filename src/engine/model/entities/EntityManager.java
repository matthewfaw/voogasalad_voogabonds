package engine.model.entities;

import java.util.HashMap;
import java.util.Map;
/**
 * This class wraps a map of entity's id and entity itself
 * @author owenchung
 *
 */
public class EntityManager implements IModifiableEntityManager {
	Map<Integer,IEntity> myEntityMap;

	public EntityManager() {
		myEntityMap = new HashMap<Integer, IEntity>();
	}
	
	
	
	public Map<Integer, IEntity> getEntityMap() {
		return myEntityMap;
	}
	
	@Override
	public void addEntity(Integer id, IEntity entity) {
		myEntityMap.put(id, entity);
	}
	
	@Override
	public void removeEntity(Integer id) {
		myEntityMap.remove(id);
	}
}
