package gamePlayerView.GUIPieces.MapView;

import java.util.ArrayList;

import authoring.controller.MapDataContainer;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import engine.model.machine.IViewableMachine;
import gamePlayerView.GUIPieces.InfoBoxes.Controls;
import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import engine.controller.ApplicationController;
import engine.controller.timeline.TimelineController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * 
 * @author graysonwise
 *
 */

public class MapDisplay implements IObserver<TimelineController> {
    
    private ApplicationController myAppController;
    private Pane myRoot;
    private Pane myPane;
    private MapGrid background;
    private ArrayList<MoveableComponentView> sprites;
    private Controls myControls;
    private static boolean isPlaying;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	//TODO: matthewfaw
    //Set up map from backend
    //register as observer of timeline
    public MapDisplay(ApplicationController aAppController) throws Exception{
    	myAppController = aAppController;
        sprites = new ArrayList<MoveableComponentView>();
        myRoot = new Pane();
        myPane = new Pane();
        myControls = new Controls();
        init();
        isPlaying = false;
    }
    
    public void giveViewableComponent(IObservable<IViewable> aObservable)
    {
    	background.giveViewableComponent(aObservable);
    }
    
    public void setMap(MapDataContainer aMapData){
        background = new MapGrid(aMapData.getNumXCells(), aMapData.getNumYCells(), aMapData.getCellSize(), myAppController);
        for (TerrainData terrainData: aMapData.getTerrainList()) {
        	//XXX: I don't like that we have to cast here
        	myPane.getChildren().add (background.fillCell((int)terrainData.getLoc().getX(), 
        	                                             (int)terrainData.getLoc().getY(), 
        						terrainData.getSize(), 
        						terrainData.getColor(),
        						myRoot));
        }
        
        myRoot.getChildren().add(myPane);
        background.setRoot(myRoot);
        myRoot.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }
    
    //TODO: Use timeline controller instead
    @Deprecated
    public void init(){
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    public void setupDragging(Scene myScene){
        
        myScene.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
               
               event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
               event.consume();
            }
        });
        
        myScene.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                 event.consume();
            }
        });
        
        myScene.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event){
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                   success = true;
                }
                event.setDropCompleted(success);
                
                event.consume();
             }
        });
    }


    //Use the update(TimelineController) method instead
    @Deprecated 
    public void step (double elapsedTime) {
        //game-specific definition of a step
        //bad guys move, etc...
        //call every other classes step function        
        
    }
    
    public static void setPlaying(boolean val){
        isPlaying = val;
    }
    
    public static boolean isPlaying(){
        return isPlaying;
    }
    public Node getView() {
        return myRoot;
    }

    @Override
    public void update(TimelineController aChangedObject) {
        //TODO:
    }
    
    public void handleKeyInput(KeyCode code){
        if(code.getName() == myControls.getControlFor("UP")){
            System.out.println("Up!");
        }
    }

    public void getControls(Controls cont) {
        myControls = cont;
    }

	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.
	}
}