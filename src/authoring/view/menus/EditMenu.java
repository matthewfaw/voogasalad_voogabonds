package authoring.view.menus;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Christopher Lu
 * Creates the Edit submenu and implements all its buttons including: undo, revert to last save.
 */
@Deprecated
public class EditMenu extends Menu {

	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Menu edit;
	
	public EditMenu(MenuBar bar) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.edit = new Menu(myResources.getString("EditMenuLabel"));
		editOptions(edit);
		bar.getMenus().add(edit);
	}
	
	private void editOptions(Menu edit) {
		MenuItem undo = new MenuItem(myResources.getString("UndoEditLabel"));
		performUndo(undo);
		MenuItem revert = new MenuItem(myResources.getString("RevertLabel"));
		performRevert(revert);
		edit.getItems().addAll(undo, revert);
	}
	
	private void performUndo(MenuItem undo) {
		undo.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that undoes previous edit upon clicking undo button.
			}
		});
	}
	
	private void performRevert(MenuItem revert) {
		revert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO: Implement code that reverts to the last save upon clicking undo button.
			}
		});
	}
	
}
