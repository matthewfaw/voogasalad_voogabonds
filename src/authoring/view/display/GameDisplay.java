package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import utility.Point;

/**
 * @author Christopher Lu
 * Creates the game display in the authoring environment, where the map is divided into clickable cells that allows the user
 * to change the property of the terrain upon click, including color and terrain type.
 */

public class GameDisplay {

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
	private MapDataContainer mapData;
	private Scene scene;
	private int tileSize;
	private HashMap<String, ArrayList<Point>> spawnPoints;
	private HashMap<String, ArrayList<Point>> sinkPoints;
	private HashSet<TerrainData> terrainList;
	private HashMap<String, String> validTerrain;
	private ArrayList<Point> usefulSinkPoints = new ArrayList<Point>();
	
	public GameDisplay(BorderPane root, Scene scene, MapDataContainer controller) {
		setUpScreenResolution();
		this.scene = scene;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.terrainContainer = new VBox();
		this.terrainArea = new ScrollPane();
		terrainArea.setId("background");
		this.terrainGrid = new TilePane();
		this.toolBar = new GridToolBar(terrainContainer, scene, controller);
		this.mapData = controller;
		importMapData();
		this.columns = controller.getNumXCells();
		this.rows = controller.getNumYCells();
		this.mapData.setDimensions(columns, rows);
		if (screenWidth/columns < (screenHeight*0.82)/rows) {
			this.tileSize = (int) (screenWidth/columns) - GAP;
		}
		else {
			this.tileSize = (int) ((screenHeight*0.82)/rows) - GAP;
		}
		terrainArea.setContent(terrainGrid);
		terrainContainer.getChildren().add(terrainArea);
		root.setCenter(terrainContainer);
		makeUsefulSink();
		populateGrid();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void importMapData() {
		spawnPoints = mapData.getSpawnPointMap();
		sinkPoints = mapData.getSinkPointMap();
		terrainList = mapData.getTerrainList();
		validTerrain = mapData.getValidTerrainMap();
	}
	
	private void makeUsefulSink() {
		Set<String> sinkNames = sinkPoints.keySet();
		for (String s : sinkNames) {
			usefulSinkPoints.add(sinkPoints.get(s).get(0));
		}
	}
	
	private void makeSinkPoint(int row, int col) {
		TerrainCell cell = new TerrainCell(mapData, toolBar, row, col, this);
		cell.setWidth(tileSize/2);
		cell.setHeight(tileSize/2);
		cell.setFill(Paint.valueOf(myResources.getString("DefaultSinkColor")));
		terrainGrid.getChildren().add(cell);
	}
	
	private void populateGrid() {
		terrainGrid.getChildren().clear();
		terrainGrid.setHgap(GAP);
		terrainGrid.setVgap(GAP);
		terrainGrid.setPrefColumns(columns);
		for (int r = 0; r < rows; r++) {
			for (int col = 0; col < columns; col++) {
				Point currentPoint = new Point((double)col, (double)r);
				if (usefulSinkPoints.contains(currentPoint)) {
					makeSinkPoint(r, col);
				}
				else {
					TerrainCell cell = new TerrainCell(mapData, toolBar, r, col, this);
					cell.setWidth(tileSize);
					cell.setHeight(tileSize);
					cell.setFill(Paint.valueOf(myResources.getString("DefaultCellColor")));
					terrainGrid.getChildren().add(cell);
				}
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
	
	public VBox getTerrainBox() {
		return terrainContainer;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
}
