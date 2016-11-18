package authoring.view.menus;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.MainInitalizer;
;
/**
 * @author Christopher Lu
 * Sets up the top menu bar that will allow the user to perform multiple actions, including accessing a help menu, oepning and closing workspaces, playing the game,
 * deleting towers, adding enemies, and personalize the workspace among multiple other things.
 */

public class TopMenuBar {
	
	private MenuBar topMenu;
	
	public TopMenuBar(BorderPane root) {
		topMenu = new MenuBar();
		Menu fileMenu = new FileMenu(topMenu);
		Menu editMenu = new EditMenu(topMenu);
		Menu personalizeMenu = new PersonalizeMenu(topMenu);
		Menu playMenu = new PlayMenu(topMenu);
		topMenu.getMenus().addAll(fileMenu, editMenu, personalizeMenu, playMenu);
		root.setTop(topMenu);
	}
}
