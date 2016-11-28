package authoring.view.input_menus;

import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.IDataController;
import authoring.controller.TowerDataController;
import authoring.view.side_panel.ITerrainTab;
import authoring.view.side_panel.TowerTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Niklas Sjoquist
 * 
 * Allows new types of IMenus to be extended easily.
 *
 */
public abstract class AbstractMenu implements IMenu {
    public static final int SIZE = 500;
    
    private ResourceBundle myResources;
    private ITerrainTab myTab;
    private MenuHelper myHelper;
    private Stage myWindow;
    private List<TextField> myTextFields;
    private List<ComboBox<String>> myComboBoxes;
    private List<MenuButton> myMenuButtons;
    
    protected AbstractMenu(ResourceBundle resources, ITerrainTab tab) {
        myResources = resources;
        myTab = tab;
        myHelper = new MenuHelper(myResources);
        myTextFields = defineTextFields();
        myComboBoxes = defineComboBoxes();
        myMenuButtons = defineMenuButtons();
    }
    
    @Override
    public void createObjectMenu (boolean isNewObject, List<String> inputFieldValues) {
        checkForErrors(myHelper);
        myWindow = new Stage();
        myWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        this.setUpNewMenuScreen(isNewObject, inputFieldValues);
        Scene scene = new Scene(root, SIZE, SIZE);
        this.setTitle();
        myWindow.setScene(scene);
        myWindow.show();
    }
    
    private void setUpNewMenuScreen(boolean isNewObject, List<String> inputFieldValues) {
        if (isNewObject) {
            setUpNewCreateScreen(myResources);
        } else {
            setUpNewUpdateScreen(inputFieldValues);
        }
    }
    
    private void setTitle() {
        myWindow.setTitle(this.getTitle());
    }
    
    // Protected Helper Methods \\
    
    protected ResourceBundle getResources() {
        return myResources;
    }
    
    protected Stage getStage() {
        return myWindow;
    }
    
    protected List<TextField> getInputFields() {
        return myTextFields;
    }
    
    protected List<ComboBox<String>> getComboBox() {
        return myComboBoxes;
    }
    
    protected List<MenuButton> getMenuButtons() {
        return myMenuButtons;
    }
    
    /**
     * Helper method to create a ComboBox. 
     * 
     * This method adds a ComboBox with a given label to the Menu's view and returns the ComboBox, if the user wants a reference
     * 
     * @param root
     * @param label
     * @param choices - the list of choices of the ComboBox
     * @param value - the default value of the ComboBox
     * @return ComboBox
     */
    protected ComboBox<String> setUpComboBox(VBox root, String label, ObservableList<String> choices, String value) {
        Text labelText = new Text(label);
        ComboBox<String> cb = new ComboBox<String>(choices);
        cb.setValue(value);
        root.getChildren().addAll(labelText, cb);
        return cb;
    }
    
    /**
     * Helper method to create a FileChooser button. 
     * 
     * This method adds a FileChooser button to the Menu's view, and returns a reference to the TextField. 
     * 
     * @param root
     * @param label
     * @param value - default value
     * @param extensionName - name of expected file extension (e.g., WAV, PNG, etc.) 
     * @param extension - format of expected file extension (e.g., '*.wav', '*.png', etc.)
     */
    protected TextField setUpBrowseButton(VBox root, String label, String value, String extensionName, String extension) {
        TextField path = myHelper.setUpBasicUserInput(root, label, value);
        myHelper.setUpBrowseButton(root, path, extensionName, extension);
        return path;
    }
    
    /**
     * Helper method to create a basic input field.
     * 
     * This adds a Text label and TextField area to the Menu's view, and returns a reference to the TextField input area.
     * 
     * @param root
     * @param label - string representation of desired label/prompt to indicate to the user what to enter
     * @param value - initial value of the TextField input area
     * @return
     */
    protected TextField setUpTextInput(VBox root, String label, String value) {
        Text text = new Text(label);
        TextField textField = new TextField(value);
        root.getChildren().addAll(text, textField);
        return textField;
    }
    
    /**
     * Helper method to create a CheckBox dropdown menu.
     * 
     * This adds a dropdown box to the Menu's view, and returns a reference to the MenuButton.
     * 
     * @param root
     * @param value - default value of check box
     * @param options - possible values of the check box
     * @return
     */
    protected MenuButton setUpMenuButton(VBox root, String value, ObservableList<String> options) {
        MenuButton menuBtn = new MenuButton(value);
        for (String terrain : myTab.getTerrains()){
                CheckBox checkBox = new CheckBox(terrain);
                checkBox.setSelected(options.contains(terrain));
                CustomMenuItem custom = new CustomMenuItem(checkBox);
                menuBtn.getItems().add(custom);
        }
        root.getChildren().add(menuBtn);
        return menuBtn;
    }
    
    // Abstract Methods \\
    
    /**
     * This method sets up a new menu screen to create a new game object defined by instance variable lists myTextFields, myComboBox, and myMenuButtons.
     * @param resources - Contains default value of TextFields.
     */
    protected abstract void setUpNewCreateScreen(ResourceBundle resources);
    
    /**
     * This method sets up a new menu screen to update an existing game object defined by instance variables myTextFields, myComboBox, and myMenuButtons.
     * @param objectData - Default value of text fields: an ordered list of parameters which represents the IReadableData of the game object
     */
    protected abstract void setUpNewUpdateScreen(List<String> objectData);
    
    /**
     * @return the title of the menu window
     */
    protected abstract String getTitle();
    
    /**
     * @return an ordered list of TextFields which are needed to create menus for this type of object (may be null if menu has no text inputs)
     */
    protected abstract List<TextField> defineTextFields();
    
    /**
     * @return an ordered list of ComboBoxes which are needed to create menus for this type of object (may be null if menu has no combo boxes)
     */
    protected abstract List<ComboBox<String>> defineComboBoxes();
    
    /**
     * @return an ordered list of MenuButtons which are needed to create menus for this type of object (may be null if menu has no check boxes)
     */
    protected abstract List<MenuButton> defineMenuButtons();
    
    /**
     * Checks for errors and uses the given MenuHelper to display an error message to the user.
     * This method should use the MenuHelper.showError method, and should also include a return statement if the menu pop-up window should be suppressed. 
     * May not show any errors.
     * @param helper
     */
    protected abstract void checkForErrors(MenuHelper helper);

}
