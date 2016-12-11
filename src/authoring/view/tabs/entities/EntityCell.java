package authoring.view.tabs.entities;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author Niklas Sjoquist
 * 
 * @resource answer by Rainer Schwarze on http://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
 *
 * 
 */
public class EntityCell extends ListCell<String> {
    private HBox cellBox = new HBox();
    private Label label = new Label("(empty)");
    private Pane cellPane = new Pane();
    private Button deleteButton = new Button("X");
}
