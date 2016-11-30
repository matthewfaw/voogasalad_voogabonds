package authoring.view.input_menus;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import authoring.controller.IDataController;
import authoring.controller.TowerDataController;
import authoring.model.IReadableData;
import authoring.view.side_panel.AbstractInfoTab;
import authoring.view.side_panel.TowerTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Niklas Sjoquist
 * 
 * Defines a general Menu class. Allows new types of IMenus to be extended easily.
 *
 */
public abstract class AbstractMenu implements IMenu {
    private ResourceBundle myResources;
    private AbstractInfoTab myTab;
    //private MenuHelper myHelper;
    private Stage myWindow;
    private int width, height;
    private List<TextField> myTextFields;
    private List<Button> myFileBrowsers;
    private List<ComboBox<String>> myComboBoxes;
    private List<MenuButton> myMenuButtons;
    
    protected AbstractMenu(ResourceBundle resources, AbstractInfoTab tab) {
        myResources = resources;
        myTab = tab;
        //myHelper = new MenuHelper(myResources);
        width = defineWidth();
        height = defineHeight();
        myTextFields = defineTextFields();
        myFileBrowsers = defineBrowserButtons();
        myComboBoxes = defineComboBoxes();
        myMenuButtons = defineMenuButtons();
    }
    
    public void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @Override
    public void createObjectMenu (IReadableData objectData) {
        checkForErrors();
        myWindow = new Stage();
        myWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        this.setUpNewMenuScreen(root, objectData);
        Scene scene = new Scene(root, width, height);
        this.setTitle();
        myWindow.setScene(scene);
        myWindow.show();
    }
    
    private void setUpNewMenuScreen(VBox root, IReadableData objectData) {
        if (objectData == null) {
            setUpNewCreateScreen(root);
        } else {
            setUpNewUpdateScreen(root, objectData);
        }
    }
    
    private void setTitle() {
        myWindow.setTitle(this.getTitle());
    }
    
    // Protected Helper Methods \\
    
    protected AbstractInfoTab getTab() {
        return myTab;
    }
    
    /**
     * @return ResourceBundle associated with this menu
     */
    protected ResourceBundle getResources() {
        return myResources;
    }
    
//    /**
//     * Note: this method is public in order to allow access by Tabs
//     * 
//     * @return MenuHelper to help show error messages, etc.
//     */
//    public MenuHelper getHelper() {
//        return myHelper;
//    }
    
    /**
     * @return Stage window
     */
    protected Stage getWindow() {
        return myWindow;
    }
    
    protected List<TextField> getInputFields() {
        return myTextFields;
    }
    
    protected List<ComboBox<String>> getComboBoxes() {
        return myComboBoxes;
    }
    
    protected List<MenuButton> getMenuButtons() {
        return myMenuButtons;
    }
    
    /**
     * Helper method to create a basic text label.
     * 
     * Simply packages the string input into a Text object.
     * 
     * @param label
     * @return
     */
    protected Text setUpLabel(String label) {
        return new Text(label);
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
    protected TextField setUpTextInput(String value) {
        TextField textField = new TextField(value);
        return textField;
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
    protected ComboBox<String> setUpComboBox(ObservableList<String> choices, String value) {
        ComboBox<String> cb = new ComboBox<String>(choices);
        cb.setValue(value);
        return cb;
    }

    /**
     * Helper method to create a FileChooser button.
     * 
     * This method takes in a TextField to edit, and returns a FileChooser.
     * 
     * @param field - text field to edit with filepath
     * @param extensionName - name of expected file extension (e.g., WAV, PNG, etc.)
     * @param extension - format of expected file extension (e.g., '*.wav', '*.png', etc.)
     * @return
     */
    protected Button setUpBrowseButton(TextField field, String extensionName, String extension) {
        Button browseButton = new Button(myResources.getString("Browse"));
        browseButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extensionName, extension));
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null){
                	int placeHolder = file.getPath().indexOf("src");
                	String simplePath = file.getPath().substring(placeHolder, file.getPath().length());
                    field.setText(simplePath);
                }
            }
        });
        return browseButton;
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
    protected MenuButton setUpMenuButton(String value, ObservableList<String> options) {
        MenuButton menuBtn = new MenuButton(value);
        for (String terrain : options){ //changed from myTab.getTerrains() -> options
                CheckBox checkBox = new CheckBox(terrain);
                //checkBox.setSelected(options.contains(terrain)); Not sure why this was included
                CustomMenuItem custom = new CustomMenuItem(checkBox);
                menuBtn.getItems().add(custom);
        }
        return menuBtn;
    }
    
    // Abstract Methods \\
    
    /**
     * This method must be defined to give the menu a width, default is 0.
     * 
     * @return width of menu
     */
    protected abstract int defineWidth();
    
    /**
     * This method must be defined to give the menu a height, default is 0.
     * 
     * @return height of menu
     */
    protected abstract int defineHeight();
    
    /**
     * This method sets up a new menu screen to create a new game object defined by instance variable lists myTextFields, myComboBox, and myMenuButtons.
     * @param resources - Contains default value of TextFields.
     */
    protected abstract void setUpNewCreateScreen(VBox root);
    
    /**
     * This method sets up a new menu screen to update an existing game object defined by instance variables myTextFields, myComboBox, and myMenuButtons.
     * @param objectData - represents the game object
     */
    protected abstract void setUpNewUpdateScreen(VBox root, IReadableData objectData);
    
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
     * @return an ordered list of FileChooser buttons which are needed to create menus for this type of object (may be null if menu has no browsers)
     */
    protected abstract List<Button> defineBrowserButtons();
    
    /**
     * Checks for errors and uses the given MenuHelper to display an error message to the user.
     * This method should use the MenuHelper.showError method, and should also include a return statement if the menu pop-up window should be suppressed. 
     * May not show any errors.
     * @param helper
     */
    protected abstract void checkForErrors();

}
