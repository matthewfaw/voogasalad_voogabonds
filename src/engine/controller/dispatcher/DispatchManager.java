// This entire file is part of my masterpiece.
// Matthew Faw

package engine.controller.dispatcher;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import engine.model.strategies.filematching.IFileMatchingStrategy;
import gamePlayerView.gamePlayerView.Router;
import utility.file_io.FileChangeNotifier;

/**
 * The purpose of this class is to manage dispatching handlers for the file change events.
 * Relevant handlers may register with this class, and when a file change, if the file is 
 * determined to be relevant to the handler, the handler will be called.
 * 
 * In order for the onFileChanged method to be called, this manager must be registered with the FileChangeNotifier
 * Thus, we assume that no other class will register with the dispatch manager
 * 
 * To use this class, you must first start the FileChangeNotifier on its own thread.
 * Create Handlers such as the EntityChangeHandler:
 * IHandler handler = new EntityChangeHandler(...);
 * dispatchManager.registerHandler(handler);
 * 
 * Whenever a file change event occurs that is relevant (as determined by the FileMatchingStrategy with which
 * the DispatchManager is constructed), each registered handler is called
 * 
 * This class uses the Chain of Responsibility design pattern to provide a layer of indirection between the FileChangeNotifier
 * and the handlers which handle the change events.
 * This allows a variable number of Handlers to register with this manager and be notified of the file change if it
 * is relevant to them
 * 
 * @author matthewfaw
 *
 */
public class DispatchManager {
	private Collection<IHandler> myHandlers;
	private IFileMatchingStrategy<IHandler> myFileMatchingStrategy;
	
	/**
	 * Default constructor for the DispatchManager
	 * @param aFileChangeNotifier the notifier that triggers the dispatching
	 * @param aFileMatchingStrategy the strategy with which the manager determines whether a 
	 * specific change handler should be notified
	 * @param aRouter the router through which errors may be distributed
	 */
	public DispatchManager(FileChangeNotifier aFileChangeNotifier, IFileMatchingStrategy<IHandler> aFileMatchingStrategy, Router aRouter)
	{
		myHandlers = new HashSet<IHandler>();
		myFileMatchingStrategy = aFileMatchingStrategy;
		
		aFileChangeNotifier.onFileChangeDetected(file -> this.onFileChanged(file));
		aFileChangeNotifier.onErrorDetected(error -> aRouter.distributeErrors(error));
	}
	
	/**
	 * A method to register a file change handler with this class
	 * Registering this handler allows the handler to be called when
	 * the file change is relevant
	 * 
	 * @param aFileChangeHandler the handler to be registered
	 */
	public void registerHandler(IHandler aFileChangeHandler)
	{
		myHandlers.add(aFileChangeHandler);
	}
	
	/**
	 * A synchronized method that manages dispatching notifications to all 
	 * @param aChangedFile a file that just changed
	 */
	public synchronized void onFileChanged(File aChangedFile)
	{
		myHandlers.stream()
					 .filter(dispatcher -> myFileMatchingStrategy.matches(dispatcher, aChangedFile))
					 .forEach(dispatcher -> dispatcher.handleSignal(aChangedFile));
	}

}
