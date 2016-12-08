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
import authoring.view.tabs.WaveTab;

/**
 * @author Christopher Lu
 * This class initiates the pane that contains the tab that allows user to edit towers, enemies, terrain, and game mechanics.
 */

public class InfoTabs {

	private TabPane infoTab;
	private int screenWidth;
	private int screenHeight;
	private Scene scene;
	private static Router r;
	
	public InfoTabs(BorderPane root, Scene scene) {
		screenInfo();
		this.scene = scene;
		infoTab = new TabPane();
		infoTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		r = new Router();
		GameDisplay mapDisplay = new GameDisplay(root, scene, r.getMapDataController());
		//Change to take in a controller as a second parameter please:
		Tab towerTab = new TowerTab(infoTab, r.getTowerDataController());
		Tab enemyTab = new EnemyTab(infoTab, r.getEnemyDataController());
		Tab waveTab = new WaveTab(infoTab, r.getWaveDataController());
		//Tab gameTab = new GameTab(infoTab);
		Tab weaponTab = new WeaponTab(infoTab, r.getWeaponDataController());
		// TODO: change null to GameTab
		r.link((TowerTab) towerTab, (EnemyTab) enemyTab, (WaveTab) waveTab, null); 
		infoTab.setPrefSize(screenWidth/5, screenHeight);
		root.setLeft(infoTab);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	// Tripp: I added this method and made Router a private instance variable. If this causes issues or is a major design flaw,
	// we can change this, but this is just to get a basic implementation up and running.
	// I made router static, but this is not the best case scenario and will change in Sprint 2.
	public static Router getRouter(){
		return r;
	}
	
}
