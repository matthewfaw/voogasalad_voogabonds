package authoring.view.menus;

import java.util.Arrays;
import java.util.List;
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
public class FileMenuBar extends MenuBar {
    private List<Menu> myMenus;
    
    public FileMenuBar (BorderPane root) {
        myMenus = getMenus(root);
        this.getMenus().addAll(myMenus);
    }

    public List<Menu> getMenus(BorderPane root) {
        FileMenu fileMenu = new FileMenu(this);
        EditMenu editMenu = new EditMenu(this);
        PersonalizeMenu personalizeMenu = new PersonalizeMenu(this, root);
        PlayMenu playMenu = new PlayMenu(this);
        return Arrays.asList(fileMenu, editMenu, personalizeMenu, playMenu);
    }

}
