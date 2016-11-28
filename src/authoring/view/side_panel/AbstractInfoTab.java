package authoring.view.side_panel;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * @author Niklas Sjoquist
 * 
 * This Abstract Class defines a Tab of InfoTabs in order to more easily create new types of Tabs. 
 *
 */
public abstract class AbstractInfoTab extends Tab implements ITerrainTab {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    
    private ResourceBundle myResources;
    private Tab myTab;
    private int screenWidth, screenHeight;
    private VBox myContent;
    
    //protected AbstractInfoTab()
    
    @Override
    public abstract ObservableList<String> getTerrains ();

}
