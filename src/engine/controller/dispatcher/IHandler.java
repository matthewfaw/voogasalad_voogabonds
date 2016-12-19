package engine.controller.dispatcher;

import java.io.File;

/**
 * The purpose of this class is to handle a signal related to a file.
 * 
 * This could be relevant, for example, to a file change
 * 
 * @author matthewfaw
 *
 */
public interface IHandler {
	
	/**
	 * a method to handle the signal related to the changed file
	 * @param aChangedFile a file that has recently changed
	 */
	public void handleSignal(File aChangedFile);

}
