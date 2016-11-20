package authoring.model.map;
import java.awt.Point;

/**
 * type: the 'type' of terrain this represents, e.g. 'water', 'air', 'ground'
 * loc: Point with x and y coordinates 
 * @author philipfoo
 *
 */
public class TerrainData {
	private String type;
	private Point loc;
	
	public TerrainData(String type, int x, int y){
		this.type = type;
		this.loc = new Point(x, y);
	}
	
	public String getType(){
		return type;
	}
	
	public Point getLocation(){
		return loc;
	}
}
