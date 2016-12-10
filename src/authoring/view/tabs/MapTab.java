package authoring.view.tabs;

import java.util.ResourceBundle;

import authoring.controller.MapDataContainer;
import authoring.view.display.GameDisplay;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MapTab extends Tab {

	private TabPane tbPane;
	private Tab mapTab;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private GameDisplay mapDisplay;
	private BorderPane pane;
	private Scene scene;
	private MapDataContainer controller;
	private String mapDataFilePath;
	
	public MapTab(TabPane tPain, Scene sc, MapDataContainer con, int mapX, int mapY, String filePath) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.tbPane = tPain;
		this.mapTab = new Tab(myResources.getString("MapTab"));
		this.pane = new BorderPane();
		this.scene = sc;
		this.controller = con;
		this.mapDataFilePath = filePath;
		this.mapDisplay = new GameDisplay(pane, scene, con, mapX, mapY, mapDataFilePath);
		mapTab.setContent(mapDisplay.getTerrainBox());
		tbPane.getTabs().add(mapTab);
	}
	
}
