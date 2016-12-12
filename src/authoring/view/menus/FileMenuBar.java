package authoring.view.menus;

import java.util.Arrays;
import java.util.List;

import authoring.controller.Router;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

/**
 * @author Niklas Sjoquist
 * @author Christopher Lu
 * 
 * Sets up the top menu bar that will allow the user to perform multiple actions, including accessing a help menu, oepning and closing workspaces, playing the game,
 * deleting towers, adding enemies, and personalize the workspace among multiple other things.
 */
@Deprecated
public class FileMenuBar extends MenuBar {
    private List<Menu> myMenus;
    private Router router;
    
    public FileMenuBar (BorderPane root, Router r) {
        myMenus = getMenus(root);
        this.router = r;
        this.setId("hbox");
        for (Menu menu: this.getMenus()){
        	menu.setId("menu");
        }
        this.getMenus().addAll(myMenus);
    }

    public List<Menu> getMenus(BorderPane root) {
        FileMenu fileMenu = new FileMenu(this, router);
        EditMenu editMenu = new EditMenu(this);
        PersonalizeMenu personalizeMenu = new PersonalizeMenu(this, root);
        PlayMenu playMenu = new PlayMenu(this);
        return Arrays.asList(fileMenu, editMenu, personalizeMenu, playMenu);
    }

}
