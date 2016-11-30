package authoring.view.input_menus;

import java.util.List;

/**
 * @author Niklas Sjoquist
 * 
 * An IMenu has the ability to generate menus to create new game objects.
 * 
 * This is used by tabs to generate new menu windows
 *
 */
public interface IMenu {
    
    /**
     * Generates a new window which contains a form to input parameters for and create the new object.
     * 
     * @param isNewObject - indicates whether the menu is generating a new object or not
     * @param inputFieldValues - default values for text inputs of menu
     */
    public void createObjectMenu(boolean isNewObject, List<String> inputFieldValues);

}
