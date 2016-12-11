package gamePlayerView.GUIPieces.InfoBoxes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
public class Controls {
    private ResourceBundle defaultControls;
    private Enumeration<String> controlFunctions;
    private ArrayList<String> controls;
    private ArrayList<String> functions;
    
    public Controls(){
        defaultControls =  ResourceBundle.getBundle("resources/defaultcontrols");
        controlFunctions = defaultControls.getKeys();
        controls = new ArrayList<String>();
        functions = new ArrayList<String>();
        while(controlFunctions.hasMoreElements()){
            String str = controlFunctions.nextElement();
            functions.add(str);
            controls.add(defaultControls.getString(str));
        }
    }
    
    public void setDefaults(){
        while(controlFunctions.hasMoreElements()){
            String str = controlFunctions.nextElement();
            controls.add(defaultControls.getString(str));
        }
    }
    
    public ArrayList<String> getControls(){
        return controls;
    }
    
    public ArrayList<String> getFunctions(){
        return functions;
    }
        
    public void setControls(ArrayList<String> newControls){
        controls = newControls;
    }
}