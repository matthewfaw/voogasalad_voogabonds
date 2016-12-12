package engine.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class wraps a map of entity's id and entity itself
 * @author owenchung
 *
 */
public class EntityManager implements IModifiableEntityManager {
	private Map<Integer,ConcreteEntity> myEntityMap;

	public EntityManager() {
		myEntityMap = new HashMap<Integer, ConcreteEntity>();
	}
	
	
	
	public Map<Integer, ConcreteEntity> getEntityMap() {
		return myEntityMap;
	}
	
	@Override
	public void addEntity(Integer id, ConcreteEntity entity) {
		myEntityMap.put(id, entity);
	}
	
	@Override
	public void removeEntity(Integer id) {
		myEntityMap.remove(id);
	}
}
