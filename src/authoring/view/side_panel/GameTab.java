package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Deprecated
public class GameTab extends Tab {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Tab gameTab;
	private int screenWidth;
	private int screenHeight;
	
	public GameTab(TabPane pane) {
		screenInfo();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.gameTab = new Tab(myResources.getString("Rules"));
		gameTabOptions(gameTab);
		pane.getTabs().add(gameTab);
	}
	
	private void gameTabOptions(Tab gameTab) {
		VBox ruleArea = new VBox(screenHeight*0.01);
		ruleArea.setMaxHeight(screenHeight*0.88);
		ScrollPane availableRules = new ScrollPane();
		availableRules.setPrefSize(screenWidth/5, screenHeight);
		HBox ruleButtons = new HBox(screenWidth*0.05);
		ruleButtons.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
		Button editRules = new Button(myResources.getString("EditRules"));
		ruleButtons.getChildren().addAll(editRules);
		ruleArea.getChildren().addAll(availableRules, ruleButtons);
		gameTab.setContent(ruleArea);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}

}
