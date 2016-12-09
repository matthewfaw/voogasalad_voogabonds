package authoring.view.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class EditEntityBox extends VBox {
    private static final int SPACING = 2;
    
    private EntityTab myTab;
    
    private ObservableList<String> myComponents = FXCollections.observableArrayList();
    private HashMap<String, ComponentData> myComponentData = new HashMap<String, ComponentData>();
    
    // Fields
    private TextField nameField;
    private ComboBox<String> componentsBox;
    private ListView<String> myComponentsView;

    public EditEntityBox (EntityTab parent, AttributeFetcher fetcher) {
        // Set up spacing
        super(SPACING);
        myTab = parent;
        // Set up name label+field
        Label nameLbl = new Label(myTab.getResources().getString("EnterName"));
        nameField = new TextField(myTab.getResources().getString("Entity"));
        nameLbl.setLabelFor(nameField);
        // Set up components ComboBox
        Label addComponentLbl = new Label(myTab.getResources().getString("AddComponents"));
        ObservableList<String> componentList = FXCollections.observableArrayList(fetcher.getComponentList());
        componentsBox = new ComboBox<String>(componentList);
        addComponentLbl.setLabelFor(componentsBox);
        componentsBox.setOnAction(handleAddComponent());
        // Set up ListView of components
        myComponentsView = new ListView<String>(myComponents);
        myComponentsView.setOnMouseClicked(handleEditComponent(fetcher));
        // Set up button container
        HBox buttons = new HBox(SPACING);
        buttons.setPadding(new Insets(SPACING,SPACING,SPACING,SPACING));
        // Set up finish button
        Button done = new Button(myTab.getResources().getString("Finish"));
        done.setOnAction(handleDone());
        // Set up cancel button
        Button cancel = new Button(myTab.getResources().getString("Cancel"));
        cancel.setOnAction(handleCancel());
        // Add buttons to container
        buttons.getChildren().addAll(done, cancel);
        // Add nodes to this VBox
        this.getChildren().addAll(nameLbl, nameField,
                                  addComponentLbl, componentsBox,
                                  myComponentsView,
                                  buttons);
    }
    
    public void editComponent(String name, ComponentData data) {
        myComponentData.put(name, data);
    }
    
    private EventHandler<ActionEvent> handleAddComponent() {
        EventHandler<ActionEvent> addToListView = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                myComponents.add(componentsBox.getValue());
            }
        };
        return addToListView;
    }
    
    private EventHandler<ActionEvent> handleDone() {
        EventHandler<ActionEvent> finish = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                EntityData entity = createDataFromInput();
                if (myTab.addEntity(entity)) {
                    myTab.getTilePane().getChildren().remove(EditEntityBox.this); // this = reference of parent (i.e., this EditEntityBox class)
                }
            }
        };
        return finish;
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
        for (String component : myComponentData.keySet()) {
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
                        editComponent = new EditComponentBox(EditEntityBox.this, myTab, myComponentData.get(selectedAttribute).getFields());
                    } else {
                        editComponent = new EditComponentBox(EditEntityBox.this, myTab, fetcher.getComponentAttributeList(selectedAttribute));
                    }
                    
                    myTab.getTilePane().getChildren().add(editComponent);
                }
            }
        };
        return handleEdit;
    }

}
