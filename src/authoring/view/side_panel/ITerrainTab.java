package authoring.view.side_panel;

import javafx.collections.ObservableList;

public interface ITerrainTab {
    
    /**
     * @return an ObservableList of possible terrains.
     */
    public ObservableList<String> getTerrains();

}
