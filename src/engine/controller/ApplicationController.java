package engine.controller;

import java.util.ResourceBundle;

import authoring.model.TowerData;
import engine.controller.timeline.TimelineController;
import engine.model.entities.IEntity;
import gamePlayerView.GUIPieces.MapView.MapDisplay;
import gamePlayerView.ScenePanes.BottomPane;
import gamePlayerView.ScenePanes.LeftPane;
import gamePlayerView.ScenePanes.RightPane;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import gamePlayerView.gamePlayerView.Router;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private GamePlayerScene myScene;
	//private Stage myStage; //////Guhan
	//private Pane myPane=new BorderPane();

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
		//myStage=aStage; ///Guhan 
		//GamePlayerScene scene = constructGUI(aStage);
		myScene=constructGUI(aStage);
		Router router = new Router(myScene);
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
	private BorderPane constructBorderPane(){
		/*myPane= new BorderPane();
		LeftPane myLeftPane=new LeftPane(this);
		RightPane myRightPane=new RightPane();
	    MapDisplay myMap = new MapDisplay(this);
		BottomPane myBottomPane = new BottomPane(this);
		//myCash.add(myLeftPane.getCash());
		//myLives.add(myLeftPane.getLives());
		//myResources.add(myRightPane.getTowerColumn());
		//mySprites.add(myMap.getSprites());
		borderpane.setRight(myRightPane.getView());
		borderpane.setBottom(myBottomPane.getView());
		borderpane.setCenter(myMap.getView());
		borderpane.setLeft(myLeftPane.getView());
		myMap.setupDragging(myScene);*/
		return null;
	}

	public void onPlayButtonPressed() {
		//AnimationController.play()
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

	public void onSellButtonPressed(TowerData tower) {
		// TODO Auto-generated method stub
//		return null;
	}
	
	public void onSavePressed() {
		// TODO Auto-generated method stub
	//	return null;
	}

	public void  onEntitySelected(IEntity aEntity)
	{
//		myScene.makeEntityInfoBox()
//			   .withUpgradeButton(...)
//			   .withTargetInfo()
//			   .with
//			   .build()
	}
	
	public void DisplayStats() throws Exception {
		myScene.updateTowerStatisticsRow();
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