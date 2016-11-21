package authoring.controller;
import javafx.collections.MapChangeListener;
import java.util.List;

public interface IObservableController{
	public void addControllerListener(MapChangeListener<String, Object> listener);
}
