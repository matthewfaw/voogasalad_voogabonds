package authoring.view.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import authoring.controller.Router;
import authoring.model.EntityList;
import authoring.view.menus.FileMenuBar;
import authoring.view.side_panel.MapTab;
import authoring.view.tabs.EntityTab;
import authoring.view.tabs.LevelTab;
import authoring.view.tabs.RulesTab;
import authoring.view.tabs.WaveTab;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import main.MainInitializer;

public class AuthorDisplay {
    public static final String AUTHORING_TITLE = "Authoring Environment";
    
    private BorderPane root;
    private TabPane tabPane;
    private FileMenuBar topMenuBar;
    private Router r;
    private EntityList el;
    private Scene scene;
    
    public AuthorDisplay(MainInitializer mainInit, BorderPane pane, Scene scn) {
        // set title
        mainInit.setTitle(AUTHORING_TITLE);
        this.scene = scn;
        
        // Set up BorderPane
        root = pane;
        // bind to take available space
//        root.prefHeightProperty().bind(pane.heightProperty());
//        root.prefWidthProperty().bind(pane.widthProperty());
        
        // Set up Top Toolbar
        topMenuBar = new FileMenuBar(pane);
        
        // Set up TabPane
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        // Set up Router
        r = new Router();
        
        // Add Tabs
        List<Tab> tabs = getTabs();
        for (Tab tab : tabs) {
            tabPane.getTabs().add(tab);
        }
        
        // Set regions of BorderPane
        root.setTop(topMenuBar);
        root.setCenter(tabPane);
    }
    
    public BorderPane getContent() {
        return root;
    }
    
    private List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();
        
        // Define Tabs
        MapTab mapTab = new MapTab(tabPane, scene, r.getMapDataContainer());
        EntityTab entityTab = new EntityTab(r.getEntityDataContainer());
        RulesTab rulesTab = new RulesTab("Rules");
        WaveTab waveTab = new WaveTab("Waves", r.getWaveDataContainer());
        LevelTab levelTab = new LevelTab("Levels");
        r.link(entityTab, levelTab, waveTab);
        // Add Tabs to list
        tabs.add(mapTab);
        tabs.add(entityTab);
        tabs.add(rulesTab);
        tabs.add(waveTab);
        tabs.add(levelTab);
        
        // Return list
        return tabs;
    }

}
