package authoring.view.tabs;

import authoring.controller.Container;
import authoring.controller.IDataContainer;
import authoring.model.EntityData;
import authoring.model.EntityList;
import engine.IObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class EntityTab extends ListTab<String> {
    public static final String TITLE = "Entities";
    public static final String ADD_ENTITY = "Add Entity";

    private EntityList myEntities;
    //private EntityDataController myController;
    

    public EntityTab (EntityList entities) {
        super(TITLE);
        
        // TODO: use EntityList
        myEntities = entities;
    }
    
    public void addEntity(EntityData entity) throws Exception {
        if (!myEntities.contains(entity.getName())) {
            myEntities.addEntity(entity);
        } else {
            throw new Exception("Entity with this name has already been created.");
        }
    }
    
    public void updateEntity(String oldName, EntityData entity) throws Exception {
        if (myEntities.contains(oldName)) {
            if (!oldName.equals(entity.getName())) {
                myEntities.set(oldName, entity);
            }
        } else {
            throw new Exception("Entity with this name has not been created yet.");
        }
    }

    @Override
    protected EventHandler<ActionEvent> handleAddNewObject () {
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                // TODO: implement method
                //IReadableData defaultData = generateDefaultData();
                //myMenu.createObjectMenu(null);
            }
        };
        return handler;
    }

}
