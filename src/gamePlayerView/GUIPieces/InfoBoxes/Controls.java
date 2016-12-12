package gamePlayerView.GUIPieces.InfoBoxes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
public class Controls {
    private ResourceBundle defaultControls;
    private Enumeration<String> controlFunctions;
    private ArrayList<String> controls;
    private ArrayList<String> functions;
    private Map<String, String> mappings;
    
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
        mappings = new HashMap<String, String>();
        makeMap();
    }
    
    public void setDefaults(){
        while(controlFunctions.hasMoreElements()){
            String str = controlFunctions.nextElement();
            controls.add(defaultControls.getString(str));
        }
        
    }
    
    public void setControlFor(String function, String newControl){
//        System.out.println(function + ": ");
//        System.out.println(mappings.get(function));
        for(int i = 0; i < controls.size(); i++){
            if(mappings.get(function) == controls.get(i)){
                controls.remove(i);
                controls.add(i, newControl);
//                System.out.println("Replaced");
//                System.out.println("controls size: " + controls.size());
            }
        }
        mappings.replace(function, mappings.get(function), newControl);
        
    }
    
    private void makeMap(){
        for(int i = 0; i < functions.size(); i++){
        mappings.put(functions.get(i), controls.get(i));
        }
    }
    
    public String getControlFor(String function){
        return mappings.get(function);
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