package authoring.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

import authoring.model.EntityData;
import authoring.model.EntityList;
import authoring.model.IReadableData;
import engine.IObserver;
import authoring.model.EntityData;
import engine.IObserver;
import engine.IObservable;


public class EntityDataContainer extends Container implements IDataContainer, IObservable<Container>{
	
    private AbstractMap<String, EntityData> myEntityDataMap = new HashMap<String, EntityData>();
    ArrayList<IObserver<Container>> myObservers = new ArrayList<IObserver<Container>>();

    /**
     * @return
     */
    public AbstractMap<String, EntityData> finalizeEntityDataMap(){
        return myEntityDataMap;
    }

    /**
     * @param EntityData
     */
    public void createEntityData(EntityData entityData) throws Exception{
        if (myEntityDataMap.containsKey(entityData.getName())){
        	throw new Exception("An entity with this name already exists!");
        }
    	myEntityDataMap.put(entityData.getName(), entityData);
        notifyObservers();
    }

    /**
     * @return
     */
    public int getNumEntities() {
        return myEntityDataMap.size();
    }
    
    public AbstractMap<String, EntityData> getEntityDataMap(){
    	return myEntityDataMap;
    }

    /**
     * @param EntityName
     * @return
     */
    public EntityData getEntityData(String EntityName){
        return myEntityDataMap.get(EntityName);
    }

    /**
     * @param originalName
     * @param updatedEntity
     */
    public void updateEntityData(String originalName, EntityData updatedEntity) throws Exception{
        myEntityDataMap.remove(originalName);
        createEntityData(updatedEntity);
        notifyObservers();
    }

    
    /**
     * 
     *  not sure if casting to IReadableData is right way to go about this
     *  
     */
    @Override
    public IReadableData getObjectData (String name) {
        return (IReadableData) getEntityData(name);
    }

    @Override
    public void createObjectData (IReadableData data) throws Exception{
        createEntityData((EntityData)data);
    }

    @Override
    public void updateObjectData (String oldName, IReadableData data) throws Exception {
        if (!myEntityDataMap.containsKey(oldName)) {
            throw new Exception("This Entity has not been created.");
        }
        updateEntityData(oldName, (EntityData)data);
    }

    @Override
    public boolean deleteObjectData (String name) {
        if (!myEntityDataMap.containsKey(name)) {
            return false;
        }
        myEntityDataMap.remove(name);
        notifyObservers();
        return true;
    }
    
	/**
	 * IObservable functions
	 */
	public void attach(IObserver<Container> observer){
		myObservers.add(observer);
	}
	
	public void detach(IObserver<Container> observer){
		myObservers.remove(observer);
	}
	
	public void notifyObservers(){
		for (IObserver<Container> observer: myObservers){
			observer.update(this);
		}
	}
}
