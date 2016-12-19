package engine.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class wraps a map of entity's id and entity itself
 * @author owenchung
 *
 */
public class EntityManager implements IModifiableEntityManager {
	private Map<String,ConcreteEntity> myEntityMap;

	public EntityManager() {
		myEntityMap = new HashMap<String, ConcreteEntity>();
	}
	
	
	
	public Map<String, ConcreteEntity> getEntityMap() {
		return myEntityMap;
	}
	
	public Collection<ConcreteEntity> getEntities()
	{
		return myEntityMap.values();
	}
	
	@Override
	public void putEntity(String id, ConcreteEntity entity) {
		myEntityMap.put(id, entity);
	}
	public void putEntity(String id, IEntity entity) {
		myEntityMap.put(id, (ConcreteEntity)entity);
	}
	
	@Override
	public void removeEntity(String id) {
		myEntityMap.remove(id);
	}
}
