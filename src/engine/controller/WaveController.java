package engine.controller;

import engine.IObserver;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;

/**
 * A class to handle which wave the game is currently in,
 * and the game time of the simulation
 * @author matthewfaw
 *
 */
public class WaveController implements IObserver<TimelineController>{

	//*******************Observer interface***************//
	@Override
	public void update(TimelineController aChangedObject) {
		//TODO: check if we should spawn a new enemy
		//TODO: check if we should transition to the next wave
	}
}