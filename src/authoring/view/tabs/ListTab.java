package authoring.view.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public abstract class ListTab<A> extends Tab {
    public static final String ADD = "Add";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    
    private ObservableList<A> myList;
    private ListView<A> myListView;
    private TilePane myContent;
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
    
    public ListTab (String text) {
        super(text);
        initContent();
    }
    
    public ListTab (ObservableList<A> list) {
        initContent(list);
    }

    public ListTab (String text, ObservableList<A> list) {
        super(text);
        initContent(list);
    }
    
    public ObservableList<A> getList() {
        return myList;
    }
    
    public void setList(ObservableList<A> list) {
        myList = list;
    }
    
    /**
     * @param handler - sets the action upon a click
     */
    public void setClickAction(EventHandler handler) {
        myListView.setOnMouseClicked(handler);
    }
    
    /**
     * @param handler - sets the action upon releasing a key
     */
    public void setKeyboardAction(EventHandler handler) {
        myListView.setOnKeyReleased(handler);
    }
    
    private void initContent() {
        initContent(null);
    }
    
    private void initContent(ObservableList<A> list) {
        Dimension screenSize = retrieveScreenSize();
        // Set up content
        myContent = new TilePane();
        myContent.setPrefColumns(2);
        VBox left = new VBox();
        ScrollPane scroll = new ScrollPane();
        scroll.prefWidthProperty().bind(left.widthProperty());
        scroll.prefHeightProperty().bind(left.heightProperty());
        left.setPrefWidth(screenSize.getWidth()/2.0);
        left.setPrefHeight(screenSize.getHeight()/1.5);
        
        // Set up add button
        Button add = new Button(ADD);
        add.setOnAction(handleAddNewObject());
        add.prefWidthProperty().bind(left.widthProperty());
        
        // Set up ListView
        if (list != null) {
            myList = list;
        } else {
            myList = FXCollections.observableArrayList();
        }
        myListView = new ListView<A>(myList);
        myListView.prefWidthProperty().bind(scroll.widthProperty());
        myListView.prefHeightProperty().bind(scroll.heightProperty());
        // Add Nodes to Tab
        scroll.setContent(myListView);
        left.getChildren().addAll(add, scroll);
        myContent.getChildren().add(left);
        this.setContent(myContent);
    }
    
    private Dimension retrieveScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }
    
    protected abstract EventHandler<ActionEvent> handleAddNewObject();
    
    protected TilePane getTilePane(){
    	return myContent;
    }
    
    protected ResourceBundle getResources(){
    	return myResources;
    }

}
