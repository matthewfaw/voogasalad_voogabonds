package authoring.view.display;

import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * @author Christopher Lu
 * Creates the individual Terrain unit for authoring front end. This unit's color and terrain type can be changed upon click.
 */

public class TerrainCell extends Rectangle {

	private String terrainType;
	private String terrainColor;
	private Point2D terrainCoords;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public TerrainCell() {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.terrainType = myResources.getString("DefaultTerrainType");
		this.terrainColor = myResources.getString("DefaultTerrainColor");
	}

	public String getType() {
		return terrainType;
	}

	public String getColor() {
		return terrainColor;
	}
	
	public String setType(String newType) {
		terrainType = newType;
		return terrainType;
	}
	
	public String setColor(String newColor) {
		terrainColor = newColor;
		return terrainColor;
	}
	
	public Point2D setPosition(int x, int y) {
		terrainCoords = new Point2D((double) x, (double) y);
		return terrainCoords;
	}

}
