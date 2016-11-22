package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * @author Christopher Lu
 * Creates the game display in the authoring environment, where the map is divided into clickable cells that allows the user
 * to change the property of the terrain upon click, including color and terrain type.
 */

public class GameDisplay {

	private Scene scene;
	private VBox terrainContainer;
	private ScrollPane terrainArea;
	private TilePane terrainGrid;
	private GridToolBar toolBar;
	public int DEFAULT_TILE_SIZE = 50;
	public int GAP = DEFAULT_TILE_SIZE/20;
	private int columns; // Number of blocks in the horizontal direction of the gridPane. Can be set by user.
	private int rows; // Number of blocks in the vertical direction of the gridPane. Can be set by user.
	private int screenWidth;
	private int screenHeight;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public GameDisplay(BorderPane root, Scene s) {
		setUpScreenResolution();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.scene = s;
		this.terrainContainer = new VBox();
		this.terrainArea = new ScrollPane();
		this.terrainGrid = new TilePane();
		this.toolBar = new GridToolBar(terrainContainer, scene);
		terrainGrid.setPrefWidth(screenWidth*0.8);
		terrainGrid.setMaxHeight(screenHeight*0.9);
		terrainArea.setContent(terrainGrid);
		terrainContainer.getChildren().add(terrainArea);
		root.setCenter(terrainContainer);
		columns = (int) (screenWidth*0.8/(DEFAULT_TILE_SIZE + GAP));
		rows = (int) (screenHeight*0.88/(DEFAULT_TILE_SIZE + GAP));
		populateGrid();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void populateGrid() {
		terrainGrid.getChildren().clear();
		terrainGrid.setHgap(GAP);
		terrainGrid.setVgap(GAP);
		terrainGrid.setPrefColumns(columns);
		System.out.println(columns);
		System.out.println(rows);
		for (int col = 0; col < columns; col++) {
			for (int r = 0; r < rows; r++) {
				TerrainCell cell = new TerrainCell(toolBar, r, col);
				cell.setWidth(DEFAULT_TILE_SIZE);
				cell.setHeight(DEFAULT_TILE_SIZE);
				cell.setFill(Paint.valueOf(myResources.getString("DefaultCellColor")));
				terrainGrid.getChildren().add(cell);
			}
		}
	}

	public void setCols(int numCols) {
		columns = numCols;
		terrainGrid.setPrefColumns(columns);
		populateGrid();
	}
	
	public void setRows(int numRows) {
		rows = numRows;
		terrainGrid.setPrefRows(rows);
		populateGrid();
	}
	
}
