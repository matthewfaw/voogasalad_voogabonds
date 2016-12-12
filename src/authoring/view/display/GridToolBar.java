package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import authoring.controller.MapDataContainer;
import authoring.model.map.TerrainData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ErrorBox;

/**
 * @author Christopher Lu
 * Creates the toolbar for the grid, allowing the user to select the draw tool and terrain type and fill in terrain types upon clicking individual cells.
 */

public class GridToolBar {

	private Scene scene;
	private HBox toolBar;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private boolean toggleStatus;
	private boolean spawnStatus;
	private boolean sinkStatus;
	private boolean imageStatus = false;
	private Color selectedColor;
	private String selectedTerrain;
	private String selectedImagePath;
	private Image mouseCursor;
	private int screenHeight;
	private int screenWidth;
	private HashMap<String, Color> colorToTerrain;
	private HashMap<String, String> imageToTerrain;
	private HashMap<String, Boolean> boolToTerrain;
	private ObservableList<String> terrainOptions = 
			FXCollections.observableArrayList (
					"Add Terrain..."
					);
	private MapDataContainer controller;
	private ToggleButton myDraw;
	private ToggleButton mySpawn;
	private ToggleButton mySink;
	
	public GridToolBar(VBox box, Scene sc, MapDataContainer controller) {
		setUpScreenResolution();
		this.scene = sc;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.toolBar = new HBox();
		toolBar.setId("hbox");
		this.colorToTerrain = new HashMap<String, Color>();
		this.imageToTerrain = new HashMap<String, String>();
		this.boolToTerrain = new HashMap<String, Boolean>();
		selectedColor = Color.WHITE;
		this.selectedTerrain = myResources.getString("DNE");
		this.controller = controller;
		importTerrains();
		createToolBar();
		box.getChildren().add(toolBar);
		toolBar.setAlignment(Pos.BOTTOM_CENTER);
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void createToolBar() {
		ToggleGroup toggles = new ToggleGroup();
		myDraw = new ToggleButton(myResources.getString("DrawMode"));
		myDraw.setToggleGroup(toggles);
		myDraw.setId("button");
		toggleHandler(toggles);
		mySpawn = new ToggleButton(myResources.getString("SpawnPoint"));
		mySpawn.setToggleGroup(toggles);
		mySpawn.setId("button");
		spawnHandler(toggles);
		mySink = new ToggleButton(myResources.getString("SinkPoint"));
		mySink.setToggleGroup(toggles);
		mySink.setId("button");
		sinkHandler(toggles);
		ComboBox<String> terrainChooser = new ComboBox<String>(terrainOptions);
		terrainChooser.setId("menu-combobox");
		terrainChooser.setMinHeight(screenHeight*0.04);
		terrainHandler(terrainChooser);
		toolBar.getChildren().addAll(mySink, mySpawn, myDraw, terrainChooser);
	}
	
	private void importTerrains() {
		HashMap<String, String> terrainList = controller.getValidTerrainMap();
		for (String terrainName : terrainList.keySet()) {
			try {
				boolToTerrain.put(terrainName, false);
				colorToTerrain.put(terrainName, Color.valueOf(terrainList.get(terrainName)));
			} catch (Exception e) {
				boolToTerrain.put(terrainName, true);
				imageToTerrain.put(terrainName, terrainList.get(terrainName));
			}
			terrainOptions.add(terrainName);
		}
	}
	
	/**
	 * Sets toggleStatus to true if the draw mode toggle button is selected, or false if not.
	 * @param drawMode
	 */
	private void toggleHandler(ToggleGroup drawGroup)  {
		drawGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		            toggleStatus = false;
		            myDraw.setId("button");
		        }
		        else {
		        	if (drawGroup.getSelectedToggle().equals(myDraw)) {
		        		myDraw.setId("button-selected");
		        		mySpawn.setId("button");
		        		mySink.setId("button");
			            toggleStatus = true;
			            spawnStatus = false;
			            sinkStatus = false;
			    		mouseCursor = new Image(getClass().getClassLoader().getResourceAsStream("resources/MousePenIcon.png"));   
				        scene.setCursor(new ImageCursor(mouseCursor)); 
		        	}
		        }
		    	if (!toggleStatus) {
		    		scene.setCursor(Cursor.DEFAULT);
		    	}
		    }
		});
	}
	
	private void spawnHandler(ToggleGroup spawnGroup) {
		spawnGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		    		spawnStatus = false;
		    		scene.setCursor(Cursor.DEFAULT);
		    		mySpawn.setId("button");
		        }
		        else {
		        	if (spawnGroup.getSelectedToggle().equals(mySpawn)) {
		        		myDraw.setId("button");
		        		mySpawn.setId("button-selected");
		        		mySink.setId("button");
			        	toggleStatus = false;
			        	spawnStatus = true;
			        	sinkStatus = false;
			        	scene.setCursor(Cursor.CROSSHAIR); 
		        	}
		        }
		    }
		});
	}
	
	private void sinkHandler(ToggleGroup sinksGroup) {
		sinksGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		    		sinkStatus = false;
		    		scene.setCursor(Cursor.DEFAULT);
		    		mySink.setId("button");
		        }
		        else {
		        	if (sinksGroup.getSelectedToggle().equals(mySink)) {
		        		myDraw.setId("button");
		        		mySpawn.setId("button");
		        		mySink.setId("button-selected");
			        	toggleStatus = false;
			        	spawnStatus = false;
			        	sinkStatus = true;
			        	scene.setCursor(Cursor.CROSSHAIR); 
		        	}
		        }
		    }
		});
	}
	
	/**
	 * Sets selectedTerrain to the terrain chosen by the user when using the terrain combo box from the toolbar.
	 * @param terrains
	 */
	private void terrainHandler(ComboBox<String> terrains) {
		terrains.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String selectedItem = terrains.getSelectionModel().getSelectedItem();
				if (selectedItem.equals(myResources.getString("DefaultTerrainOption"))) {
					Stage createTerrain = new Stage();
					createTerrain.initModality(Modality.APPLICATION_MODAL);
					VBox choiceContainer = new VBox(screenHeight*0.02);
					HBox choiceArea = new HBox(screenWidth*0.01);
					HBox toggleArea = new HBox(screenWidth*0.05);
					ColorPicker colorChooser = new ColorPicker();
					TextField terrainName = new TextField();
					terrainName.setText(myResources.getString("TerrainName"));
					Button chooseImage = new Button(myResources.getString("ChooseTerrainImage"));
					confirmImageHandler(chooseImage);
					ToggleGroup toggles = new ToggleGroup();
					ToggleButton imageMode = new ToggleButton(myResources.getString("ImageMode"));
					imageMode.setToggleGroup(toggles);
					fillImageHandler(toggles, imageMode, terrainName);
					Button confirmTerrain = new Button(myResources.getString("ApplyChanges"));
					choiceArea.getChildren().addAll(colorChooser, chooseImage, terrainName, confirmTerrain);
					toggleArea.getChildren().addAll(imageMode);					
					choiceContainer.getChildren().addAll(choiceArea, toggleArea);
					confirmTerrainHandler(createTerrain, terrainName, confirmTerrain, colorChooser);
					Scene terrainChoiceScene = new Scene(choiceContainer, screenWidth*0.3, screenHeight*0.1);
					createTerrain.setScene(terrainChoiceScene);
					createTerrain.setWidth(screenWidth*0.5);
					createTerrain.show();
				}
				else {
					selectedTerrain = terrains.getSelectionModel().getSelectedItem();
					imageStatus = boolToTerrain.get(terrains.getSelectionModel().getSelectedItem());
					if (imageStatus) {
						selectedImagePath = imageToTerrain.get(terrains.getSelectionModel().getSelectedItem());
					}
					else {
						selectedColor = colorToTerrain.get(terrains.getSelectionModel().getSelectedItem());
					}
				}
				terrains.getSelectionModel().clearSelection();
			}
		});
	}
	
	private void fillImageHandler(ToggleGroup group, ToggleButton button, TextField field) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		    		imageStatus = false;
		        }
		        else {
		        	imageStatus = true;
		        }
		    }
		});
	}
	
	private void confirmImageHandler(Button chooseImage) {
		chooseImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ImageGallery terrainImages = new ImageGallery(GridToolBar.this, myResources.getString("TerrainImageFilePath"));
			}
		});
	}
	
	private void confirmTerrainHandler(Stage createTerrain, TextField field, Button b, ColorPicker colors) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent event) {
				terrainOptions.add(field.getText());
				boolToTerrain.put(field.getText(), imageStatus);
				if (imageStatus) {
					imageToTerrain.put(field.getText(), selectedImagePath);
					try {
						controller.addValidTerrain(field.getText(), selectedImagePath);
					} catch (Exception e) {
						ErrorBox.displayError(myResources.getString("TerrainError"));
					}
				}
				else {
					colorToTerrain.put(field.getText(), colors.getValue());
					try {
						controller.addValidTerrain(field.getText(), colors.getValue().toString());
					} catch (Exception e) {
						ErrorBox.displayError(myResources.getString("TerrainError"));
					}
				}
				createTerrain.close();
			}
		});
	}
	
	public boolean getSpawnStatus() {
		return spawnStatus;
	}
	
	public boolean getToggleStatus() {
		return toggleStatus;
	}
	
	public  boolean getSinkStatus() {
		return sinkStatus;
	}
		
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	public String getSelectedTerrain() {
		return selectedTerrain;
	}
	
	public String getSelectedImagePath() {
		return selectedImagePath;
	}
	
	public void setSelectedImagePath(String newPath) {
		selectedImagePath = newPath;
	}
	
	public boolean getImageStatus() {
		return imageStatus;
	}
	
}
