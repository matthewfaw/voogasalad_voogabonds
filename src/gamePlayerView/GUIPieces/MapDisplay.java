package gamePlayerView.GUIPieces;

import authoring.model.map.MapData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class MapDisplay implements IObserver<TimelineController> {
    
    private Pane myRoot;
    private Pane myPane;
    private MapGrid background;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	//TODO: matthewfaw
    //Set up map from backend
    //register as observer of timeline
    public MapDisplay() throws Exception{
        myRoot = new Pane();
        myPane = new Pane();
        init();
        MapData temp = new MapData();
        temp.setNumXCells(10);
        temp.setNumYCells(10);
        background = new MapGrid(temp.getNumXCells(), temp.getNumYCells());
        setMap(temp);
    }
    
    public void setMap(MapData aMapData){
        for(int i = 0; i < background.getNumRows(); i++)
        {
            for(int j = 0; j < background.getNumCols(); j++)
            {
                myPane.getChildren().add(background.fillCell(i, j, aMapData));
            }
        }
        
        myRoot.getChildren().add(myPane);
        background.setRoot(myRoot);
    }
    
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
    
    public Node getView() {
        return myRoot;
    }

	@Override
	public void update(TimelineController aChangedObject) {
		//step
	}
}