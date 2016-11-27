package engine.controller;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import gamePlayerView.GamePlayerScene;
import gamePlayerView.Router;

/**
 * A main controller whose primary purpose is to
 * create the frontend and backend objects and to manage 
 * communication from frontend to backend
 * 
 * Communication from backend to frontend will be established
 * by the Router object, and will be maintained using the 
 * Observer/Observable pattern of communication
 * 
 * @author matthewfaw
 *
 */
public class ApplicationController {
	private static final String GAME_OPTIONS_PATH = "resources/game_options/GamePaths";

	private ResourceBundle myGameOptions;
	private BackendController myBackendController;

	public ApplicationController()
	{
		myGameOptions = ResourceBundle.getBundle(GAME_OPTIONS_PATH);
	}
	
	/**
	 * A method to be called to initialize the frontend and backend
	 */
	public void init()
	{
		GamePlayerScene scene = constructGUI();
		Router router = new Router(scene);
		constructBackend(router);
	}
	/**
	 * Helper method to create the view object
	 * from the GameData file
	 */
	private GamePlayerScene constructGUI()
	{
		GamePlayerScene scene = new GamePlayerScene();
		return scene;
	}
	/**
	 * A method to handle the construction of the backend controller
	 * This method establishes the link between the frontend and backend
	 * through the Router class
	 * @param aRouter
	 */
	private void constructBackend(Router aRouter)
	{
		//TODO: Change this to make this dynamic--select different games
		myBackendController = new BackendController(myGameOptions.getString("DefaultGame"), aRouter);
	}
	
	public static void main(String[] args)
	{
		ApplicationController appcont = new ApplicationController();
		appcont.init();
//		appcont.constructMap();
	}
	
}