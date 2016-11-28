package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import authoring.controller.Router;
import authoring.view.display.GameDisplay;

/**
 * @author Christopher Lu
 * This class initiates the pane that contains the tab that allows user to edit towers, enemies, terrain, and game mechanics.
 */

public class InfoTabs {

	private TabPane infoTab;
	private int screenWidth;
	private int screenHeight;
	private Scene scene;
	
	public InfoTabs(BorderPane root, Scene scene) {
		screenInfo();
		this.scene = scene;
		infoTab = new TabPane();
		infoTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Router r = new Router();
		GameDisplay mapDisplay = new GameDisplay(root, scene, r.getMapDataController());
		//Change to take in a controller as a second parameter please:
		Tab towerTab = new TowerTab(infoTab, r.getTowerDataController());
		Tab enemyTab = new EnemyTab(infoTab, r.getEnemyDataController());
		Tab waveTab = new WaveLevelTab(infoTab, r.getWaveDataController());
		Tab gameTab = new GameTab(infoTab);
		Tab weaponTab = new WeaponTab(infoTab, r.getWeaponDataController());
		r.link((TowerTab) towerTab, (EnemyTab) enemyTab, (WaveLevelTab) waveTab, (GameTab) gameTab);
		infoTab.setPrefSize(screenWidth/5, screenHeight);
		root.setLeft(infoTab);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
