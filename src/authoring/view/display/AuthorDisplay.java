package authoring.view.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authoring.controller.MapDataContainer;
import authoring.controller.Router;
import authoring.model.EntityList;
import authoring.view.menus.FileMenuBar;
import authoring.view.menus.TopMenuBar;
import authoring.view.tabs.LevelTab;
import authoring.view.tabs.MapTab;
import authoring.view.tabs.RulesTab;
import authoring.view.tabs.WaveTab;
import authoring.view.tabs.entities.EntityTab;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import main.MainInitializer;
import utility.ResouceAccess;

public class AuthorDisplay {
    public static final String AUTHORING_TITLE = "Authoring Environment";
    
    private BorderPane root;
    private TabPane tabPane;
    private TopMenuBar topMenuBar;
    private Router r;
    private EntityList el;
    private Scene scene;
    private Router router;
    
    public AuthorDisplay(BorderPane pane, Scene scn, Router r) {
        // set title
//        scn.setTitle(AUTHORING_TITLE);
        this.scene = scn;
        this.router = r;
        // Set up BorderPane
        root = pane;
        // bind to take available space
//        root.prefHeightProperty().bind(pane.heightProperty());
//        root.prefWidthProperty().bind(pane.widthProperty());
        
        // Set up Top Toolbar
        topMenuBar = new TopMenuBar(pane, router);
        
        // Set up TabPane
        tabPane = new TabPane();
        tabPane.setId("background");
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        // Add Tabs
        try {
        	List<Tab> tabs = getTabs();
        	
            for (int i = 0; i < tabs.size(); i++) {
                //System.out.println("Tab "+i+" Added");
                tabPane.getTabs().add(tabs.get(i));
                tabs.get(i).setId("tab");
            }
        } catch (ClassNotFoundException e) {
        	throw new UnsupportedOperationException(ResouceAccess.getError("NoTabs"), e);
        }

        // Set regions of BorderPane
//        root.setTop(topMenuBar);
        root.setCenter(tabPane);
    }
    
    public BorderPane getContent() {
        return root;
    }
    
    private List<Tab> getTabs() throws ClassNotFoundException {
        List<Tab> tabs = new ArrayList<>();
        
        // Define Tabs
        MapDataContainer mapData = r.getMapDataContainer();
        MapTab mapTab = new MapTab(tabPane, scene, mapData);
        EntityTab entityTab = new EntityTab(r.getEntityDataContainer());
        RulesTab rulesTab = new RulesTab("Rules", r.getPlayerDataContainer());
        WaveTab waveTab = new WaveTab("Waves", r.getWaveDataContainer());
        LevelTab levelTab = new LevelTab("Levels", r.getLevelDataContainer());
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
    
    public Router getRouter(){
    	return r;
    }

}