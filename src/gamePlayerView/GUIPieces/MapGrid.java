package gamePlayerView.GUIPieces;

import java.util.ArrayList;
import java.util.Set;
//import javax.media.j3d.Group;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapGrid extends Node{
    private int numColumns;
    private int numRows;
    private Rectangle[][] actualGrid;
    private Pane myPane;
    private ArrayList<MoveableComponentView> sprites;
    private int myCellSize;
    
    public MapGrid(int rows, int cols, int aCellSize){
       myPane = new Pane();
       sprites = new ArrayList<MoveableComponentView>();
       numColumns = cols;
       numRows = rows;
       myCellSize = aCellSize;
       actualGrid = new Rectangle[numRows][numColumns];
    }
    
    public Rectangle fillCell(int row, int col, int aCellSize, String aHexValue) {

    	Rectangle temp = new Rectangle();
    	temp.setFill(Color.web(aHexValue));
    	temp.setStroke(Color.BLACK);
    	temp.setStrokeWidth(1);
    	temp.setHeight(aCellSize);
    	temp.setWidth(aCellSize);
    	temp.setX(row*aCellSize);
    	temp.setY(col*aCellSize);
//    	loadTerrainData(temp, row, col, aMapData);

    	temp.setOnDragDropped(new EventHandler<DragEvent>() {
    		public void handle(DragEvent event) {
    			MoveableComponentView source = new MoveableComponentView();
//    			if(!isFull(temp)){
    				source.setImage(event.getDragboard().getImage());
    				findDropLocation(event.getX(), event.getY(), source);
//    			}
    			event.consume();
    		}
    	});

    	actualGrid[row][col] = temp;
    	return actualGrid[row][col];
    }
    
    private void loadTerrainData(Rectangle temp, int row, int col, MapData aMapData){
        //Set this up later
    }
    
    public void setRoot(Pane myRoot){
        myPane = myRoot;
    }
    
    @Deprecated
    private boolean isFull(Rectangle temp){
        return (temp.getFill() != Color.AQUA);    
    }
   
    
    public void findDropLocation(double x, double y, MoveableComponentView source){
        Rectangle closest = new Rectangle();
        double minDist = Integer.MAX_VALUE;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Rectangle temp = actualGrid[i][j];
                if(calculateDistance(x, y, temp.getX(), temp.getY()) < minDist)
                        {
                            minDist = calculateDistance(x, y, temp.getX(), temp.getY());
                            closest = temp;
                        }
                else
                {      
                }
            }
        }
        source.setX(closest.getX());
        source.setY(closest.getY());
        System.out.println("Dropping at: " + source.getX() + ", " + source.getY());
//        myApplicationController.onTowerDropped(source.)
        source.setFitHeight(closest.getHeight());
        source.setFitWidth(closest.getWidth());
        //sprites.add(source);
        myPane.getChildren().add(source);
    }
    
    private double calculateDistance (double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow((x - (x2+myCellSize/2)), 2)
                         + Math.pow((y - (y2 + myCellSize/2)), 2));
    }

    public int getNumCols(){
        return numColumns;
    }
    
    public int getNumRows(){
        return numRows;
    }
    
    public int getHeight(){
        return myCellSize;
    }
    
    public int getWidth(){
        return myCellSize;
    }

    @Override
    protected NGNode impl_createPeer () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds (BaseBounds bounds, BaseTransform tx) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean impl_computeContains (double localX, double localY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object impl_processMXNode (MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
