package authoring.view.tabs;

import authoring.controller.Container;
import authoring.controller.EntityDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.AttributeFetcher;
import authoring.model.EntityData;
import engine.IObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class EntityTab extends ListTab<String> implements IObserver<Container>{
    public static final String TITLE = "Entities";
    public static final String ADD_ENTITY = "Add Entity";
    private static final int COLS = 3;

    private EntityDataContainer myEntities;
    private ArrayList<String> validTerrains = new ArrayList<String>();
    
    private AttributeFetcher ecFetcher = new AttributeFetcher();

    public EntityTab(EntityDataContainer entities) throws ClassNotFoundException {
        super(TITLE, COLS);
        
        myEntities = entities;
        
        // initialize AttributeFetcher
        ecFetcher.fetch();
    }
    
    public boolean addEntity(EntityData entity){
        try{
                System.out.println("Adding Entity: "+entity.getName());
        	myEntities.createEntityData(entity);
        	this.getList().add(entity.getName());
        	return true;
        }catch(Exception e){
        	this.showError(e.getMessage());
        	return false;
        }
    }
    
    public boolean updateEntity(String oldName, EntityData entity){
    	try{
    		myEntities.updateEntityData(oldName, entity);
    		if (!oldName.equals(entity.getName())) {
    		    this.getList().remove(oldName);
    		    this.getList().add(entity.getName());
    		}
    		return true;
    	}catch(Exception e){
    	        this.showError(e.getMessage());
    	        return false;
    	}
    }
    
    public boolean removeEntity(String name) {
        if (this.getList().contains(name)) {
            this.getList().remove(name);
        }
        return myEntities.deleteObjectData(name);
    }

    @Override
    protected EventHandler<ActionEvent> handleAddNewObject () {
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                EditEntityBox newEntityMenu = getNewEntityMenu();
                getTilePane().getChildren().add(newEntityMenu);
            }
        };
        return handler;
    }
    
    public EditEntityBox getNewEntityMenu() {
        return new EditEntityBox(this, ecFetcher);
    }
    
    public EditEntityBox getEditEntityMenu(EntityData entity) {
        return new EditEntityBox(this, ecFetcher, entity);
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

	@Override
	protected void edit(String name) {
                EntityData clickedEntity = myEntities.getEntityData(EntityTab.this.getListView().getSelectionModel().getSelectedItem());
                EditEntityBox editEntityMenu = EntityTab.this.getEditEntityMenu(myEntities.getEntityData(clickedEntity.getName()));
                getTilePane().getChildren().add(editEntityMenu);
	}

}
