package engine.model.entities;

import java.util.HashMap;
import java.util.Map;
/**
 * This class wraps a map of entity's id and entity itself
 * @author owenchung
 *
 */
public class EntityManager implements IModifiableEntityManager {
	Map<String,IEntity> myEntityMap;

	public EntityManager() {
		myEntityMap = new HashMap<String, IEntity>();
	}
	
	
	
	public Map<String, IEntity> getEntityMap() {
		return myEntityMap;
	}
	
	@Override
	public void addEntity(String id, IEntity entity) {
		myEntityMap.put(id, entity);
	}
	
	@Override
	public void removeEntity(String id) {
		myEntityMap.remove(id);
	}
}
