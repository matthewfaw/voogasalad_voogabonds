package authoring.view.tabs.entities;

import authoring.controller.Container;
import authoring.controller.EntityDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.AttributeFetcher;
import authoring.model.EntityData;
import authoring.view.tabs.ListTab;
import engine.IObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import java.util.ArrayList;

public class EntityTab extends ListTab<EntityListView> implements IObserver<Container>{
    public static final String TITLE = "Entities";
    public static final String ADD_ENTITY = "Add Entity";
    private static final int COLS = 3;

    private EntityDataContainer myEntities;
    private ArrayList<String> validTerrains = new ArrayList<String>();

    private AttributeFetcher ecFetcher = new AttributeFetcher();

    public EntityTab(EntityDataContainer entities) throws ClassNotFoundException {
        super(TITLE, COLS);
        myEntities = entities;
        ecFetcher.fetch();
        this.setCellFactory(getCustomCellFactory());
    }

    public boolean addEntity(EntityData entity){
        try{
            myEntities.createEntityData(entity);
            EntityListView entityView = new EntityListView(entity);
            this.getList().add(entityView);
            return true;
        }catch(Exception e){
            this.showError(e.getMessage());
            return false;
        }
    }

    public boolean updateEntity(EntityListView oldEntity, EntityData entity){
        try{
            myEntities.updateEntityData(oldEntity.getEntityName(), entity);
            if (!oldEntity.equals(entity.getName())) {
                this.getList().remove(oldEntity);
                EntityListView updatedEntity = new EntityListView(entity);
                this.getList().add(updatedEntity);
            }
            return true;
        }catch(Exception e){
            this.showError(e.getMessage());
            return false;
        }
    }
    
    public boolean deleteEntity(EntityListView entity) {
        return this.getList().remove(entity);
    }

    public EditEntityBox getNewEntityMenu() {
        return new EditEntityBox(this, ecFetcher);
    }

    public EditEntityBox getEditEntityMenu(EntityData entityData, EntityListView entityView) {
        return new EditEntityBox(this, ecFetcher, entityData, entityView);
    }
    
    private Callback<ListView<EntityListView>, ListCell<EntityListView>> getCustomCellFactory() {
        return new Callback<ListView<EntityListView>, ListCell<EntityListView>>() {
            
            @Override
            public ListCell<EntityListView> call (ListView<EntityListView> param) {
                return new EntityCell(EntityTab.this);
            }
            
        };
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

    @Override
    protected void edit(EntityListView name) {
        EntityListView clickedEntity = EntityTab.this.getListView().getSelectionModel().getSelectedItem();
        EntityData clickedEntityData = myEntities.getEntityData(clickedEntity.getEntityName());
        EditEntityBox editEntityMenu = EntityTab.this.getEditEntityMenu(clickedEntityData, clickedEntity);
        getTilePane().getChildren().add(editEntityMenu);
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
