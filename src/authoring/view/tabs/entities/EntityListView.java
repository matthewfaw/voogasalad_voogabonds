package authoring.view.tabs.entities;

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
        myName = entityData.getName();
        myImagePath = getImagePath(entityData);
    }
    
    public String getEntityName() {
        return myName;
    }
    
    public ImageView getEntityImageView() {
        Image img = new Image(myImagePath);
        ImageView entityView = new ImageView(img);
        return entityView;
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
