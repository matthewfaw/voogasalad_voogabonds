// This entire file is part of my masterpiece.
// Matthew Faw

package engine.controller.dispatcher;

import java.io.File;

import engine.model.entities.EntityManager;
import engine.model.entities.EntityReconstructor;
import engine.model.entities.IEntity;
import engine.model.strategies.filematching.IFileMatchingStrategy;

/**
 * The purpose of this class is to handle file change events relevant to Entities.
 * 
 * When the handleSignal command is called, the EntityChangeHandler manages reconstructing
 * all changed objects, while preserving their references
 * 
 * For example usage, see the DispatchManager.
 * To be registered to be updated on file change, register with the dispatch manager
 *  
 * This class is a receiver in the Chain of Responsibility
 * 
 * @author matthewfaw
 *
 */
public class EntityChangeHandler implements IHandler {
	private EntityManager myEntityManager;
	private EntityReconstructor myEntityReconstructor;
	private IFileMatchingStrategy<IEntity> myFileMatchingStrategy;
	
	public EntityChangeHandler(EntityReconstructor aEntityReconstructor, EntityManager aEntityManager, IFileMatchingStrategy<IEntity> aFileMatchingStrategy)
	{
		myEntityReconstructor = aEntityReconstructor;
		myEntityManager = aEntityManager;
		myFileMatchingStrategy = aFileMatchingStrategy;
	}

	@Override
	public synchronized void handleSignal(File aChangedFile) 
	{
		myEntityManager.getEntities().stream()
									 .filter(entity -> myFileMatchingStrategy.matches(entity, aChangedFile))
									 .map(entity -> myEntityReconstructor.reconstruct(entity, aChangedFile))
									 .forEach(newEntity -> myEntityManager.putEntity(newEntity.getId(), newEntity));
	}
}
