package authoring.view.display;

import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Christopher Lu
 * Creates the individual Terrain unit for authoring front end. This unit's color and terrain type can be changed upon click.
 */

public class TerrainCell extends Rectangle {

	private String terrainType;
	private Color terrainColor;
	private GridToolBar toolBar;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public TerrainCell(GridToolBar tools) {
		this.toolBar = tools;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.terrainType = myResources.getString("DefaultTerrainType");
		this.terrainColor = Color.BLACK;
		System.out.println(toolBar.getToggleStatus());
		if (!toolBar.getToggleStatus()) {
			this.setOnMouseClicked(new EventHandler<MouseEvent>()
		       {
		           @Override
		           public void handle(MouseEvent t) {
		               terrainColor = toolBar.getSelectedColor();
		               terrainType = toolBar.getSelectedTerrain();
		               System.out.println("Color is now " + terrainColor);
		               System.out.println("Terrain is now " + terrainType);
		           }
		       });
			}
//		cellHandler();
	}

//	private void cellHandler() {
//		if (toolBar.getToggleStatus()) {
//		this.setOnMouseClicked(new EventHandler<MouseEvent>()
//	       {
//	           @Override
//	           public void handle(MouseEvent t) {
//	               terrainColor = toolBar.getSelectedColor();
//	               terrainType = toolBar.getSelectedTerrain();
//	               System.out.println("Color is now " + terrainColor);
//	               System.out.println("Terrain is now " + terrainType);
//	           }
//	       });
//		}
//
//	}
	
	public String getType() {
		return terrainType;
	}

	public Color getColor() {
		return terrainColor;
	}
	
	public void setType(String newType) {
		terrainType = newType;
	}
	
	public void setColor(Color newColor) {
		terrainColor = newColor;
	}

}
