package authoring.view.tabs.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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
    
    public EntityCell(EntityTab parent) {
        super();
        cellBox.getChildren().addAll(label,cellPane,deleteButton);
        HBox.setHgrow(cellPane, Priority.ALWAYS);
        deleteButton.setOnAction(deleteEntity(parent));
    }
    
    private EventHandler<ActionEvent> deleteEntity(EntityTab parent) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent event) {
                // TODO Auto-generated method stub
                
            }
            
        };
    }
    
}
