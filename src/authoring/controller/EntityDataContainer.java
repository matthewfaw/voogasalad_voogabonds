package authoring.controller;

import java.util.AbstractMap;
import java.util.HashMap;

import authoring.model.EntityData;
import authoring.model.IReadableData;
import authoring.model.EntityData;


public class EntityDataContainer implements IDataContainer{
	
    private AbstractMap<String, EntityData> myEntityDataMap = new HashMap<String, EntityData>();

    /**
     * @return
     */
    public AbstractMap<String, EntityData> finalizeEntityDataMap(){
        return myEntityDataMap;
    }

    /**
     * @param EntityData
     */
    public void createEntityData(EntityData EntityData){
        myEntityDataMap.put(EntityData.getName(), EntityData);
    }

    /**
     * @return
     */
    public int getNumEntitys() {
        return myEntityDataMap.size();
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
    public void updateEntityData(String originalName, EntityData updatedEntity){
        myEntityDataMap.remove(originalName);
        createEntityData(updatedEntity);
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
    public void createObjectData (IReadableData data) {
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
        return true;
    }
}
