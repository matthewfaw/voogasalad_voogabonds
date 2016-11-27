package engine.controller;

import java.util.ArrayList;
import java.util.List;

import engine.IObservable;
import engine.IObserver;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;

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
		myObservers.forEach(observer -> observer.update(this));
	}
}
