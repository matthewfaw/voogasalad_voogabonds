package authoring.view.tabs;

import authoring.controller.Container;
import authoring.controller.EntityDataContainer;
import authoring.controller.MapDataContainer;
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

import java.util.ArrayList;

public class EntityTab extends ListTab<String> implements IObserver<Container>{
    public static final String TITLE = "Entities";
    public static final String ADD_ENTITY = "Add Entity";

    private EntityDataContainer myEntities;
    private ArrayList<String> validTerrains = new ArrayList<String>();
    
    //private EntityDataController myController;
    

    public EntityTab (EntityDataContainer entities) {
        super(TITLE);
        
        // TODO: use EntityList
        myEntities = entities;
    }
    
    public void addEntity(EntityData entity){
        myEntities.createEntityData(entity);
    }
    
    public void updateEntity(String oldName, EntityData entity){
    	myEntities.updateEntityData(oldName, entity);
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
    
    /**
     * IObserver functions
     */
    public void update(Container c){
    	if (c instanceof MapDataContainer){
    		validTerrains.clear();
    		for (String terrain: ((MapDataContainer) c).getValidTerrainMap().keySet()){
    			validTerrains.add(terrain);
    		}
    	}
    }

}
