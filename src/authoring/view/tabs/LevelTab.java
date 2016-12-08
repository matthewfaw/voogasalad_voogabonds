package authoring.view.tabs;

import java.util.ArrayList;

import authoring.controller.Container;
import authoring.controller.WaveDataContainer;
import engine.IObservable;
import engine.IObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LevelTab extends ListTab<String> implements IObserver<Container>{

        private static final int COLS = 2;
    
	private ArrayList<String> myWaves = new ArrayList<String>();
	
	public LevelTab(String text) {
		super(text, COLS);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EventHandler<ActionEvent> handleAddNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Container c) {
		myWaves.clear();
		if (c instanceof WaveDataContainer){
			for (String s: ((WaveDataContainer) c).getWaveMap().keySet()){
				myWaves.add(s);
			}
		}
	}
}
