package authoring.model.map;
import utility.Point;

import authoring.model.IReadableData;

/**
 * type: the 'type' of terrain this represents, e.g. 'water', 'air', 'ground'
 * loc: Point with x and y coordinates 
 * @author philipfoo
 *
 */
public class TerrainData implements IReadableData {
	private String type;
	private String color;
	private Point loc;
	
	public TerrainData(String type, String color, int x, int y){
		this.type = type;
		this.color = color;
		this.loc = new Point(x, y);
	}
	
	public TerrainData(String type, int x, int y){
		this(type, null, x, y);
	}
	
	@Override
	public String getName()
	{
		return type;
	}
	
	public String getColor()
	{
		return color;
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
