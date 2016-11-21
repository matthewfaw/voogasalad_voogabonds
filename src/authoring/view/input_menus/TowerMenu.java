package authoring.view.input_menus;

import java.util.ResourceBundle;
import authoring.controller.TowerDataController;
import authoring.view.objects.FrontEndEnemy;
import authoring.view.objects.FrontEndTower;
import authoring.view.side_panel.TowerTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Niklas Sjoquist
 *
 */
public class TowerMenu {
    private static final int SIZE = 500;
    
    private ResourceBundle myResources;
    private TowerTab myTab;
    private TowerDataController myController;
    private MenuHelper myHelper;
    private Stage myTowerWindow;
    private TextField myNameField;
    private TextField myTypeField;
    private TextField myHealthField;
    private TextField myCostField;
    private TextField mySizeField;
    private TextField myImageField;
    private TextField mySoundField;
    
    public TowerMenu(ResourceBundle resources, TowerTab tab) {
        myResources = resources;
        myTab = tab;
        myController = new TowerDataController();
        myHelper = new MenuHelper(myResources);
    }
    
    public void createTowerMenu(String nameVal, String typeVal, String healthVal, String costVal, String sizeVal, String imageVal, String soundVal) {
        myTowerWindow = new Stage();
        myTowerWindow.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        setUpTowerScreen(root, nameVal, typeVal, healthVal, costVal, sizeVal, imageVal, soundVal);
        Scene scene = new Scene(root, SIZE, SIZE);
        myTowerWindow.setTitle(myResources.getString("AddTower"));
        myTowerWindow.setScene(scene);
        myTowerWindow.show();
    }
    
    private void setUpTowerScreen(VBox root, String nameVal, String typeVal, String healthVal, String costVal, String sizeVal, String imageVal, String soundVal) {
        myNameField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterName"), nameVal);
        myTypeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterType"), typeVal);
        myHealthField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterHealth"), healthVal);
        myCostField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterCost"), costVal);
        mySizeField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSize"), sizeVal);
        setUpImage(root, imageVal);
        setUpSound(root, soundVal);
        setUpWeapon(root);
        setUpFinishButton(root);
    }
    
    private void setUpImage(VBox root, String value) {
        myImageField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterImage"), value);
        myHelper.setUpBrowseButton(root, myImageField, "PNG", "*.png");
    }
    
    private void setUpSound(VBox root, String value) {
        if (value == null)
                mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), 
                                myResources.getString("DefaultSound"));
        else
                mySoundField = myHelper.setUpBasicUserInput(root, myResources.getString("EnterSound"), value);
        myHelper.setUpBrowseButton(root, mySoundField, "WAV", "*.wav");
    }
    
    private void setUpWeapon(VBox root) {
        Text weaponText = new Text(myResources.getString("SelectWeapon"));
        ComboBox<String> weaponBox = new ComboBox<String>();
        root.getChildren().addAll(weaponText, weaponBox);
    }
    
    private void setUpFinishButton(VBox root){
        Button finishButton = new Button(myResources.getString("Finish"));
        finishButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event){
                        String name = myNameField.getCharacters().toString();
                        String type = myTypeField.getCharacters().toString();
                        double health = 0;
                        double cost = 0;
                        double size = 0;
                        try {
                                health = Double.parseDouble(myHealthField.getCharacters().toString());
                                cost = Double.parseDouble(myCostField.getCharacters().toString());
                                size = Double.parseDouble(mySizeField.getCharacters().toString());
                        } catch(Exception e) {
                                myTab.showError(myResources.getString("BadDoubleInput"));
                                return;
                        }
                        String image = myImageField.getCharacters().toString();
                        if (!(image.substring(image.length() - 4).equals(".png"))){
                                myTab.showError(myResources.getString("NoImageInput"));
                                return;
                        }
                        FrontEndTower tower = new FrontEndTower(name, type, health, cost, size, image);
                        String sound = mySoundField.getCharacters().toString();
                        if (sound.substring(sound.length() - 4).equals(".wav")){
                                tower.setSound(sound);
                        }
                        myTab.removeButtonDuplicates(name);
                        myTab.addButtonToDisplay(name);
                        myTab.addTower(name);
                        // TODO: set up weapon for enemy
                        myController.createTowerData(tower);
                        myTowerWindow.close();
                }
        });
        root.getChildren().add(finishButton);
    }
    

}
