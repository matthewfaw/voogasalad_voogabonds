package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
	
	public TerrainCell(GridToolBar tools, int row, int column) {	
		setUpScreenResolution();
		this.toolBar = tools;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.terrainType = myResources.getString("DefaultTerrainType");
		this.rowLocation = row;
		this.colLocation = column;
		clickEvent();
	}
	
	private void clickEvent() {
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					System.out.println(cellName);
					if (toolBar.getToggleStatus()) {
						setFill(toolBar.getSelectedColor());
						setType(toolBar.getSelectedTerrain());
	
					}
					if (toolBar.getSpawnStatus()) {
						setWidth(TerrainCell.this.getWidth()/2);
						setHeight(TerrainCell.this.getHeight()/2);
						setFill(Paint.valueOf("Red"));
					}
				}
				else {
					createSpawnNameWindow();
					}
				}
		});
		this.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (toolBar.getToggleStatus()) {
					setFill(toolBar.getSelectedColor());
					setType(toolBar.getSelectedTerrain());
				}
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
	}
		
	private void spawnNameHandler(Stage s, TextField text, Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String name = text.getText();
				TerrainCell.this.setName(name);
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
	
	public void setType(String newType) {
		terrainType = newType;
	}
	
	public void setName(String newName) {
		cellName = newName;
	}
	
}
