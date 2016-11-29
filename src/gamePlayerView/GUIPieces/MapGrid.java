package gamePlayerView.GUIPieces;

import java.util.ArrayList;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapGrid extends Node{
    private int numColumns;
    private int numRows;
    private int myHeight;
    private int myWidth;
    public final int CELL_SIZE = 50;
    private Rectangle[][] actualGrid; 
    
    public MapGrid(int rows, int cols){
       numColumns = cols;
       numRows = rows;
       myHeight = CELL_SIZE;
       myWidth = CELL_SIZE;
       actualGrid = new Rectangle[numRows][numColumns];
    }
    
//    public Rectangle drawGrid() {
//        Rectangle toReturn = new Rectangle();
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                toReturn = fillCell(i, j);
//            }
//        }
//        return toReturn;
//    }
    
    public Rectangle fillCell(int row, int col) {
        
        Rectangle temp = new Rectangle();
        temp.setFill(Color.AQUA);
        temp.setStroke(Color.BLACK);
        temp.setStrokeWidth(1);
        temp.setHeight(myHeight);
        temp.setWidth(myWidth);
        temp.setX(row*CELL_SIZE);
        temp.setY(col*CELL_SIZE);
        temp.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                ImageView source = new ImageView();
                source.setImage(event.getDragboard().getImage());
                findDropLocation(event.getX(), event.getY(), source);
                //temp.setFill(new ImagePattern(source.getImage()));
                event.consume();
                
            }
        });
        
        
        actualGrid[row][col] = temp;
        return actualGrid[row][col];
    }
    
    public void findDropLocation(double x, double y, ImageView source){
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
        source.setX(closest.getX() + CELL_SIZE/2);
        source.setY(closest.getY() + CELL_SIZE/2);
        closest.setFill(new ImagePattern(source.getImage()));
    }
    
    private double calculateDistance (double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow((x - (x2+CELL_SIZE/2)), 2) + Math.pow((y - (y2 + CELL_SIZE/2)), 2));
    }

    public int getNumCols(){
        return numColumns;
    }
    
    public int getNumRows(){
        return numRows;
    }
    
    public int getHeight(){
        return myHeight;
    }
    
    public int getWidth(){
        return myWidth;
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
