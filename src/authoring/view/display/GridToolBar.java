package authoring.view.display;

import java.util.ResourceBundle;

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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	private ObservableList<String> terrainOptions = 
			FXCollections.observableArrayList (
					"Ground",
					"Water",
					"Ice",
					"Acid"
					);
	
	public GridToolBar(VBox box, Scene sc) {
		this.scene = sc;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.toolBar = new HBox();
		selectedColor = Color.WHITE;
		createToolBar();
		box.getChildren().add(toolBar);
		toolBar.setAlignment(Pos.BOTTOM_CENTER);
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
		ColorPicker colorChooser = new ColorPicker();
		colorHandler(colorChooser);
		ComboBox<String> terrainChooser = new ComboBox<String>(terrainOptions);
		terrainHandler(terrainChooser);
		toolBar.getChildren().addAll(setSinkPoint, setSpawnPoint, drawMode, colorChooser, terrainChooser);
	}
	
	/**
	 * Sets toggleStatus to true if the draw mode toggle button is selected, or false if not.
	 * @param drawMode
	 */
	private void toggleHandler(ToggleGroup drawGroup, ToggleButton drawMode)  {
		drawGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {		 
		    	mouseCursor = new Image(getClass().getClassLoader().getResourceAsStream("resources/MousePenIcon.png"));   
		    	if (new_toggle == null) {
		            toggleStatus = false;
		        }
		        else {
		        	if (drawGroup.getSelectedToggle().equals(drawMode)) {
			            toggleStatus = true;
			            spawnStatus = false;
			            sinkStatus = false;
		        	}
		        }
		    	if (!toggleStatus) {
		    		scene.setCursor(Cursor.DEFAULT);
		    	}
		    	else {
			        scene.setCursor(new ImageCursor(mouseCursor)); 
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
	 * Sets selectedColor to the color chosen by the user when using the Color Picker from the toolbar.
	 * @param colors
	 */
	private void colorHandler(ColorPicker colors) {
		colors.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				selectedColor = colors.getValue();
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
				selectedTerrain = terrains.getSelectionModel().getSelectedItem();

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
	
}
