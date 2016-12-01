package engine.controller;

import java.util.ResourceBundle;

import authoring.model.TowerData;
import engine.controller.timeline.TimelineController;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import gamePlayerView.gamePlayerView.Router;
import javafx.stage.Stage;
import utility.Point;

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
	//XXX: maybe make a frontend controller, and move this there
	private TimelineController myAnimationTimelineController;

	public ApplicationController()
	{
		myGameOptions = ResourceBundle.getBundle(GAME_OPTIONS_PATH);
	}
	
	/**
	 * A method to be called to initialize the frontend and backend
	 * @throws Exception 
	 */
	public void init(Stage aStage) throws Exception
	{
		GamePlayerScene scene = constructGUI(aStage);
		Router router = new Router(scene);
		constructBackend(router);
	}
	/**
	 * Helper method to create the view object
	 * from the GameData file
	 * @throws Exception 
	 * TODO: Move to a frontend controller?
	 */
	private GamePlayerScene constructGUI(Stage aStage) throws Exception
	{
		GamePlayerScene scene = new GamePlayerScene(aStage, this);
		myAnimationTimelineController = new TimelineController();
		myAnimationTimelineController.attach(scene.getMapDisplay());
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
		myBackendController = new BackendController(myGameOptions.getString("ExampleGame"), aRouter);
	}

	public void onPlayButtonPressed() {
		myBackendController.startTimeLine();
	}

	public void onPauseButtonPressed() {
		//AnimationController.pause()
	}

	public void onFastButtonPressed() {
		//AnimationController.fastForward()
	}

	public void onSlowButtonPressed() {
		//AnimationController.slow()
	}

	public void onUpgradeButtonPressed() {
		//
	}

	public void onSellButtonPressed() {
		//
	}
	
	public void onTowerDropped(String aTowerName, Point aDropLocation)
	{
		myBackendController.attemptToPlaceTower(aTowerName, aDropLocation);
	}
	
	/*
	public static void main(String[] args)
	{
		ApplicationController appcont = new ApplicationController();
		appcont.init();
//		appcont.constructMap();
	}
	*/
	
}