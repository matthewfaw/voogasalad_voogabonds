package authoring.view.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import com.sun.javafx.collections.MappingChange.Map;
import authoring.model.ComponentData;
import authoring.model.EntityData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    public EditEntityBox (EntityTab parent, List<String> components) {
        // Set up spacing
        super(SPACING);
        myTab = parent;
        // Set up title
        Text title = new Text(myTab.getResources().getString("NewEntity"));
        title.setTextAlignment(TextAlignment.CENTER);
        // Set up name label+field
        Label nameLbl = new Label(myTab.getResources().getString("EnterName"));
        nameField = new TextField(myTab.getResources().getString("Entity"));
        nameLbl.setLabelFor(nameField);
        // Set up components ComboBox
        Label addComponentLbl = new Label(myTab.getResources().getString("AddComponents"));
        ObservableList<String> componentList = FXCollections.observableArrayList(components);
        ComboBox<String> componentsBox = new ComboBox<String>(componentList);
        addComponentLbl.setLabelFor(componentsBox);
        // Set up component ListView
        ListView<String> myComponentsView = new ListView<String>(myComponents);
        // Set up finish button
        Button done = new Button(myTab.getResources().getString("Finish"));
        done.setOnAction(handleDone());
        
        // Add nodes to this VBox
        this.getChildren().addAll(title,
                                  nameLbl, nameField,
                                  addComponentLbl, componentsBox,
                                  myComponentsView,
                                  done);
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
    
    private EntityData createDataFromInput() {
        // define name+components
        EntityData entity = new EntityData();
        entity.setName(nameField.getCharacters().toString());
        for (String component : myComponentData.keySet()) {
            entity.addComponent(component, myComponentData.get(component));
        }
        return entity;
    }

}
