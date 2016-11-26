package authoring.view.menus;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * Creates the personalization menu that allows the user to customize their workspace.
 */

public class PersonalizeMenu extends Menu {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Menu personalize;
	private BorderPane myRoot;
	private boolean isShowing = true;
	private TilePane myTilePane;
	
	public PersonalizeMenu(MenuBar bar, BorderPane root) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.personalize = new Menu(myResources.getString("PersonalizeMenuLabel"));
		this.myRoot = root;
		personalizeOptions(personalize);
		bar.getMenus().add(personalize);
	}
	
	private void personalizeOptions(Menu personalize) {
		MenuItem workspaceColor = new MenuItem(myResources.getString("WorkspaceColorLabel"));
		performChangeColor(workspaceColor);
		MenuItem showGrid = new MenuItem(myResources.getString("ShowGridLabel"));
		performShowGrid(showGrid);
		MenuItem hideGrid = new MenuItem(myResources.getString("HideGridLabel"));
		performHideGrid(hideGrid);
		personalize.getItems().addAll(workspaceColor, showGrid, hideGrid);
	}
	
	private void performChangeColor(MenuItem workspaceColor) {
		workspaceColor.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Stage stage = new Stage();
				VBox root = new VBox();
				Scene scene = new Scene(root, 200, 200);
				ColorPicker cp = new ColorPicker(Color.WHITE);
				Button button = new Button(myResources.getString("Finish"));
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event){
						myRoot.setBackground(new Background(new BackgroundFill(cp.getValue(), 
								CornerRadii.EMPTY, Insets.EMPTY)));
						stage.close();
					}
				});
				root.getChildren().addAll(cp, button);
				stage.setScene(scene);
				stage.setTitle(myResources.getString("PickColor"));
				stage.show();
			}
		});
	}
	
	private void performShowGrid(MenuItem showGrid) {
		showGrid.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (!isShowing){
					((ScrollPane) ((VBox) myRoot.getCenter()).getChildren().get(1)).setContent(myTilePane);
					isShowing = true;
				}
			}
		});
	}
	
	private void performHideGrid(MenuItem hideGrid) {
		hideGrid.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (isShowing){
					myTilePane = (TilePane) ((ScrollPane) ((VBox) myRoot.getCenter()).getChildren().get(1)).getContent();
					((ScrollPane) ((VBox) myRoot.getCenter()).getChildren().get(1)).setContent(null);
					isShowing = false;
				}
			}
		});
	}
	
}
