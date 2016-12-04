package engine.controller.timeline;

import java.util.ArrayList;
import java.util.List;

import engine.IObservable;
import engine.IObserver;
import gamePlayerView.Resources;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * A class used to encapsulate the Timeline object
 * Any object can observe the timeline, and perform it's updates accordingly
 * 
 * @author matthewfaw
 *
 */
public class TimelineController implements IObservable<TimelineController> {
	private Timeline myTimeline;
	private List<IObserver<TimelineController>> myObservers;

	public TimelineController()
	{
		myTimeline = new Timeline();
		myObservers = new ArrayList<IObserver<TimelineController>>();
		
		//TODO: maybe change this to another location?
		myTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(Resources.MILLISECOND_DELAY), e -> notifyObservers()));
	}
	
	/**
	 * A method to start the timeline.  If the timeline is already
	 * running, this method does nothing
	 */
	public void start()
	{
		if (!myTimeline.getStatus().equals(Status.RUNNING)) {
			myTimeline.play();
		}
	}
	
	/**
	 * A method to pause the timeline, if it is running
	 * If the timeline is not running, then this method does nothing
	 */
	public void pause()
	{
		if (myTimeline.getStatus().equals(Status.RUNNING)) {
			myTimeline.pause();
		}
	}
	
	/**
	 * A method to get the total number of milliseconds elapsed by the timeline
	 * @return
	 */
	public double getTotalTimeElapsed()
	{
		return myTimeline.getTotalDuration().toMillis();
	}
	
	//*********************Observable interface******************//
	@Override
	public void attach(IObserver<TimelineController> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<TimelineController> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		//IMPORTANT: cannot write a foreach loop here because of JavaFX concurrency issues
		for (int i=0; i<myObservers.size(); ++i) {
			myObservers.get(i).update(this);
		}
	}
}
