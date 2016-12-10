package authoring.view.tabs;

import authoring.controller.Container;
import authoring.controller.EntityDataContainer;
import authoring.controller.MapDataContainer;
import authoring.controller.IDataContainer;
import authoring.model.AttributeFetcher;
import authoring.model.EntityData;
import authoring.model.EntityList;
import engine.IObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.List;

public class EntityTab extends ListTab<String> implements IObserver<Container>{
    public static final String TITLE = "Entities";
    public static final String ADD_ENTITY = "Add Entity";
    private static final int COLS = 3;

    private EntityDataContainer myEntities;
    private ArrayList<String> validTerrains = new ArrayList<String>();
    
    private AttributeFetcher ecFetcher = new AttributeFetcher();

    public EntityTab (EntityDataContainer entities) {
        super(TITLE, COLS);
        
        myEntities = entities;
        
        // initialize AttributeFetcher
        ecFetcher.fetch();
        
        this.setClickAction(handleEditEntity());
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
    		    this.getList().set(this.getList().indexOf(oldName), entity.getName());
    		}
    		return true;
    	}catch(Exception e){
    	        this.showError(e.getMessage());
    	        return false;
    	}
    }
    
    private EventHandler<MouseEvent> handleEditEntity () {
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event){
                if (event.getClickCount() == 2) {
                    // TODO: implement retrieving data
                    EntityData clickedEntity = myEntities.getEntityData(EntityTab.this.getListView().getSelectionModel().getSelectedItem());
                    EditEntityBox editEntityMenu = EntityTab.this.getEditEntityMenu(myEntities.getEntityData(clickedEntity.getName()));
                    getTilePane().getChildren().add(editEntityMenu);
                }
//                EditEntityBox newEntityMenu = getNewEntityMenu();
//                getTilePane().getChildren().add(newEntityMenu);
            }
        };
        return handler;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Button setUpFinishButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
