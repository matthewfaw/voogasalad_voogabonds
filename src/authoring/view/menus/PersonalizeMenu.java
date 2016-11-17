package authoring.view.menus;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Christopher Lu
 * Creates the personalization menu that allows the user to customize their workspace.
 */

public class PersonalizeMenu extends Menu {
	
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Menu personalize;
	
	public PersonalizeMenu(MenuBar bar) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.personalize = new Menu(myResources.getString("PersonalizeMenuLabel"));
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
				//TODO: Implement code that changes color of workspace upon clicking Color button. Use color chooser.
			}
		});
	}
	
	private void performShowGrid(MenuItem showGrid) {
		showGrid.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that shows grid on the map upon clicking Show Grid button.
			}
		});
	}
	
	private void performHideGrid(MenuItem hideGrid) {
		hideGrid.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that hides grid on the map upon clicking Hide Grid button.
			}
		});
	}
	
}
