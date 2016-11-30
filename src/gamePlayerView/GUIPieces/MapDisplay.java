package gamePlayerView.GUIPieces;

import authoring.model.map.MapData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class MapDisplay {
    
    private Group myRoot;
    private Pane thing;
    private MapGrid background;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    public MapDisplay() throws Exception{
        
        myRoot = new Group();
        thing = new Pane();
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
                thing.getChildren().add(background.fillCell(i, j));
            }
        }
        
        myRoot.getChildren().add(thing);
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
        
//        myScene.setOnDragExited(new EventHandler<DragEvent>() {
//            public void handle(DragEvent event) {
//                ImageView source = new ImageView();
//                source.setImage(event.getDragboard().getImage());
////                source.setX(event.getX()-source.getImage().getWidth()/2);
////                source.setY(event.getY()-source.getImage().getHeight()/2);
////                System.out.println("X and Y of dropped tower: " + source.getX() + ", " + source.getY());
//                //TODO: add tower to list of towers
//                //background.findDropLocation(event.getX(), event.getY(), source);
//                //System.out.println("X and Y of dropped tower: " + source.getX() + ", " + source.getY());
////                thing.getChildren().add(source);
//                event.consume();
//            }
//        });
        
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


    public void step (double elapsedTime) {
        //game-specific definition of a step
        //bad guys move, etc...
        //call every other classes step function
    }
    
    public Node getView() {
        return myRoot;
    }
}