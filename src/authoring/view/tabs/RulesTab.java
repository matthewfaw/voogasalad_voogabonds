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

public class RulesTab extends AuthoringTab {
	
	private ObservableList<String> myWins = FXCollections.observableList(new ArrayList<String>());
	private ObservableList<String> myLosses = FXCollections.observableList(new ArrayList<String>());
	private PlayerDataContainer myContainer;
	
	public RulesTab(String text, PlayerDataContainer container){
		super(text);
		myWins.addAll(getResources().getString("NeverWin"));
		myLosses.addAll(getResources().getString("NeverLose"));
		myContainer = container;
		VBox menu = setUpMenu();
		setContent(menu);
	}

	private VBox setUpMenu() {
		VBox v = new VBox();
		Label livesLabel = setUpLabel(getResources().getString("EnterLives"));
		TextField livesField = setUpTextInput(getResources().getString("DefaultLives"));
		livesLabel.setLabelFor(livesField);
		Label cashLabel = setUpLabel(getResources().getString("EnterCash"));
		TextField cashField = setUpTextInput(getResources().getString("DefaultCash"));
		cashLabel.setLabelFor(cashField);
		Label winLabel = setUpLabel(getResources().getString("EnterWin"));
		ComboBox<String> winBox = setUpStringComboBox(myWins, null);
		winLabel.setLabelFor(winBox);
		Label loseLabel = setUpLabel(getResources().getString("EnterLose"));
		ComboBox<String> loseBox = setUpStringComboBox(myLosses, null);
		loseLabel.setLabelFor(loseBox);
		Button applyChanges = setUpApplyChangesButton(livesField, cashField, winBox, loseBox);
		v.getChildren().addAll(livesLabel, livesField, cashLabel, cashField, winLabel, winBox, loseLabel,
				loseBox, applyChanges);
		return v;
	}

	private Button setUpApplyChangesButton(TextField livesField, TextField cashField, ComboBox<String> winBox,
			ComboBox<String> loseBox) {
		Button applyChanges = new Button(getResources().getString("ApplyChanges"));
		applyChanges.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				PlayerData player = new PlayerData();
				try {
					player.setStartingLives(livesField.getText());
					player.setStartingCash(cashField.getText());
					player.setWinStrategyName(winBox.getValue());
					player.setLoseStrategyName(loseBox.getValue());
				} catch(Exception e){
					showError(e.getMessage());
				}
				myContainer.createPlayerData(player);
			}
		});
		return applyChanges;
	}
}
