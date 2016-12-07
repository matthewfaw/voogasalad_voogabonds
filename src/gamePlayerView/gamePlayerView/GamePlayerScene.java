package gamePlayerView.gamePlayerView;

import java.util.ArrayList;
import java.util.List;
import authoring.model.map.MapData;
import engine.controller.ApplicationController;
import gamePlayerView.GUIPieces.GamePlayOptions;
import gamePlayerView.GUIPieces.TowerColumn;
import gamePlayerView.GUIPieces.InfoBoxes.CashBox;
import gamePlayerView.GUIPieces.InfoBoxes.LivesBox;
import gamePlayerView.GUIPieces.MapView.MapDisplay;
import gamePlayerView.ScenePanes.BottomPane;
import gamePlayerView.ScenePanes.LeftPane;
import gamePlayerView.ScenePanes.RightPane;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;
import gamePlayerView.interfaces.IResourceAcceptor;
import gamePlayerView.interfaces.IWavesAcceptor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GamePlayerScene {
	private ApplicationController myAppController;
	
	private Stage myStage;
	private TowerColumn myTowerColumn;
	private BottomPane myStatisticsRow;
	private GamePlayOptions myGamePlayOptions;
	private LeftPane myLeftPane;
	private RightPane myRightPane;
	private MapDisplay myMap;
	private Scene myScene;
	private List<ICashAcceptor> myCash;
	private List<ILivesAcceptor> myLives; 
	private List<IWavesAcceptor> myWaves;
	private List<IResourceAcceptor> myResources;
	private ApplicationController myApplicationController;
	//private List<MoveableComponentView> mySprites;
	

	public GamePlayerScene(Stage aStage, ApplicationController aAppController) throws Exception{
		//myStage=stage;
    	myAppController = aAppController;
		myCash = new ArrayList<ICashAcceptor>();
		myLives = new ArrayList<ILivesAcceptor>();
		myWaves = new ArrayList<IWavesAcceptor>();
		myResources = new ArrayList<IResourceAcceptor>();
		//myApplicationController=applicationController;
		//mySprites=new ArrayList<ISprite>();
		init(aStage);
	}

	public void init(Stage s) throws Exception {
		Scene myScene=build(s);
		setScene(s,myScene);
	}

	private void setScene(Stage s, Scene scene) { ///public or private
		s.setScene(scene);
		s.show();
	}
	/*
	public List<ICashAcceptor> getCashAcceptors()
	{
		List<ICashAcceptor> acceptors = new ArrayList<ICashAcceptor>();
		acceptors.add(gui.getCash());
		return acceptors;
	}
	public List<IResourceStoreAcceptor> getResourceStoreAcceptors()
	{
		//TODO:
		// get all frontend components that need info from the resource store (available towers, ect)
	}
	*/

	public Scene build(Stage stage) throws Exception {
		Group gameplayer =new Group();
		myScene = new Scene(gameplayer, 900, 700);
		gameplayer.getChildren().add(setScreen());
		return myScene;
	}
	
	public BorderPane setScreen() throws Exception{
		//myTowerColumn   = new TowerColumn();
		//myGamePlayOptions=new GamePlayOptions(myAppController);
		myLeftPane=new LeftPane(myAppController);
		myRightPane=new RightPane();
	    myMap = new MapDisplay(myAppController);
		myStatisticsRow = new BottomPane(myAppController);
		myCash.add(myLeftPane.getCash());
		myLives.add(myLeftPane.getLives());
		myResources.add(myRightPane.getTowerColumn());
		//mySprites.add(myMap.getSprites());
		BorderPane borderpane=new BorderPane();
		borderpane.setRight(myRightPane.getView());
		borderpane.setBottom(myStatisticsRow.getView());
		borderpane.setCenter(myMap.getView());
		borderpane.setLeft(myLeftPane.getView());
		myMap.setupDragging(myScene);
		return borderpane;
	}
	
	public List<ICashAcceptor> getCash() {
		return myCash;
	}

	public List<ILivesAcceptor> getLives() {
		return myLives;
	}

	public List<IWavesAcceptor> getWaves() {
		return myWaves;
	}
	
	public void giveMapData(MapData aMapData){
	        myMap.setMap(aMapData);
	}
	
	public MapDisplay getMapDisplay()
	{
		return myMap;
	}

	public List<IResourceAcceptor> getResources() {
		//TODO;Refactor later to seperate the Resource object from tower column. Not doing now so I don't screw with Grayson's stuff
		return myResources;
	}
	//TODO:Uncomment
	//public List<ISprite> getSprites(){
		//return mySprites;
	//}
	
}


