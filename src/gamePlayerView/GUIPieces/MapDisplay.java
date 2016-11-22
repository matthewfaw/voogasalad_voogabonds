package gamePlayerView.GUIPieces;
import java.awt.Dimension;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import authoring.view.display.GridToolBar;
import authoring.view.display.TerrainCell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
public class MapDisplay {
    
    private BorderPane myRoot;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public int DEFAULT_TILE_SIZE = 50;
    public int GAP = DEFAULT_TILE_SIZE/20;
    private ArrayList<ImageView> towers;
    private TilePane background;
    
    public MapDisplay(){
        
        background = new TilePane();
        myRoot = new BorderPane();
        towers = new ArrayList<ImageView>();
        init();
        setMap();
    }
    
    public void setMap(){
        background.setPadding(new Insets(1, 1, 1, 1));
        background.setVgap(1);
        background.setHgap(1);
        background.setPrefColumns(12);
        background.setPrefRows(12);
        background.setPrefTileHeight(50);
        background.setPrefTileWidth(50);

        background.setStyle("-fx-background-color: black;" + "-fx-border-color: white");

        myRoot.getChildren().add(background);
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
        
        myScene.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                ImageView source = new ImageView();
                source.setImage(event.getDragboard().getImage());
                source.setX(event.getX()-source.getImage().getWidth()/2);
                source.setY(event.getY()-source.getImage().getHeight()/2);
//                if(isColliding(source, towers))
//                {
//                    System.out.println("Try again");
//                }
//                else
//                {
                towers.add(source);
                background.getChildren().add(source);
               // }
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
    
    public void step (double elapsedTime) {
        //game-specific definition of a step
        //bad guys move, etc...
        //call every other classes step function
    }
    public Node getView() {
        return background;
    }
    
    //TODO: Fix math for collisions
    private boolean isColliding(ImageView source, ArrayList<ImageView> list){
        double x = source.getX();
        double y = source.getY();
        Point p = new Point();
        p.setLocation(x, y);
        for(ImageView n : list){
            double otherX = n.getX();
            double otherY = n.getY();
            Point other = new Point();
            other.setLocation(otherX, otherY);
            //System.out.println(p.distance(other));
            if(p.distance(other) < n.getImage().getWidth()){
                return true;
            }
        }
        return false;
    }
}
