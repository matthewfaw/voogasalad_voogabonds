package authoring.view.tabs.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.AttributeFetcher;
import authoring.model.ComponentData;
import authoring.view.tabs.ISubmittable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private final String TERRAINS = "Terrains";
    private final String CALC = "calc";
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
            ////System.out.println("Attribute: "+cleanedAttributeName+" ("+uglyAttributeName+")\nType: "+attributeType);
            Label lbl = new Label(uglyAttributeName);
            if (attributeType.toLowerCase().equals("list")) {
                // TODO: set up combo box of choices
                MenuButton checkbox = null;
                if (uglyAttributeName.toLowerCase().contains(TERRAINS.toLowerCase())) {
                    checkbox = grandparent.setUpMenuButton(TERRAINS, FXCollections.observableArrayList(grandparent.getTerrains()));
                } //else if ()
                if (checkbox != null) {
                    this.getChildren().add(checkbox);
                }
            } else if (attributeType.toLowerCase().equals("boolean")) {
                ObservableList<String> choices = FXCollections.observableArrayList("True", "False");
                ComboBox<String> boolCombo = new ComboBox<>(choices);
                lbl.setLabelFor(boolCombo);
                lbl.setId("label");
                boolCombo.setId("combobox");
                myLabels.add(lbl);
                this.getChildren().addAll(lbl, boolCombo);
            } else if (attributeType.toLowerCase().equals("int")) {
                TextField numericField = new TextField();
                setUpLabeledField(lbl, numericField);
                numericField.textProperty().addListener(new ChangeListener<String>() {
                    // Forces numeric input
                    @Override
                    public void changed (ObservableValue<? extends String> observable,
                                         String oldValue,
                                         String newValue) {
                        if (!newValue.matches("\\d*")) {
                            numericField.setText(newValue.replaceAll("[^\\d]",""));
                        }
                    }
                });
            } else if (attributeType.toLowerCase().equals("double")) {
                TextField numericField = new TextField();
                setUpLabeledField(lbl, numericField);
                numericField.textProperty().addListener(new ChangeListener<String>() {
                    // Forces double input
                    @Override
                    public void changed (ObservableValue<? extends String> observable,
                                         String oldValue,
                                         String newValue) {
                        if (!newValue.matches("\\d+\\.\\d+")) {
                            numericField.setText(newValue.replaceAll("[^-?\\d+(\\.\\d+)?]", ""));
                        }
                    }
                });
            }
            else {
                if (uglyAttributeName.toLowerCase().contains(STRATEGY) || uglyAttributeName.toLowerCase().contains(CALC)) {
                    // TODO: replace if/else
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
                    List<String> strategies = new ArrayList<String>(fileList.size());
                    fileList.forEach((path) -> strategies.add(stripStrategyClassPath(path)));
                    ComboBox<String> strategyChoice = new ComboBox<String>(FXCollections.observableArrayList(strategies));
                    lbl.setLabelFor(strategyChoice);
                    lbl.setId("label");
                    myLabels.add(lbl);
                    strategyChoice.setId("combobox");
                    this.getChildren().addAll(lbl, strategyChoice);
                }
                else {
                    TextField field = new TextField();
                    setUpLabeledField(lbl, field);
                    if (lbl.getText().equals(IMAGE_PATH)) {
                        Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                        this.getChildren().add(browse);
                    }
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
            ////System.out.println("Attribute: "+cleanedAttributeName+" ("+uglyAttributeName+")\nType: "+attributeType);
            Label lbl = new Label(uglyAttributeName);
            if (attributeType.toLowerCase().equals("list")) {
                // TODO: set up combo box of choices
                MenuButton checkbox = null;
                if (uglyAttributeName.toLowerCase().contains("terrains")) {
                    checkbox = grandparent.setUpMenuButton("Terrains", FXCollections.observableArrayList(grandparent.getTerrains()));
                    String terrainData = componentData.getFields().get(uglyAttributeName);
                    ////System.out.println("Terrains: "+terrainData);
                    List<String> terrains;
                    try {
                        terrains = ListStringManipulator.stringToList(terrainData);
                    }
                    catch (Exception e) {
                        terrains = null;
                        ////System.out.println("Terrains is null");
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
            } else if (attributeType.toLowerCase().equals("boolean")) {
                // TODO: Set combo box for booleans
                ObservableList<String> choices = FXCollections.observableArrayList("True", "False");
                ComboBox<String> boolCombo = new ComboBox<>(choices);
                lbl.setLabelFor(boolCombo);
                lbl.setId("label");
                boolCombo.setValue(retrievedData.get(uglyAttributeName));
                boolCombo.setId("combobox");
                myLabels.add(lbl);
                this.getChildren().addAll(lbl, boolCombo);
            } else if (attributeType.toLowerCase().equals("int")) {
                //System.out.println("NUMERIC FIELD");
                TextField numericField = new TextField(retrievedData.get(uglyAttributeName));
                setUpLabeledField(lbl, numericField);
                numericField.textProperty().addListener(new ChangeListener<String>() {
                    // Forces numeric input
                    @Override
                    public void changed (ObservableValue<? extends String> observable,
                                         String oldValue,
                                         String newValue) {
                        if (!newValue.matches("\\d*")) {
                            numericField.setText(newValue.replaceAll("[^\\d]",""));
                        }
                    }
                });
            } else if (attributeType.toLowerCase().equals("double")) {
                TextField numericField = new TextField(retrievedData.get(uglyAttributeName));
                setUpLabeledField(lbl, numericField);
                numericField.textProperty().addListener(new ChangeListener<String>() {
                    // Forces double input
                    @Override
                    public void changed (ObservableValue<? extends String> observable,
                                         String oldValue,
                                         String newValue) {
                        if (!newValue.matches("\\d+\\.?\\d+")) {
                            numericField.setText(newValue.replaceAll("[^-?\\d+(\\.\\d+)?]", ""));
                        }
                    }
                });
            }
            else {
                if (uglyAttributeName.toLowerCase().contains(STRATEGY) || uglyAttributeName.toLowerCase().contains(CALC)) {
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
                    List<String> strategies = new ArrayList<String>(fileList.size());
                    fileList.forEach((path) -> strategies.add(stripStrategyClassPath(path)));
                    ComboBox<String> strategyChoice = new ComboBox<String>(FXCollections.observableArrayList(strategies));
                    strategyChoice.setValue(retrievedData.get(uglyAttributeName));
                    lbl.setLabelFor(strategyChoice);
                    lbl.setId("label");
                    myLabels.add(lbl);
                    strategyChoice.setId("combobox");
                    this.getChildren().addAll(lbl, strategyChoice);
                }
                else {
                    TextField field = new TextField(retrievedData.get(uglyAttributeName));
                    setUpLabeledField(lbl, field);
                    if (lbl.getText().equals(IMAGE_PATH)) {
                        Button browse = grandparent.setUpBrowseButton(field, "PNG", "*.png");
                        this.getChildren().add(browse);
                    }
                }
            }
        }

        HBox btns = getBottomButtons();
        this.getChildren().add(btns);
    }
    
    private String stripStrategyClassPath(String strategyClassPath) {
        int lastSlash = strategyClassPath.lastIndexOf("/");
        return strategyClassPath.substring(lastSlash+1,strategyClassPath.length()-EXTENSION.length());
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

    @Deprecated
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
    
    @Deprecated
    private String capitalizeFirstLetter(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    /**
     * Code based from stackoverflow.com
     * 
     * @param smooshed
     * @return
     */
    @Deprecated
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
                ////System.out.println("Put Component ("+name+") in parent map");
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
        component.setComponentName(myName);
        for (int i = 0; i < myLabels.size(); i++) {
            Label lbl = myLabels.get(i);
            String attributeName = lbl.getText();
            Node input = lbl.getLabelFor();
            if (input.getClass()==TextField.class) {
                String value = ((TextField) input).getText();
                component.addField(attributeName, value);
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
                component.addField(attributeName, list);
            } else if (input.getClass()==ComboBox.class) {
                ComboBox<String> combo = (ComboBox<String>) input;
                component.addField(attributeName, combo.getValue());
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
