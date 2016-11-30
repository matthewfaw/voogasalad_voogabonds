package authoring.controller;

import authoring.model.IReadableData;

/**
 * @author Niklas Sjoquist
 *
 * An IDataController can create and edit a certain type of game object.
 *
 */
public interface IDataController {
    
    /**
     * Creates new game object (adds it to this' observableDataMap)
     */
    public void createObjectData(IReadableData data);
    
    /**
     * Edits the game object defined by input data (edits the reference in observableDataMap)
     * 
     * @param data - the data representing the game object
     * @throws Exception - if the name of the game object data does not match the names of any of the existing game objects
     */
    public void updateObjectData(String oldName, IReadableData data) throws Exception;
    
    /**
     * Returns the Data object for the specified object name
     * 
     * @param name
     * @return
     */
    public IReadableData getObjectData(String name);

}
