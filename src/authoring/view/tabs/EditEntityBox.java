package authoring.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import com.sun.javafx.collections.MappingChange.Map;
import authoring.model.AttributeFetcher;
import authoring.model.ComponentData;
import authoring.model.EntityData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class EditEntityBox extends VBox {
    private static final int SPACING = 2;
    
    private static final String NAME_CHANGE_ALERT = "Name changed";
    private static final String NAME_CHANGED = "The name of this Entity has been changed.";
    private static final String NAME_CONTENT = "Would you like to confirm changes?";
    
    private EntityTab myTab;
    private String entityName;
    
    private ObservableList<String> myComponents;
    private HashMap<String, ComponentData> myComponentData = new HashMap<String, ComponentData>();
    
    // Fields
    private TextField nameField;
    private ComboBox<String> componentsBox;
    private ListView<String> myComponentsView;

    public EditEntityBox (EntityTab parent, AttributeFetcher fetcher) {
        super(SPACING);
        myTab = parent;
        nameField = parent.setUpTextInputWithLabel(myTab.getResources().getString("EnterName"), myTab.getResources().getString("Entity"), this);
        setUpComponentBox(parent, fetcher);
        setUpComponentListView(fetcher, null);
        HBox buttons = setUpButtonContainer();
        this.getChildren().addAll(myComponentsView, buttons);
    }
    
    public EditEntityBox (EntityTab parent, AttributeFetcher fetcher, EntityData entityData) {
        super(SPACING);
        myTab = parent;
        retrieveEntityData(entityData);
        nameField = parent.setUpTextInputWithLabel(myTab.getResources().getString("EnterName"), entityName, this);
        setUpComponentBox(parent, fetcher);
        setUpComponentListView(fetcher, entityData.getComponents().keySet());
        HBox buttons = setUpButtonContainer();
        this.getChildren().addAll(myComponentsView, buttons);
    }
    
    public void editComponent(String name, ComponentData data) {
        myComponentData.put(name, data);
    }
    
    private void retrieveEntityData(EntityData entityData) {
        entityName = entityData.getName();
        for (String component : entityData.getComponents().keySet()) {
            myComponentData.put(component, entityData.getComponents().get(component));
        }
    }
    
    private void setUpComponentBox(EntityTab parent, AttributeFetcher fetcher) {
        ObservableList<String> componentList = FXCollections.observableArrayList(fetcher.getComponentList());
        componentsBox = parent.setUpStringComboBoxWithLabel(myTab.getResources().getString("AddComponents"), null, componentList, this);
        componentsBox.setOnAction(handleAddComponent(fetcher));
    }
    
    private void setUpComponentListView(AttributeFetcher fetcher, Set<String> components) {
        if (components != null) {
            myComponents = FXCollections.observableArrayList(components);
        } else {
            myComponents = FXCollections.observableArrayList();
        }
        myComponentsView = new ListView<String>(myComponents);
        myComponentsView.setOnMouseClicked(handleEditComponent(fetcher));
    }
    
    private HBox setUpButtonContainer() {
        HBox buttons = new HBox(SPACING);
        buttons.setPadding(new Insets(SPACING,SPACING,SPACING,SPACING));
        
        // Set up finish button
        Button done = new Button(myTab.getResources().getString("Finish"));
        done.setOnAction(handleDone(entityName));
        
        // Set up cancel button
        Button cancel = new Button(myTab.getResources().getString("Cancel"));
        cancel.setOnAction(handleCancel());
        
        // Add buttons to container
        buttons.getChildren().addAll(done, cancel);
        return buttons;
    }
    
    private EventHandler<ActionEvent> handleAddComponent(AttributeFetcher fetcher) {
        EventHandler<ActionEvent> addToListView = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                String component = componentsBox.getValue();
                if (!myComponents.contains(component)) {
                    myComponents.add(component);
                    ComponentData data = new ComponentData();
                    for (String attribute : fetcher.getComponentAttributeList(component)) {
                        data.addField(attribute, "");
                    }
                    myComponentData.put(component, data);
                }
            }
        };
        return addToListView;
    }
    
    /**
     * @param oldName - is equal to null when creating a new Entity
     * @return
     */
    private EventHandler<ActionEvent> handleDone(String oldName) {
        EventHandler<ActionEvent> finish = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                EntityData entity = createDataFromInput();
                if (oldName == null) {
                    tryAddNewEntity(entity);
                }
                else {
                    // editing existing entity
                    if (!oldName.equals(entity.getName())) {
                        // changed name of entity
                        Optional<ButtonType> result = showNameChangeConfirmation();
                        if (result.get() == ButtonType.OK) {
                            tryEditEntity(oldName, entity);
                        }
                    } else {
                        // overwrite data to existing name
                        tryEditEntity(oldName, entity);
                    }
                }
            }
        };
        return finish;
    }
    
    private Optional<ButtonType> showNameChangeConfirmation() {
        Alert changeNameAlert = new Alert(AlertType.CONFIRMATION);
        changeNameAlert.setTitle(NAME_CHANGE_ALERT);
        changeNameAlert.setHeaderText(NAME_CHANGED);
        changeNameAlert.setContentText(NAME_CONTENT);
        Optional<ButtonType> result = changeNameAlert.showAndWait();
        return result;
    }
    
    private void tryAddNewEntity(EntityData entity) {
        if (myTab.addEntity(entity)) {
            System.out.println("Added Entity");
            for (String component : entity.getComponents().keySet()) {
                myComponentData.put(component, entity.getComponents().get(component));
            }
            //System.out.println("Entity has "+myComponentData.size()+" Components");
            myTab.getTilePane().getChildren().remove(EditEntityBox.this); // this = reference of parent (i.e., this EditEntityBox class)
        }
    }
    
    private void tryEditEntity(String oldName, EntityData entity) {
        if (myTab.updateEntity(oldName, entity)) {
            myTab.getTilePane().getChildren().remove(EditEntityBox.this); // this = reference of parent (i.e., this EditEntityBox class)
        }
    }
    
    private EventHandler<ActionEvent> handleCancel() {
        EventHandler<ActionEvent> cancel = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                myTab.getTilePane().getChildren().remove(EditEntityBox.this); // this = reference of parent (i.e., this EditEntityBox class)
            }
        };
        return cancel;
    }
    
    private EntityData createDataFromInput() {
        // define name+components
        EntityData entity = new EntityData();
        entity.setName(nameField.getCharacters().toString());
        for (String component : myComponentsView.getItems()) {
            //System.out.println(component+" component created: "+myComponentData.get(component));
            entity.addComponent(component, myComponentData.get(component));
        }
        return entity;
    }
    
    private EventHandler<MouseEvent> handleEditComponent(AttributeFetcher fetcher) {
        EventHandler<MouseEvent> handleEdit = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedAttribute = myComponentsView.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 2 && selectedAttribute!=null) {
                    //System.out.println("Double Click: "+selectedAttribute);
                    EditComponentBox editComponent;
                    if (myComponentData.containsKey(selectedAttribute)) {
                        //System.out.println("Entity contains this key! (edit)");
                        editComponent = new EditComponentBox(EditEntityBox.this, myTab, selectedAttribute, myComponentData.get(selectedAttribute).getFields());
                    } else {
                        //System.out.println("Entity does not contain this key yet! (new)");
                        editComponent = new EditComponentBox(EditEntityBox.this, myTab, selectedAttribute, fetcher.getComponentAttributeList(selectedAttribute));
                    }
                    
                    myTab.getTilePane().getChildren().add(editComponent);
                }
            }
        };
        return handleEdit;
    }

}
