package gamePlayerView.GUIPieces.MapView;

import java.util.ArrayList;
import java.util.Set;
//import javax.media.j3d.Group;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import authoring.model.TowerData;
import authoring.model.map.MapData;
import authoring.model.map.TerrainData;
import engine.IObservable;
import engine.IObserver;
import engine.IViewable;
import engine.controller.ApplicationController;
import engine.model.components.MoveableComponent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utility.Point;

public class MapGrid extends Node {
	private ApplicationController myAppController;
    private int numColumns;
    private int numRows;
    private Rectangle[][] actualGrid;
    private Pane myPane;
    private ArrayList<MoveableComponentView> sprites;
    private int myCellSize;
    private Rectangle closest; 
    
    //XXX: maybe remove this--just a quick fix
    public MapGrid(int rows, int cols, int aCellSize, ApplicationController aAppController){
    	myAppController = aAppController;
    	myPane = new Pane();
    	closest = new Rectangle();
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
				Dragboard db = event.getDragboard();
//				TowerData data = myTowerColumn.getTowerData(db.getString());
//				System.out.println(data.getBuyPrice());
//    			if(!isFull(temp)){
				Rectangle closestRectangle = findDropLocation(event.getX(), event.getY());
//    			}
				myAppController.onTowerDropped(db.getString(), new Point(closestRectangle.getX(), closestRectangle.getY()));
				setClickAction();

				event.consume();
    		}
    	});

    	actualGrid[row][col] = temp;
    	return actualGrid[row][col];
    }
    
    public void setClickAction(){
        for(MoveableComponentView m : sprites){
            m.setOnMouseClicked(e -> {
				try {
					setClickForComponent(m);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
        }
    }
    
    private void setClickForComponent(MoveableComponentView m) throws Exception {
        //get info to come up on click
        //m.getInfo();
    	myAppController.DisplayStats();
        System.out.println("hi");
    }
    
    public void giveViewableComponent(IObservable<IViewable> aObservable)
    {
    	MoveableComponentView aComponent = new MoveableComponentView(aObservable);
    	aObservable.attach(aComponent);
    	sprites.add(aComponent);
    	System.out.println("hey mom I am here");
    	myPane.getChildren().add(aComponent);
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
   
    
    public Rectangle findDropLocation(double x, double y){
        closest = new Rectangle();
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
        return closest;
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
