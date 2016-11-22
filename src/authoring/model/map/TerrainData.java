package authoring.model.map;
import utility.Point;

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
	
	public Point getLoc(){
		return loc;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (!(o instanceof TerrainData)){
			return false;
		}
		TerrainData td = (TerrainData) o;
		return td.type.equals(this.type) && (td.getLoc().getX() == loc.getX()) && (td.getLoc().getY() == loc.getY());
	}
}
