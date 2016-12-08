package authoring.view.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public abstract class ListTab<A> extends AuthoringTab {
    public static final String ADD = "Add";
    
    private ObservableList<A> myList;
    private ListView<A> myListView;
    private TilePane myContent;
    
    public ListTab (String text) {
        super(text);
        initContent();
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
    public void setClickAction(EventHandler<MouseEvent> handler) {
        myListView.setOnMouseClicked(handler);
    }
    
    /**
     * @param handler - sets the action upon releasing a key
     */
    public void setKeyboardAction(EventHandler<KeyEvent> handler) {
        myListView.setOnKeyReleased(handler);
    }
    
    private void initContent() {
        initContent(null);
    }
    
    private void initContent(ObservableList<A> list) {
        // Set up content
        myContent = new TilePane();
        myContent.setPrefColumns(2);
        VBox left = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        // Set up add button
        Button add = new Button(ADD);
        add.setOnAction(handleAddNewObject());
        add.setMinWidth(300);
        
        // Set up ListView
        if (list != null) {
            myList = list;
        } else {
            myList = FXCollections.observableArrayList();
        }
        myListView = new ListView<A>(myList);
        myListView.setMinWidth(300);
        // Add Nodes to Tab
        scroll.setContent(myListView);
        left.getChildren().addAll(add, scroll);
        myContent.getChildren().add(left);
        this.setContent(myContent);
    }
    
    protected abstract EventHandler<ActionEvent> handleAddNewObject();
    
    protected TilePane getTilePane(){
    	return myContent;
    }
    
    protected ObservableList<A> getObservableList(){
    	return myList;
    }

}
