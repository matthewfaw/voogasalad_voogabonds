package authoring.view.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public abstract class ListTab<A> extends Tab {
    public static final String ADD = "Add";
    
    private ObservableList<A> myList;
    private ListView<A> myListView;
    
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
        // Set up content
        VBox content = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        // Set up add button
        Button add = new Button(ADD);
        add.setOnAction(handleAddNewObject());
        
        // Set up ListView
        if (list != null) {
            myList = list;
        } else {
            myList = FXCollections.observableArrayList();
        }
        myListView = new ListView<A>(myList);
        
        // Add Nodes to Tab
        scroll.setContent(myListView);
        content.getChildren().addAll(add, scroll);
        this.setContent(content);
    }
    
    protected abstract EventHandler<ActionEvent> handleAddNewObject();

}
