package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.IDataController;
import authoring.model.IReadableData;
import authoring.view.input_menus.AbstractMenu;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Niklas Sjoquist
 * 
 * This Abstract Class defines a general Tab of InfoTabs in order to more easily create new types of Tabs. 
 *
 */
public abstract class AbstractInfoTab extends Tab {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String VIEW_RESOURCES = "View";
    
    private ResourceBundle myResources;
    private Tab myTab;
    private int screenWidth, screenHeight;
    private VBox myContent;
    private AbstractMenu myMenu;
    private IDataController myController;
    private List<ObservableList<String>> myObservableLists;
    
    protected AbstractInfoTab(TabPane pane, IDataController controller) {
        this.retrieveScreenInfo();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + VIEW_RESOURCES);
        myTab = new Tab(this.getTitle());
        myController = controller;
        defineObservableLists();
        myMenu = initMenu(myResources, myController);
        initTab(pane, myTab, this.getTitle());
    }
    
    // TODO: fix magic numbers
    private void initTab(TabPane pane, Tab tab, String title) {
        // VBox to wrap contents of Tab
        VBox tabArea = new VBox(screenHeight*0.01);
        tabArea.setMaxHeight(screenHeight*0.88);
        
        // ScrollPane to wrap myContent (list of available objects)
        ScrollPane availableObjectsArea = new ScrollPane();
        myContent = new VBox();
        setContent(myContent);
        availableObjectsArea.setContent(myContent);
        availableObjectsArea.setPrefSize(screenWidth/5, screenHeight);
        
        // HBox to wrap buttons along bottom of tab
        HBox bottomBtnBar = new HBox(screenWidth*0.05);
        bottomBtnBar.setPadding(new Insets(0.01*screenHeight, screenWidth*0.01, 0.01*screenHeight, screenWidth*0.01));
        
        // Add new object button
        Button addObject = new Button("Add "+title);
        addObject.setOnAction(handleAddNewObject());
        
        // Add all nodes to the Tab and add Tab to TabPane
        bottomBtnBar.getChildren().addAll(addObject);
        tabArea.getChildren().addAll(availableObjectsArea, bottomBtnBar);
        tab.setContent(tabArea);
        pane.getTabs().add(tab);
    }
    
    private EventHandler<ActionEvent> handleAddNewObject() {
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                IReadableData defaultData = generateDefaultData();
                myMenu.createObjectMenu(defaultData);
            }
        };
        return handler;
    }
    
    private void retrieveScreenInfo() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }
    
    // Protected helper methods \\
    
    /**
     * @return
     */
    protected int getWidth() {
        return screenWidth;
    }
    
    /**
     * @return
     */
    protected int getHeight() {
        return screenHeight;
    }
    
    /**
     * @param menu
     */
    protected void setMenu(AbstractMenu menu) {
        myMenu = menu;
    }
    
    /**
     * @return
     */
    protected AbstractMenu getMenu() {
        return myMenu;
    }
    
    /**
     * @return
     */
    protected IDataController getController() {
        return myController;
    }
    
    /**
     * @return
     */
    protected ResourceBundle getResources() {
        return myResources;
    }
    
    /**
     * @return
     */
    protected List<ObservableList<String>> getObservableLists() {
        return myObservableLists;
    }
    
    // Abstract methods \\
    
    /**
     * This method should specify how to add a newly created object to the Tab
     *
     * @param name - Name of the created object
     * @param data - Data object representing the created object (to be passed to controller)
     */
    public abstract void addObject(String name, IReadableData data);
    
    /**
     * This method should specify how to update an existing object and update the view to show the changes
     * 
     * @param oldName - Name of the object before it was changed (could be different than the original name)
     * @param data - Data object representing object
     */
    public abstract void updateObject(String oldName, IReadableData data);
    
    /**
     * @return the title of the tab
     */
    protected abstract String getTitle();

    /**
     * This method is used to set the contents of the ScrollPane region in the Tab.
     * 
     * @param content - reference to the contents of the ScrollPane region
     */
    protected abstract void setContent(VBox content);
    
    /**
     * This method assumes the controller and resource bundle have already been initialized.
     * 
     * @return the specific type of Menu contained in this Tab
     */
    protected abstract AbstractMenu initMenu(ResourceBundle resources, IDataController controller);
    
    /**
     * This method should specify the different lists of Strings that this tab will need to track.
     * It is called once during initialization of the Tab, as there are a certain number of known observables.
     * 
     * @return
     */
    protected abstract List<ObservableList<String>> defineObservableLists();
    
    /**
     * This method should specify the default input values when creating a new object.
     * 
     * @return
     */
    protected abstract IReadableData generateDefaultData();

}
