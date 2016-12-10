package authoring.view.tabs;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import authoring.model.ComponentData;
import authoring.model.EntityData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EditComponentBox extends VBox {
    public static final String DONE = "Done";
    public static final String CANCEL = "Cancel";
    private static final int SPACING = 2;
    
    private List<Label> myLabels;
    private List<TextField> myFields;
    private EditEntityBox parent;
    private EntityTab grandparent;
    private String myEntity;

    public EditComponentBox (EditEntityBox parent, EntityTab grandparent, List<String> attributes) {
        super(SPACING);
        
        this.parent = parent;
        this.grandparent = grandparent;
        
        myLabels = new ArrayList<Label>(attributes.size());
        myFields = new ArrayList<TextField>(attributes.size());
        
        for (int i = 0; i < attributes.size(); i++) {
            Label lbl = new Label(attributes.get(i));
            TextField field = new TextField();
            lbl.setLabelFor(field);
            myLabels.add(lbl);
            myFields.add(field);

            this.getChildren().add(lbl);
            this.getChildren().add(field);
        }
        
        // Set up horizontal buttons
        HBox btns = new HBox(SPACING);
        btns.setPadding(new Insets(SPACING,SPACING,SPACING,SPACING));
        Button done = new Button(DONE);
        done.setOnAction(handleDone());
        Button cancel = new Button(CANCEL);
        cancel.setOnAction(handleCancel());
        btns.getChildren().addAll(done,cancel);

        // Add nodes to Menu Box
        this.getChildren().add(btns);
    }
    
    public EditComponentBox(EditEntityBox parent, EntityTab grandparent, AbstractMap<String,String> retrievedData) {
        super(SPACING);
        
        this.parent = parent;
        this.grandparent = grandparent;
        
        myLabels = new ArrayList<Label>(retrievedData.size());
        myFields = new ArrayList<TextField>(retrievedData.size());
        
        for (String attributeName : retrievedData.keySet()) {
            Label lbl = new Label(attributeName);
            TextField field = new TextField(retrievedData.get(attributeName));
            lbl.setLabelFor(field);
            myLabels.add(lbl);
            myFields.add(field);
            // Add nodes to Menu Box
            this.getChildren().add(lbl);
            this.getChildren().add(field);
        }
        
        HBox btns = getBottomButtons();

        // Add nodes to Menu Box
        this.getChildren().add(btns);
    }
    
    private HBox getBottomButtons() {
        HBox btns = new HBox(SPACING);
        btns.setPadding(new Insets(SPACING,SPACING,SPACING,SPACING));
        Button done = new Button(DONE);
        done.setOnAction(handleDone());
        Button cancel = new Button(CANCEL);
        cancel.setOnAction(handleCancel());
        btns.getChildren().addAll(done,cancel);
        return btns;
    }
    
    private EventHandler<ActionEvent> handleDone() {
        EventHandler<ActionEvent> finish = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                String name = getName();
                ComponentData entity = createDataFromInput();
                System.out.println("Put Component ("+name+") in parent map");
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
            component.addField(myLabels.get(i).getText(), myFields.get(i).getCharacters().toString());
        }
        return component;
    }
    
    private String getName() {
        for (Label lbl : myLabels) {
            String text = lbl.getText();
            System.out.println(text);
            if (text.equals("Name*")) {
                return text;
            }
        }
        return null;
    }

}
