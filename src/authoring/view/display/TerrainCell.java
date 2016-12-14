package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import utility.ErrorBox;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.Point;

/**
 * @author Christopher Lu
 * Creates the individual Terrain unit for authoring front end. This unit's color and terrain type can be changed upon click.
 */

public class TerrainCell extends Rectangle {

	private String cellName;
	private String terrainType;
	private GridToolBar toolBar;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private int rowLocation;
	private int colLocation;
	private int screenHeight;
	private int screenWidth;
	private MapDataContainer controller;
	private int DEFAULT_TILE_SIZE;
	private Point point;
	private GameDisplay gameDisplay;
	private String relPath = "";
	
	public TerrainCell(MapDataContainer c, GridToolBar tools, int row, int column, GameDisplay disp) {	
		setUpScreenResolution();
		this.toolBar = tools;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.terrainType = myResources.getString("DefaultTerrainType");
		this.rowLocation = row;
		this.colLocation = column;
		this.controller = c;
		this.DEFAULT_TILE_SIZE = Integer.parseInt(myResources.getString("DefaultTileSize"));
		this.point = new Point(column, row);
		this.gameDisplay = disp;
		clickEvent();
	}
	
	private void clickEvent() {
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					if (toolBar.getToggleStatus()) {
						if (toolBar.getSelectedTerrain().equals(myResources.getString("DNE"))) {
							ErrorBox.displayError("Please Choose a Valid Terrain to use.");
							mouseEvent.consume();
						}
						
						else if (toolBar.getImageStatus()){
							String[] splitPath = toolBar.getSelectedImagePath().toString().split("src/");
							relPath = "";
							if (toolBar.getImageStatus() && splitPath.length > 1) {
								relPath += splitPath[1] + "/";
								System.out.println("THISISTHERELATIVEPATH: " + relPath);
							}
							
							Image image = new Image(toolBar.getSelectedImagePath());
							ImagePattern imagePattern = new ImagePattern(image);
							setFill(imagePattern);
							controller.addTerrainData(new TerrainData(TerrainCell.this.getType(), colLocation, rowLocation, (int) TerrainCell.this.getHeight(), relPath));
							setType(toolBar.getSelectedTerrain(), toolBar.getSelectedImagePath().toString());
							}
						else {
							controller.addTerrainData(new TerrainData(TerrainCell.this.getType(), colLocation, rowLocation, (int) TerrainCell.this.getHeight(), toolBar.getSelectedColor().toString()));
							setFill(toolBar.getSelectedColor());
							setType(toolBar.getSelectedTerrain(), toolBar.getSelectedColor().toString());
						}

						try {
							TerrainCell.this.getStroke().equals(Paint.valueOf("Red"));
							setWidth(gameDisplay.getTileSize()*0.9);
							setHeight(gameDisplay.getTileSize()*0.9);
						} catch (Exception e) {
							setWidth(gameDisplay.getTileSize());
							setHeight(gameDisplay.getTileSize());
						}
					}
					else if (toolBar.getSpawnStatus()) {
						try {
							TerrainCell.this.getStroke().equals(myResources.getString("DefaultSinkColor"));
							controller.removeSinkPoint(cellName);
						} catch (Exception e) {
							
						}
						ArrayList<Point> points = new ArrayList<Point>();
						points.add(new Point(colLocation, rowLocation));
						setWidth(gameDisplay.getTileSize()*0.9);
						setHeight(gameDisplay.getTileSize()*0.9);
						setStroke(Paint.valueOf(myResources.getString("DefaultSpawnColor")));
						setStrokeWidth(gameDisplay.getTileSize()*0.1);
						createSpawnNameWindow();
					}
					else if (toolBar.getSinkStatus()) {
						try {
							TerrainCell.this.getStroke().equals(myResources.getString("DefaultSpawnColor"));
							controller.removeSpawnPoints(cellName);
						} catch (Exception e) {
							
						}
						ArrayList<Point> points = new ArrayList<Point>();
						points.add(new Point(colLocation, rowLocation));
//						controller.addSinkPoints(cellName, points);
						setWidth(gameDisplay.getTileSize()*0.9);
						setHeight(gameDisplay.getTileSize()*0.9);
						setStroke(Paint.valueOf(myResources.getString("DefaultSinkColor")));
						setStrokeWidth(gameDisplay.getTileSize()*0.1);
						createSinkNameWindow();
					}
				}
				else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
					if (getStroke().equals(Color.valueOf(myResources.getString("DefaultSpawnColor")))) {
						controller.removeSpawnPoints(cellName);
						setStrokeWidth(0);
						setStroke(Paint.valueOf("White"));
						setWidth(gameDisplay.getTileSize());
						setHeight(gameDisplay.getTileSize());
					}
					else if (getStroke().equals(Color.valueOf(myResources.getString("DefaultSinkColor")))) {
						controller.removeSinkPoint(cellName);
						setStrokeWidth(0);
						setStroke(Paint.valueOf("White"));
						setWidth(gameDisplay.getTileSize());
						setHeight(gameDisplay.getTileSize());
					}
				}
			}
		});
}
	
	private void createSinkNameWindow() {
		Stage sinkStage = new Stage();
		VBox sinkBox = new VBox(screenHeight*0.01);
		sinkBox.setId("vbox");
		TextField setPointName = new TextField();
		setPointName.setId("menu-textfield");
		Button confirmName = new Button(myResources.getString("ApplyChanges"));
		confirmName.setId("button");
		sinkNameHandler(sinkStage, setPointName, confirmName);
		sinkBox.getChildren().addAll(setPointName, confirmName);
		Scene sinkNameScene = new Scene(sinkBox);
		sinkNameScene.getStylesheets().add("style.css");
		sinkStage.setTitle(myResources.getString("SetSpawnName"));
		sinkStage.setScene(sinkNameScene);
		sinkStage.show();
		sinkStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	if (setPointName.getText().isEmpty()) {
			        event.consume();
				}
		    	else {	
					sinkStage.close();
		    	}
		    }
		});
	}
	
	private void sinkNameHandler(Stage s, TextField text, Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String name = text.getText();
				TerrainCell.this.setName(name);
				ArrayList<Point> list = new ArrayList<Point>();
				list.add(new Point((double) colLocation, (double) rowLocation));
				controller.addSinkPoints(name, list);
				s.close();
			}
		});
	}
	
	private void createSpawnNameWindow() {
		Stage spawnStage = new Stage();
		VBox spawnBox = new VBox(screenHeight*0.01);
		spawnBox.setId("vbox");
		TextField setPointName = new TextField();
		setPointName.setId("menu-textfield");
		Button confirmName = new Button(myResources.getString("ApplyChanges"));
		confirmName.setId("button");
		spawnNameHandler(spawnStage, setPointName, confirmName);
		spawnBox.getChildren().addAll(setPointName, confirmName);
		Scene spawnNameScene = new Scene(spawnBox);
		spawnNameScene.getStylesheets().add("style.css");
		spawnStage.setTitle(myResources.getString("SetSpawnName"));
		spawnStage.setScene(spawnNameScene);
		spawnStage.show();
		spawnStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	if (setPointName.getText().isEmpty()) {
			        event.consume();
				}
		    	else {	
					spawnStage.close();
		    	}
		    }
		});
	}
	
	private void spawnNameHandler(Stage s, TextField text, Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String name = text.getText();
				TerrainCell.this.setName(name);
				ArrayList<Point> list = new ArrayList<Point>();
				list.add(new Point((double) colLocation, (double) rowLocation));
				controller.addSpawnPoints(name, list);
				s.close();
			}
		});
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	public String getName() {
		return cellName;
	}
	
	public String getType() {
		return terrainType;
	}
	
	public int getRow() {
		return rowLocation;
	}
	
	public int getColumn() {
		return colLocation;
	}
	
	public void setType(String newType, String color) {
		terrainType = newType;
		try { 
			if (toolBar.getImageStatus()) {
				String[] splitPath = toolBar.getSelectedImagePath().toString().split("src/");
				relPath = "";
				if (splitPath.length > 1)
					relPath += splitPath[1]+"/";
				System.out.println("THISISTHERELATIVEPATH: " + relPath);
				controller.addValidTerrain(terrainType, relPath);
			}
			else {
				controller.addValidTerrain(terrainType, relPath);
			}
		} catch (Exception e) {
			ErrorBox.displayError(myResources.getString("TerrainError"));
		}
	}
	
	public void setName(String newName) {
		cellName = newName;
	}
	
}
