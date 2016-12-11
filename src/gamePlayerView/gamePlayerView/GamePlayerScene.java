package gamePlayerView.gamePlayerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.model.TowerData;
import engine.controller.ApplicationController;
import gamePlayerView.Resources;
import gamePlayerView.GUIPieces.GamePlayOptions;
import gamePlayerView.GUIPieces.TowerColumn;
import gamePlayerView.GUIPieces.InfoBoxes.DisplayBoxFactory;
import gamePlayerView.GUIPieces.InfoBoxes.InfoBox;
import gamePlayerView.GUIPieces.InfoBoxes.LivesBox;
import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.TowerStatistics;
import gamePlayerView.GUIPieces.MachineInfoView.TowerStatisticsandOptions;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeOrSell;
import gamePlayerView.GUIPieces.InfoBoxes.PauseMenu;
import gamePlayerView.GUIPieces.MapView.MapDisplay;
import gamePlayerView.ScenePanes.BottomPane;
import gamePlayerView.ScenePanes.LeftPane;
import gamePlayerView.ScenePanes.RightPane;
import gamePlayerView.builders.EntityInfoBox;
import gamePlayerView.builders.EntityInfoBoxBuilder;
//import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.IEnemiesKilledAcceptor;
import gamePlayerView.interfaces.IPlayerAcceptor;
import gamePlayerView.interfaces.IResourceAcceptor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GamePlayerScene {
	private static final String GAME_PLAYER_PATH = "resources/textfiles";
	private ApplicationController myAppController;
	private Stage myStage;
	private BottomPane myBottomPane;
	private LeftPane myLeftPane;
	private RightPane myRightPane;
	private MapDisplay myMap;
	private PauseMenu pause;
	private Scene myScene;
	private Pane myGamePlayer;
	private List<IPlayerAcceptor> myCash;
	private List<IPlayerAcceptor> myLives; 
	private List<IPlayerAcceptor> myWaves;
	private List<IResourceAcceptor> myResources;
	private List<IEnemiesKilledAcceptor> myEnemiesKilled;
	private BorderPane myBorderPane;
	private DisplayBoxFactory myBoxFactory;
	private ResourceBundle myResourceBundle;
	//private List<MoveableComponentView> mySprites;
	

	public GamePlayerScene(Stage aStage, ApplicationController aAppController) throws Exception{
    	myAppController = aAppController;
		myCash = new ArrayList<IPlayerAcceptor>();
		myLives = new ArrayList<IPlayerAcceptor>();
		myWaves = new ArrayList<IPlayerAcceptor>();
		myResources = new ArrayList<IResourceAcceptor>();
		myStage=aStage;
		myBoxFactory=new DisplayBoxFactory();
		myBorderPane=new BorderPane();
		myResourceBundle=ResourceBundle.getBundle(GAME_PLAYER_PATH);
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
		//myGamePlayer =new Pane();
		myBorderPane.setPrefWidth(Resources.SCREEN_WIDTH);
		myBorderPane.setPrefHeight(Resources.SCREEN_HEIGHT);
		//myGamePlayer.setPrefWidth(Resources.SCREEN_WIDTH);
		//myGamePlayer.setPrefHeight(Resources.SCREEN_HEIGHT);
		//myScene = new Scene(myGamePlayer);
		myScene=new Scene(myBorderPane);
		setScreen();
		//myGamePlayer.getChildren().add(myBorderPane);
		return myScene;
	}
	//This might be called by controller
	/*public void rebuild(Stage aStage,BorderPane aPane) {
		myScene = new Scene(myGamePlayer, 1000, 700);
		myGamePlayer.getChildren().clear();
		myGamePlayer.getChildren().add(updateScreen(aPane));
		setScene(aStage,myScene);
	}*/

	public void updateTowerStatisticsRow(/*TowerData tower*/) throws Exception {
		myBottomPane.clear();
		Collection<Node> myCollection=new ArrayList<Node>();
		TowerStatistics myTowerStatistics=new TowerStatistics();
		TargetingButtons myTargetingMechanism=new TargetingButtons();
		VBox myTowerOptions=new VBox();
		myTowerOptions.setSpacing(10);
		myTowerOptions.getChildren().addAll(myTowerStatistics.getView(),myTargetingMechanism.getView());
	    UpgradeOrSell myUpgradeandSell=new UpgradeOrSell();
	    MachineInfo myInfo=new MachineInfo();
	    myCollection.add(myInfo.getView());
		myCollection.add(myTowerOptions);
		myCollection.add(myUpgradeandSell.getView());
		myBottomPane.add(myCollection);
		myBorderPane.setBottom(myBottomPane.getView());
	}

	public void setScreen() throws Exception{
		//myTowerColumn   = new TowerColumn();
		//myGamePlayOptions=new GamePlayOptions(myAppController);
		myLeftPane=createLeftPane();
		myRightPane=createRightPane();
		myBottomPane=createBottomPane();
	    myMap = new MapDisplay(myAppController);
		//mySprites.add(myMap.getSprites());
	    pause = new PauseMenu();
        makePauseMenu();
		myBorderPane.setRight(myRightPane.getView());
		myBorderPane.setBottom(myBottomPane.getView());
		myBorderPane.setCenter(myMap.getView());
		myBorderPane.setLeft(myLeftPane.getView());
		myMap.setupDragging(myScene);
		//return borderpane;
	}
	private BottomPane createBottomPane() {
		BottomPane pane=new BottomPane();
		Label l =new Label("Wassup");
		Collection<Node> myCollection=new ArrayList<Node>();
		//MachineInfo myInfo=new MachineInfo();
		//myCollection.add(myInfo.getView());
		myCollection.add(l);
		pane.add(myCollection);
		return pane;
	}

	private RightPane createRightPane() {
		RightPane pane=new RightPane();
		TowerColumn myTowerColumn=new TowerColumn();
		Collection<Node> myCollection=new ArrayList<Node>();
		myCollection.add(myTowerColumn.getView());
		myResources.add(myTowerColumn);
		pane.add(myCollection);
		return pane;
	}

	private LeftPane createLeftPane() {
		LeftPane pane=new LeftPane();
		GamePlayOptions myGamePlayOptions=new GamePlayOptions(myAppController);
		InfoBox myWallet=myBoxFactory.createBox(myResourceBundle.getString("Cash"));
		InfoBox myLife=myBoxFactory.createBox(myResourceBundle.getString("Lives"));
		myCash.add((IPlayerAcceptor) myWallet); ///FIX LATER
		myLives.add((IPlayerAcceptor) myLife);//// FIX LATER
		Collection<Node> myCollection=new ArrayList<Node>();
		myCollection.add(myGamePlayOptions.getView());
		myCollection.add(myWallet.getView());
		myCollection.add(myLife.getView());
		pane.add(myCollection);
		return pane;
	}


	public void makePauseMenu(){ 
            myScene.setOnKeyPressed(e -> pause.handleKeyInput(e.getCode()));               
    }
	
	public List<IPlayerAcceptor> getCash() {
		return myCash;
	}

	public List<IPlayerAcceptor> getLives() {
		return myLives;
	}

	public List<IPlayerAcceptor> getWaves() {
		return myWaves;
	}
	
	public List<IEnemiesKilledAcceptor> getEnemiesKilled() {
		return myEnemiesKilled;
	}
	
	public void giveMapData(MapDataContainer aMapData){
	        myMap.setMap(aMapData);
	}
	
	public MapDisplay getMapDisplay()
	{
		return myMap;
	}

	public EntityInfoBox makeEntityInfoBox()
	{
		return null;
		//return new EntityInfoBox.EntityInfoBoxBuilder(this);
	}
	
	public List<IResourceAcceptor> getResources() {
		//TODO;Refactor later to seperate the Resource object from tower column. Not doing now so I don't screw with Grayson's stuff
		return myResources;
	}
	//TODO:Uncomment
	//public List<ISprite> getSprites(){
		//return mySprites;
	//}

	public void updateDisplay(EntityInfoBox myStatisticsBox) {
		myBottomPane.clear();
		Collection<Node> myCollection=new ArrayList<Node>();
		myCollection.add(myStatisticsBox.getView());
		myBottomPane.add(myCollection);
	}
	
}

