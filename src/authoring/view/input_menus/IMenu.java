package authoring.view.input_menus;

import java.util.List;

/**
 * @author Niklas Sjoquist
 * 
 * An IMenu has the ability to generate menus to create new game objects
 *
 */
public interface IMenu {
    
    /**
     * Generates a new window which contains a form to input parameters for and create the new object
     */
    public void createObjectMenu(boolean isNewObject, List<String> inputFieldValues);

}
