package authoring.view.tabs;

import java.util.ArrayList;

import authoring.controller.PlayerDataContainer;
import authoring.model.PlayerData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RulesTab extends AuthoringTab implements ISubmittable {
	
	private ObservableList<String> myWins = FXCollections.observableList(new ArrayList<String>());
	private ObservableList<String> myLosses = FXCollections.observableList(new ArrayList<String>());
	private PlayerDataContainer myContainer;
	private TextField myLivesField;
	private TextField myCashField;
	private ComboBox<String> myWinBox;
	private ComboBox<String> myLoseBox;
	
	public RulesTab(String text, PlayerDataContainer container){
		super(text);
		myWins.addAll(getResources().getString("Never Win"));
		myLosses.addAll(getResources().getString("Never Lose"), getResources().getString("HP Lose"),
				getResources().getString("No Money Lose"));
		myContainer = container;
		VBox menu = setUpMenu();
		menu.setId("vbox");
		setContent(menu);
	}

	private VBox setUpMenu() {
		VBox v = new VBox();
		myLivesField = setUpTextInputWithLabel(getResources().getString("EnterLives"), 
				getResources().getString("DefaultLives"), v);
		myCashField = setUpTextInputWithLabel(getResources().getString("EnterCash"),
				getResources().getString("DefaultCash"), v);
		myWinBox = setUpStringComboBoxWithLabel(getResources().getString("EnterWin"), null, 
				myWins, v);
		myLoseBox = setUpStringComboBoxWithLabel(getResources().getString("EnterLose"), null,
				myLosses, v);
		Button applyChanges = setUpSubmitButton();
		v.getChildren().add(applyChanges);
		return v;
	}

	public Button setUpSubmitButton() {
		Button applyChanges = new Button(getResources().getString("ApplyChanges"));
		applyChanges.setId("button");
		applyChanges.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				PlayerData player = new PlayerData();
				try {
					player.setStartingLives(myLivesField.getText());
					player.setStartingCash(myCashField.getText());
					player.setWinStrategyName(myWinBox.getValue());
					player.setLoseStrategyName(myLoseBox.getValue());
				} catch(Exception e){
					showError(e.getMessage());
				}
				myContainer.createPlayerData(player);
			}
		});
		return applyChanges;
	}
}
