package authoring.view.tabs.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.AttributeFetcher;
import authoring.model.ComponentData;
import authoring.view.tabs.ISubmittable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utility.FileRetriever;
import utility.ListStringManipulator;

/**
 * @author Niklas Sjoquist
 *
 * A menu box that allows the user to edit a Component of an Entity.
 *
 */
public class EditComponentBox extends VBox implements ISubmittable {
    private final String PACKAGE = "engine/model/strategies/";
    private final String STRATEGY = "strategy";
    private final String DAMAGE = "damage";
    private final String MOVEMENT = "movement";
    private final String SPAWNING = "spawning";
    private final String TARGET = "target";
    private final String WIN = "win";
    private final String LOSE = "lose";
    private final String EXTENSION = ".class";
    public static final String IMAGE_PATH = "myImagePath";
    public static final String DONE = "Done";
    public static final String CANCEL = "Cancel";
    private static final int SPACING = 2;

    private List<Label> myLabels;
    private List<TextField> myFields;
    private List<MenuButton> myComboCheckBoxes;
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
        List<String> attributeTypes = fetcher.getComponentAttributeTypeList(componentName);
        init(parent, grandparent, componentName, attributes.size());

        // Set up input fields
        for (int i = 0; i < attributes.size(); i++) {
            String attributeType = attributeTypes.get(i);
            String uglyAttributeName = attributes.get(i);
            //String cleanedAttributeName = cleanUpAttributeName(uglyAttributeName);
            //System.out.println("Attribute: "+cleanedAttributeName+" ("+uglyAttributeName+")\nType: "+attributeType);
            Label lbl = new Label(uglyAttributeName);
            if (attributeType.toLowerCase().equals("list")) {
                // TODO: set up combo box of choices
                MenuButton checkbox = null;
                if (uglyAttributeName.toLowerCase().contains("terrains")) {
                    checkbox = grandparent.setUpMenuButton("Terrains", FXCollections.observableArrayList(grandparent.getTerrains()));
                } //else if ()
                if (checkbox != null) {
                    this.getChildren().add(checkbox);
                }
            } else {
                if (uglyAttributeName.toLowerCase().contains(STRATEGY)) {
                    // TODO: Make ComboBox for Strategies!
                    
                    List<String> fileList = new ArrayList<String>();
                    FileRetriever fr;
                    
                    if (uglyAttributeName.toLowerCase().contains(DAMAGE)) {
                        fr = new FileRetriever(PACKAGE+DAMAGE);
                    } else if (uglyAttributeName.toLowerCase().contains(MOVEMENT)) {
                        fr = new FileRetriever(PACKAGE+MOVEMENT);
                    } else if (uglyAttributeName.toLowerCase().contains(SPAWNING)) {
                        fr = new FileRetriever(PACKAGE+SPAWNING);
                    } else if (uglyAttributeName.toLowerCase().contains(TARGET)) {
                        fr = new FileRetriever(PACKAGE+TARGET);
                    } else if (uglyAttributeName.toLowerCase().contains(WIN) || uglyAttributeName.toLowerCase().contains(LOSE)) {
                        fr = new FileRetriever(PACKAGE+WIN+LOSE);
                    } else {
                        fr = new FileRetriever(PACKAGE+MOVEMENT);
                    }
                    
                    fileList = fr.getFileNames("/");
                    ComboBox<String> strategyChoice = grandparent.setUpStringComboBoxWithLabel(lbl.getText(), null, FXCollections.observableArrayList(fileList), this);
                    
                }
                TextField field = new TextField();
                setUpLabeledField(lbl, field);
                if (lbl.getText().equals(IMAGE_PATH)) {
                    Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                    this.getChildren().add(browse);
                }
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
        String uglyComponentName = this.convertBackToFieldName(componentName);
        List<String> attributes = fetcher.getComponentAttributeList(uglyComponentName);
        List<String> attributeTypes = fetcher.getComponentAttributeTypeList(uglyComponentName);
        Map<String,String> retrievedData = componentData.getFields();
        init(parent, grandparent, componentName, retrievedData.size());

        // Set up input fields
        for (int i = 0; i < attributes.size(); i++) {
            String attributeType = attributeTypes.get(i);
            String uglyAttributeName = attributes.get(i);
            //String cleanedAttributeName = cleanUpAttributeName(uglyAttributeName);
            //System.out.println("Attribute: "+cleanedAttributeName+" ("+uglyAttributeName+")\nType: "+attributeType);
            Label lbl = new Label(uglyAttributeName);
            if (attributeType.toLowerCase().equals("list")) {
                // TODO: set up combo box of choices
                MenuButton checkbox = null;
                if (uglyAttributeName.toLowerCase().contains("terrains")) {
                    checkbox = grandparent.setUpMenuButton("Terrains", FXCollections.observableArrayList(grandparent.getTerrains()));
                    String terrainData = componentData.getFields().get(uglyAttributeName);
                    //System.out.println("Terrains: "+terrainData);
                    List<String> terrains;
                    try {
                        terrains = ListStringManipulator.stringToList(terrainData);
                    }
                    catch (Exception e) {
                        terrains = null;
                        //System.out.println("Terrains is null");
                    }
                    // Initialize valid terrains
                    if (terrains != null && terrains.size() > 0) {
                        for (MenuItem item : checkbox.getItems()) {
                            CheckBox check = (CheckBox) ((CustomMenuItem) item).getContent();
                            if (terrains.contains(check.getText())) {
                                check.setSelected(true);
                            }
                        }
                    }
                } //else if () other checkboxes
                if (checkbox != null) {
                    lbl.setId("label");
                    lbl.setLabelFor(checkbox);
                    checkbox.setId("combobox");
                    myLabels.add(lbl);
                    myComboCheckBoxes.add(checkbox);
                    this.getChildren().add(lbl);
                    this.getChildren().add(checkbox);
                }
            } else {
                //System.out.println("Clean: "+retrievedData.get(cleanedAttributeName)+"\tUgly: "+retrievedData.get(uglyAttributeName));
                TextField field = new TextField(retrievedData.get(uglyAttributeName));
                setUpLabeledField(lbl, field);
                if (lbl.getText().equals(IMAGE_PATH)) {
                    Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                    this.getChildren().add(browse);
                }
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
        myComboCheckBoxes = new ArrayList<MenuButton>();
    }

    private String cleanUpAttributeName(String attribute) {
        // assume all attributes start with 'my', and separate words
        String cleanedName = attribute;
        if (cleanedName.startsWith("my")) {
            cleanedName = cleanedName.substring(2);
        } else {
            cleanedName = capitalizeFirstLetter(cleanedName);
        }
        if (!attribute.contains(" ")) {
            cleanedName = this.separateCapitalizedWords(cleanedName);
        }
        return cleanedName;
    }
    
    private String capitalizeFirstLetter(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
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
    
    /**
     * Converts name back into form stored in AttributeFetcher.
     * 
     * Converts from the form: 'Physical Component'
     * To the form: '.PhysicalComponent'
     * 
     * @param cleanedName
     * @return
     */
    private String convertBackToFieldName(String cleanedName) {
        return "."+smoosh(cleanedName);
    }
    
    private String smoosh(String spaced) {
        return spaced.replaceAll("\\s", "");
    }

    private void setUpLabeledField(Label lbl, TextField field) {
        lbl.setLabelFor(field);
        lbl.setId("label");
        field.setId("textfield");
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
            Label lbl = myLabels.get(i);
            String attributeName = lbl.getText();
            Node input = lbl.getLabelFor();
            if (input.getClass()==TextField.class) {
                String attributeValue = myFields.get(i).getCharacters().toString();
                //System.out.println("Attribute Name: "+attributeName+"\tAttribute Value: "+attributeValue);
                component.addField(attributeName, attributeValue);
            } else if (input.getClass()==MenuButton.class) {
                MenuButton checkbox = (MenuButton) input;
                List<String> selectedItems = new ArrayList<String>();
                for (MenuItem item : checkbox.getItems()) {
                    CheckBox check = (CheckBox) ((CustomMenuItem) item).getContent();
                    if (check.isSelected()) {
                        selectedItems.add(check.getText());
                    }
                }
                String list = ListStringManipulator.listToString(selectedItems);
                //System.out.println(list);
                component.addField(attributeName, list);
            }
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
