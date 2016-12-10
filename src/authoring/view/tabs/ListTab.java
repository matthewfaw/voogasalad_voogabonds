package authoring.view.tabs;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public abstract class ListTab<A> extends AuthoringTab {
    public static final String ADD = "Add";
    private static final int OFFSET = 2;
    
    private ObservableList<A> myList;
    private ListView<A> myListView;
    private TilePane myContent;
    
    public ListTab (String text, int prefColumns) {
        super(text);
        initContent(prefColumns);
    }

    public ListTab (String text, int prefColumns, ObservableList<A> list) {
        super(text);
        initContent(prefColumns, list);
    }
    
    public ObservableList<A> getList() {
        return myList;
    }
    
    public void setList(ObservableList<A> list) {
        myList = list;
    }
    
    public ListView<A> getListView() {
        return myListView;
    }
    
    /**
     * @param handler - sets the action upon a click
     */
    public void setClickAction(EventHandler<MouseEvent> handler) {
        myListView.setOnMouseClicked(handler);
    }
    
    /**
     * @param handler - sets the action upon releasing a key
     */
    public void setKeyboardAction(EventHandler<KeyEvent> handler) {
        myListView.setOnKeyReleased(handler);
    }
    
    private void initContent(int prefColumns) {
        initContent(prefColumns, null);
    }
    
    private void initContent(int prefColumns, ObservableList<A> list) {
        Dimension screenSize = retrieveScreenSize();
        // Set up content
        myContent = new TilePane();
        myContent.setPrefColumns(prefColumns);
        VBox left = new VBox();
        ScrollPane scroll = new ScrollPane();
        bindSize(left, scroll);
        left.setPrefWidth(screenSize.getWidth()/prefColumns - OFFSET);
        left.setPrefHeight(screenSize.getHeight()/1.5);
        // Set up add button
        Button add = new Button(ADD);
        add.setOnAction(handleAddNewObject());
        bindWidth(left, add);
        
        // Set up ListView
        if (list != null) {
            myList = list;
        } else {
            myList = FXCollections.observableArrayList();
        }
        myListView = new ListView<A>(myList);
        bindSize(scroll, myListView);
        myListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent event){
        		if (event.getClickCount() == 2 && myListView.getSelectionModel().getSelectedItem()!=null){
                    edit(myListView.getSelectionModel().getSelectedItem());
                }
        	}
        });
        // Add Nodes to Tab
        scroll.setContent(myListView);
        left.getChildren().addAll(add, scroll);
        myContent.getChildren().add(left);
        this.setContent(myContent);
    }
    
    private void bindSize(Region parent, Region child) {
        bindWidth(parent,child);
        bindHeight(parent,child);
    }
    
    private void bindWidth(Region parent, Region child) {
        child.prefWidthProperty().bind(parent.widthProperty());
    }
    
    private void bindHeight(Region parent, Region child) {
        child.prefHeightProperty().bind(parent.heightProperty());
    }
    
    private Dimension retrieveScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }
    
    protected abstract EventHandler<ActionEvent> handleAddNewObject();
    
    protected abstract void edit(A name);
    
    protected TilePane getTilePane(){
    	return myContent;
    }
    
    protected ObservableList<A> getObservableList(){
    	return myList;
    }

}