package player.map;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapDisplay {
    
    private BorderPane myRoot;
    private Scene myScene;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    public MapDisplay(){
        myRoot = new BorderPane();
        myScene = new Scene(myRoot, 500, 500, Color.GHOSTWHITE);
    }
    
    public void init(Stage primaryStage){
       //Text source = new Text(50, 100, "DRAG ME");
        ImageView source = setupDragging();
        myRoot.getChildren().add(source);
        primaryStage.setScene(myScene);
        primaryStage.show();
        
      

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    private ImageView setupDragging(){
        ImageView source = new ImageView();
        Image img = new Image(getClass().getClassLoader().getResourceAsStream("buff_sticker.jpg"));
        source.setImage(img);
        source.setFitWidth(40);
        source.setFitHeight(40);
        
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("buff_sticker.jpg");
                db.setContent(content);
                event.consume();
            }  
        });
        
        myScene.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                
               event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
               event.consume();
            }
        });
        
        myScene.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                 //event.consume();
            }
        });
        
        myScene.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                source.setX(event.getX()-source.getFitWidth()/2);
                source.setY(event.getY()-source.getFitHeight()/2);
                event.consume();
            }
        });
       
        
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                event.consume();
            }
        });
        
        myScene.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                   success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);
                
                event.consume();
             }
        });
        
        return source;
    }

    
    public void step (double elapsedTime) {
        //game-specific definition of a step
        //bad guys move, etc... 
    }

}


