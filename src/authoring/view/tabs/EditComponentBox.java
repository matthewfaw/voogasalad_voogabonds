package authoring.view.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.ComponentData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Niklas Sjoquist
 *
 * A menu box that allows the user to edit a Component of an Entity.
 *
 */
public class EditComponentBox extends VBox implements ISubmittable {
    public static final String DONE = "Done";
    public static final String CANCEL = "Cancel";
    private static final int SPACING = 2;
    
    private List<Label> myLabels;
    private List<TextField> myFields;
    private EditEntityBox parent;
    private EntityTab grandparent;
    private String myName;

    /**
     * Create new component.
     * @param parent
     * @param grandparent
     * @param componentName
     * @param attributes
     */
    public EditComponentBox (EditEntityBox parent, EntityTab grandparent, String componentName, List<String> attributes) {
        super(SPACING);
        init(parent, grandparent, componentName, attributes.size());
        
        // Set up input fields
        for (int i = 0; i < attributes.size(); i++) {
            Label lbl = new Label(attributes.get(i));
            TextField field = new TextField();
            setUpLabeledField(lbl, field);
        }
        
        HBox btns = getBottomButtons();
        this.getChildren().add(btns);
    }
    
    /**
     * Edit existing component.
     * @param parent
     * @param grandparent
     * @param componentName
     * @param retrievedData
     */
    public EditComponentBox(EditEntityBox parent, EntityTab grandparent, String componentName, Map<String,String> retrievedData) {
        super(SPACING);
        init(parent, grandparent, componentName, retrievedData.size());
        
        // Set up input fields
        for (String attributeName : retrievedData.keySet()) {
            Label lbl = new Label(attributeName);
            TextField field = new TextField(retrievedData.get(attributeName));
            setUpLabeledField(lbl, field);
        }
        
        HBox btns = getBottomButtons();
        this.getChildren().add(btns);
    }
    
    private void init(EditEntityBox parent, EntityTab grandparent, String componentName, int numAttributes) {
        this.setId("vbox");
        this.parent = parent;
        this.grandparent = grandparent;
        myName = componentName;
        myLabels = new ArrayList<Label>(numAttributes);
        myFields = new ArrayList<TextField>(numAttributes);
    }
    
    private void setUpLabeledField(Label lbl, TextField field) {
        lbl.setLabelFor(field);
        lbl.setId("label");
        myLabels.add(lbl);
        myFields.add(field);
        this.getChildren().add(lbl);
        this.getChildren().add(field);
    }
    
    private HBox getBottomButtons() {
        HBox btns = new HBox(SPACING);
        btns.setPadding(new Insets(SPACING,SPACING,SPACING,SPACING));
        btns.setId("hbox");
        Button done = new Button(DONE);
        done.setOnAction(handleDone());
        done.setId("button");
        Button cancel = new Button(CANCEL);
        cancel.setOnAction(handleCancel());
        cancel.setId("button");
        btns.getChildren().addAll(done,cancel);
        return btns;
    }
    
    private EventHandler<ActionEvent> handleDone() {
        EventHandler<ActionEvent> finish = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                String name = getName();
                ComponentData entity = createDataFromInput();
                //System.out.println("Put Component ("+name+") in parent map");
                parent.editComponent(name, entity);
                grandparent.getTilePane().getChildren().remove(EditComponentBox.this); // this = reference of parent (i.e., this EditEntityBox class)
            }
        };
        return finish;
    }
    
    private EventHandler<ActionEvent> handleCancel() {
        EventHandler<ActionEvent> cancel = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                grandparent.getTilePane().getChildren().remove(EditComponentBox.this); // this = reference of parent (i.e., this EditEntityBox class)
            }
        };
        return cancel;
    }

    private ComponentData createDataFromInput () {
        ComponentData component = new ComponentData();
        for (int i = 0; i < myLabels.size(); i++) {
            String attributeName = myLabels.get(i).getText();
            String attributeValue = myFields.get(i).getCharacters().toString();
            //System.out.println("Attribute Name: "+attributeName+"\tAttribute Value: "+attributeValue);
            component.addField(attributeName, attributeValue);
        }
        return component;
    }
    
    private String getName() {
        return myName;
    }

	@Override
	public Button setUpSubmitButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
