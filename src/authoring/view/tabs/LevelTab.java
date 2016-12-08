package authoring.view.tabs;

import java.util.ArrayList;
import java.util.List;

import authoring.controller.Container;
import authoring.controller.LevelDataContainer;
import authoring.controller.WaveDataContainer;
import authoring.model.LevelData;
import authoring.model.WaveData;
import engine.IObservable;
import engine.IObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LevelTab extends ListTab<String> implements IObserver<Container>{

    private static final int COLS = 2;
    
	private ArrayList<WaveData> myWaves = new ArrayList<WaveData>();
	private LevelDataContainer myContainer;
	
	public LevelTab(String text, LevelDataContainer container) {
		super(text, COLS);
		myContainer = container;
	}

	@Override
	protected EventHandler<ActionEvent> handleAddNewObject() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				VBox menu = setUpMenu(getResources().getString("DefaultLevelName"), null);
				getTilePane().getChildren().add(menu);
			}
		};
	}
	
	private VBox setUpMenu(String name, List<WaveData> selectedWaves) {
		VBox v = new VBox();
		Label nameLabel = setUpLabel(getResources().getString("EnterName"));
		TextField nameField = setUpTextInput(name);
		nameLabel.setLabelFor(nameField);
		Label wavesLabel = setUpLabel(getResources().getString("SelectWaves"));
		ArrayList<CheckBox> waveChecks = new ArrayList<CheckBox>();
		VBox checkV = new VBox();
		for (WaveData wave: myWaves){
			CheckBox check = new CheckBox(wave.getName());
			if (selectedWaves != null && selectedWaves.contains(wave))
				check.setSelected(true);
			waveChecks.add(check);
			checkV.getChildren().add(check);
		}
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(checkV);
		Button finish = setUpFinishButton(nameField, waveChecks, v);
		v.getChildren().addAll(nameLabel, nameField, wavesLabel, scroll, finish);
		return v;
	}

	private Button setUpFinishButton(TextField nameField, ArrayList<CheckBox> waveChecks, VBox root) {
		Button finish = new Button(getResources().getString("Finish"));
		finish.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				LevelData level = new LevelData();
				try {
					level.setLevelName(nameField.getText());
					for (CheckBox check: waveChecks){
						if (check.isSelected())
							for (WaveData wave: myWaves)
								if (wave.getName().equals(check.getText())){
									level.addWaveDataListToList(wave);
									break;
								}
					}
				} catch(Exception e){
					showError(e.getMessage());
					return;
				}
				myContainer.createNewLevelData(level);
				getTilePane().getChildren().remove(root);
				getObservableList().remove(level.getLevelName());
				getObservableList().add(level.getLevelName());
			}
		});
		return finish;
	}

	@Override
	public void update(Container c) {
		myWaves.clear();
		if (c instanceof WaveDataContainer){
			for (WaveData wave: ((WaveDataContainer) c).getWaveMap().values()){
				myWaves.add(wave);
			}
		}
	}

	@Override
	protected void edit(String name) {
		LevelData level = myContainer.getLevelData(name);
		VBox menu = setUpMenu(level.getLevelName(), level.getWaveDataList());
		getTilePane().getChildren().add(menu);
	}
}
