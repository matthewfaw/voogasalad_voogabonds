package authoring.view.tabs.entities;

import java.io.File;
import authoring.model.EntityData;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityListView {
    private static final String PHYSICAL_COMPONENT = "Physical Component";
    private static final String IMAGE_ATTRIBUTE = "Image Path";
    
    private String myName;
    private String myImagePath;
    
    public EntityListView(EntityData entityData) {
        if (entityData == null) {
            System.out.println("EntityData is null");
        }
        myName = entityData.getName();
        myImagePath = getImagePath(entityData);
    }
    
    public String getEntityName() {
        return myName;
    }
    
    public ImageView getEntityImageView() {
        if (myImagePath == null || myImagePath.equals("")) {
            return null;
        }
        System.out.println(myImagePath+"/ \\"+myImagePath.length());
        String relativePath = myImagePath.substring(4);
        Image img = new Image(getClass().getClassLoader().getResourceAsStream(relativePath));
        return new ImageView(img);
    }
    
    private String getImagePath(EntityData entityData) {
        String imagePath = null;
        for (String component : entityData.getComponents().keySet()) {
            if (component.equals(PHYSICAL_COMPONENT)) {
                for (String attribute : entityData.getComponents().get(component).getFields().keySet()) {
                    if (attribute.equals(IMAGE_ATTRIBUTE)) {
                        imagePath = entityData.getComponents().get(component).getFields().get(attribute);
                    }
                }
            }
        }
        return imagePath;
    }

}
