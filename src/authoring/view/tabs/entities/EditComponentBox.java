package authoring.view.tabs.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.AttributeFetcher;
import authoring.model.ComponentData;
import authoring.view.tabs.ISubmittable;
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
    public EditComponentBox (EditEntityBox parent, EntityTab grandparent, AttributeFetcher fetcher, String componentName) {
        super(SPACING);
        List<String> attributes = fetcher.getComponentAttributeList(componentName);
        init(parent, grandparent, componentName, attributes.size());

        // Set up input fields
        for (int i = 0; i < attributes.size(); i++) {
            Label lbl = new Label(cleanUpAttributeName(attributes.get(i)));
            TextField field = new TextField();
            setUpLabeledField(lbl, field);
            if (lbl.getText().equals("Image Path")) {
                Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                this.getChildren().add(browse);
            }
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
    public EditComponentBox(EditEntityBox parent, EntityTab grandparent, AttributeFetcher fetcher, String componentName, ComponentData componentData) {
        super(SPACING);
        Map<String,String> retrievedData = componentData.getFields();
        init(parent, grandparent, componentName, retrievedData.size());

        // Set up input fields
        for (String attributeName : retrievedData.keySet()) {
            String attribute = cleanUpAttributeName(attributeName);
            Label lbl = new Label(attribute);
            TextField field = new TextField(retrievedData.get(attributeName));
            setUpLabeledField(lbl, field);
            if (lbl.getText().equals("Image Path")) {
                Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                this.getChildren().add(browse);
            }
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

    private String cleanUpAttributeName(String attribute) {
        // assume all attributes start with 'my', and separate words
        String cleanedName = attribute;
        if (cleanedName.startsWith("my")) {
            cleanedName = cleanedName.substring(2);
        }
        if (!attribute.contains(" ")) {
            cleanedName = this.separateCapitalizedWords(cleanedName);
        }
        return cleanedName;
    }

    /**
     * Code based from stackoverflow.com
     * 
     * @param smooshed
     * @return
     */
    private String separateCapitalizedWords(String smooshed) {
        return smooshed.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
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
        Button done = setUpSubmitButton();
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
        Button done = new Button(DONE);
        done.setOnAction(handleDone());
        done.setId("button");
        return done;
    }

}
