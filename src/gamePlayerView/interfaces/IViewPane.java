package gamePlayerView.interfaces;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Guhan Muruganandam
 */

public interface IViewPane {
	public void setUpPane();
	public void add(Collection<Node> collection);
	public void clear();
	
}
