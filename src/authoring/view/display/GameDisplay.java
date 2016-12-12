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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
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
	private ArrayList<Point> usefulSpawnPoints = new ArrayList<Point>();
	private ArrayList<Point> usefulTerrainPoints = new ArrayList<Point>();
	private ArrayList<String> usefulTerrainFills = new ArrayList<String>();
	private Set<String> spawnNames;
	
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
		this.columns = mapData.getNumXCells();
		this.rows = mapData.getNumYCells();
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
		makeUsefulSpawn();
		makeUsefulTerrain();
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
	
	private void makeUsefulSpawn() {
		spawnNames = spawnPoints.keySet();
		for (String s: spawnNames) {
			usefulSpawnPoints.add(spawnPoints.get(s).get(0));
		}
	}
	
	private void makeUsefulTerrain() {
		for (TerrainData terrain : terrainList) {
			usefulTerrainPoints.add(terrain.getLoc());
			usefulTerrainFills.add(terrain.getColor());
		}
	}
	
	private void makeSinkPoint(int row, int col, Point currentPoint) {
		TerrainCell cell = new TerrainCell(mapData, toolBar, row, col, this);
		cell.setStroke(Paint.valueOf(myResources.getString("DefaultSinkColor")));
		cell.setWidth(tileSize*0.9);
		cell.setHeight(tileSize*0.9);
		cell.setStrokeWidth(tileSize*0.1);
		int index = usefulTerrainPoints.indexOf(currentPoint);
		try {
			cell.setFill(Paint.valueOf(usefulTerrainFills.get(index)));
		} catch (Exception e) {
			Image image = new Image(usefulTerrainFills.get(index));
			ImagePattern pattern = new ImagePattern(image);
			cell.setFill(pattern);
		}
		terrainGrid.getChildren().add(cell);
	}
	
	private void makeSpawnPoint(int row, int col, Point currentPoint) {
		TerrainCell cell = new TerrainCell(mapData, toolBar, row, col, this);
		cell.setStroke(Paint.valueOf(myResources.getString("DefaultSpawnColor")));
		cell.setWidth(tileSize*0.9);
		cell.setHeight(tileSize*0.9);
		cell.setStrokeWidth(tileSize*0.1);
		int index = usefulTerrainPoints.indexOf(currentPoint);
		try {
			cell.setFill(Paint.valueOf(usefulTerrainFills.get(index)));
		} catch (Exception e) {
			Image image = new Image(usefulTerrainFills.get(index));
			ImagePattern pattern = new ImagePattern(image);
			cell.setFill(pattern);
		}
//		for (String name: spawnNames) {
//			if (spawnPoints.get(name).equals(o))
//		}
		terrainGrid.getChildren().add(cell);
	}
	
	private void makeTerrainPoint(int row, int col, Point currentPoint) {
		TerrainCell cell = new TerrainCell(mapData, toolBar, row, col, this);
		cell.setWidth(tileSize);
		cell.setHeight(tileSize);
		int index = usefulTerrainPoints.indexOf(currentPoint);
		try {
			cell.setFill(Paint.valueOf(usefulTerrainFills.get(index)));
		} catch (Exception e) {
			Image image = new Image(usefulTerrainFills.get(index));
			ImagePattern pattern = new ImagePattern(image);
			cell.setFill(pattern);
		}
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
					makeSinkPoint(r, col, currentPoint);
				}
				else if (usefulSpawnPoints.contains(currentPoint)) {
					makeSpawnPoint(r, col, currentPoint);
				}
				else if (usefulTerrainPoints.contains(currentPoint)) {
					makeTerrainPoint(r, col, currentPoint);
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
