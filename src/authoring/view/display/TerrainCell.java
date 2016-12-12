package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
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
						else {
							if (TerrainCell.this.getHeight() != gameDisplay.getTileSize() || TerrainCell.this.getWidth() != gameDisplay.getTileSize()) {
								if (TerrainCell.this.getFill().equals(Color.RED)) {
									controller.removeSpawnPoints(cellName);
								}
								else {
									controller.removeSinkPoint(cellName);
								}
							}
							if (toolBar.getImageStatus()) {
								Image image = new Image(toolBar.getSelectedImagePath());
								ImagePattern imagePattern = new ImagePattern(image);
								setFill(imagePattern);
								controller.addTerrainData(new TerrainData(TerrainCell.this.getName(), colLocation, rowLocation, (int) TerrainCell.this.getHeight(), toolBar.getSelectedImagePath()));
								System.out.println("Image Path is: " + toolBar.getSelectedImagePath());
							}
							else {
								controller.addTerrainData(new TerrainData(TerrainCell.this.getName(), colLocation, rowLocation, (int) TerrainCell.this.getHeight(), TerrainCell.this.getFill().toString()));
								setFill(toolBar.getSelectedColor());
							}
							setType(toolBar.getSelectedTerrain(), toolBar.getSelectedColor().toString());
							setWidth(gameDisplay.getTileSize());
							setHeight(gameDisplay.getTileSize());
							
						}
					}
					else if (toolBar.getSpawnStatus()) {
						if (TerrainCell.this.getHeight() != gameDisplay.getTileSize() || TerrainCell.this.getWidth() != gameDisplay.getTileSize()) {
							if (TerrainCell.this.getFill().equals(Color.GREEN)) {
								controller.removeSinkPoint(cellName);
							}
						}
						ArrayList<Point> points = new ArrayList<Point>();
						points.add(new Point(TerrainCell.this.getX(), TerrainCell.this.getY()));
						setWidth(gameDisplay.getTileSize()/2);
						setHeight(gameDisplay.getTileSize()/2);
						setFill(Paint.valueOf(myResources.getString("DefaultSpawnColor")));
						createSpawnNameWindow();
					}
					else if (toolBar.getSinkStatus()) {
						if (TerrainCell.this.getHeight() != DEFAULT_TILE_SIZE || TerrainCell.this.getWidth() != DEFAULT_TILE_SIZE) {
							if (TerrainCell.this.getFill().equals(Color.RED)) {
								controller.removeSpawnPoints(cellName);
							}
						}
						ArrayList<Point> points = new ArrayList<Point>();
						points.add(new Point(TerrainCell.this.getX(), TerrainCell.this.getY()));
						setWidth(gameDisplay.getTileSize()/2);
						setHeight(gameDisplay.getTileSize()/2);
						setFill(Paint.valueOf(myResources.getString("DefaultSinkColor")));
						createSinkNameWindow();
					}
				}
			}
		});
	}
	
	private void createSinkNameWindow() {
		Stage sinkStage = new Stage();
		VBox sinkBox = new VBox(screenHeight*0.01);
		TextField setPointName = new TextField();
		Button confirmName = new Button(myResources.getString("ApplyChanges"));
		sinkNameHandler(sinkStage, setPointName, confirmName);
		sinkBox.getChildren().addAll(setPointName, confirmName);
		Scene sinkNameScene = new Scene(sinkBox, screenWidth*0.2, screenHeight*0.08);
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
		TextField setPointName = new TextField();
		Button confirmName = new Button(myResources.getString("ApplyChanges"));
		spawnNameHandler(spawnStage, setPointName, confirmName);
		spawnBox.getChildren().addAll(setPointName, confirmName);
		Scene spawnNameScene = new Scene(spawnBox, screenWidth*0.2, screenHeight*0.08);
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
				controller.addValidTerrain(terrainType, toolBar.getSelectedImagePath().toString());
			}
			else {
				controller.addValidTerrain(terrainType, toolBar.getSelectedColor().toString());
			}
		} catch (Exception e) {
			ErrorBox.displayError(myResources.getString("TerrainError"));
		}
	}
	
	public void setName(String newName) {
		cellName = newName;
	}
	
}
