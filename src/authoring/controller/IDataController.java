package authoring.controller;

/**
 * Ideally an interface which all DataControllers implement. finalizeData() is a method
 * used to ensure everything in a DataController is valid before returning it as valid
 * game data.
 * 
 * @author philipfoo
 *
 */
public interface IDataController {
	public void finalizeData() throws Exception;

}
