package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
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
	private Color selectedColor;
	private String selectedTerrain;
	private Image mouseCursor;
	private int screenHeight;
	private int screenWidth;
	private HashMap<String, Color> colorToTerrain;
	private ObservableList<String> terrainOptions = 
			FXCollections.observableArrayList (
					"Add Terrain..."
					);
	private MapDataContainer controller;
	
	public GridToolBar(VBox box, Scene sc, MapDataContainer controller) {
		setUpScreenResolution();
		this.scene = sc;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.toolBar = new HBox();
		this.colorToTerrain = new HashMap<String, Color>();
		selectedColor = Color.WHITE;
		this.selectedTerrain = myResources.getString("DNE");
		this.controller = controller;
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
		ToggleButton drawMode = new ToggleButton(myResources.getString("DrawMode"));
		drawMode.setToggleGroup(toggles);
		toggleHandler(toggles, drawMode);
		ToggleButton setSpawnPoint = new ToggleButton(myResources.getString("SpawnPoint"));
		setSpawnPoint.setToggleGroup(toggles);
		spawnHandler(toggles, setSpawnPoint);
		ToggleButton setSinkPoint = new ToggleButton(myResources.getString("SinkPoint"));
		setSinkPoint.setToggleGroup(toggles);
		sinkHandler(toggles, setSinkPoint);
		ComboBox<String> terrainChooser = new ComboBox<String>(terrainOptions);
		terrainHandler(terrainChooser);
		toolBar.getChildren().addAll(setSinkPoint, setSpawnPoint, drawMode, terrainChooser);
	}
	
	/**
	 * Sets toggleStatus to true if the draw mode toggle button is selected, or false if not.
	 * @param drawMode
	 */
	private void toggleHandler(ToggleGroup drawGroup, ToggleButton drawMode)  {
		drawGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		            toggleStatus = false;
		        }
		        else {
		        	if (drawGroup.getSelectedToggle().equals(drawMode)) {
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
	
	private void spawnHandler(ToggleGroup spawnGroup, ToggleButton spawnMode) {
		spawnGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		    		spawnStatus = false;
		    		scene.setCursor(Cursor.DEFAULT);
		        }
		        else {
		        	if (spawnGroup.getSelectedToggle().equals(spawnMode)) {
			        	toggleStatus = false;
			        	spawnStatus = true;
			        	sinkStatus = false;
			        	scene.setCursor(Cursor.CROSSHAIR); 
		        	}
		        }
		    }
		});
	}
	
	private void sinkHandler(ToggleGroup sinksGroup, ToggleButton sinkMode) {
		sinksGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	if (new_toggle == null) {
		    		sinkStatus = false;
		    		scene.setCursor(Cursor.DEFAULT);
		        }
		        else {
		        	if (sinksGroup.getSelectedToggle().equals(sinkMode)) {
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
					HBox choiceArea = new HBox(screenWidth*0.01);
					ColorPicker colorChooser = new ColorPicker();
					TextField terrainName = new TextField();
					terrainName.setText(myResources.getString("TerrainName"));
					Button chooseImage = new Button(myResources.getString("ChooseTerrainImage"));
					confirmImageHandler(chooseImage, createTerrain);
					Button confirmTerrain = new Button(myResources.getString("ApplyChanges"));
					choiceArea.getChildren().addAll(colorChooser, chooseImage, terrainName, confirmTerrain);
					confirmTerrainHandler(createTerrain, terrainName, confirmTerrain, colorChooser);
					Scene terrainChoiceScene = new Scene(choiceArea, screenWidth*0.3, screenHeight*0.1);
					createTerrain.setScene(terrainChoiceScene);
					createTerrain.show();
				}
				else {
					selectedTerrain = terrains.getSelectionModel().getSelectedItem();
					selectedColor = colorToTerrain.get(terrains.getSelectionModel().getSelectedItem());
				}
//				terrains.getSelectionModel().clearSelection();
			}
		});
	}
	
	private void confirmImageHandler(Button chooseImage, Stage imageStage) {
		chooseImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ImageGallery terrainImages = new ImageGallery(imageStage, myResources.getString("TerrainImageFilePath"));
			}
		});
	}
	
	private void confirmTerrainHandler(Stage createTerrain, TextField field, Button b, ColorPicker colors) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent event) {
				terrainOptions.add(field.getText());
				colorToTerrain.put(field.getText(), colors.getValue());
				try {
					controller.addValidTerrain(field.getText(), colors.getValue().toString());
				} catch (Exception e) {
					ErrorBox.displayError(myResources.getString("TerrainError"));
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
		System.out.println(selectedColor);
		return selectedColor;
	}
	
	public String getSelectedTerrain() {
		return selectedTerrain;
	}
	
}
