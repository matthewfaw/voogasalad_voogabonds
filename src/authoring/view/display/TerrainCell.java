package authoring.view.display;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * @author Christopher Lu
 * Creates the individual Terrain unit for authoring front end. This unit's color and terrain type can be changed upon click.
 */

public class TerrainCell extends Rectangle {

	private String terrainType;
	private GridToolBar toolBar;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private int rowLocation;
	private int colLocation;
	
	public TerrainCell(GridToolBar tools, int row, int column) {	
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
				if (toolBar.getToggleStatus()) {
					setFill(toolBar.getSelectedColor());
					setType(toolBar.getSelectedTerrain());

				}
			}
		});
		this.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (toolBar.getToggleStatus()) {
					setFill(toolBar.getSelectedColor());
					setType(toolBar.getSelectedTerrain());
					setWidth(50);
					setHeight(50);
				}
			}
		});
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
	
}
