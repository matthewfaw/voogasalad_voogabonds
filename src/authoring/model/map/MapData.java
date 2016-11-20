package authoring.model.map;
import java.awt.Point;
import java.util.ArrayList;

public class MapData {
	private int numXCells;
	private int numYCells;
	private ArrayList<TerrainData> terrainList;
	
	public void setNumXCells(int x) throws Exception{
		if (x <= 0){
			throw new Exception("The map must be wider than 0 cells.");
		}
		this.numXCells = x;
	}
	
	public void setNumYCells(int y) throws Exception{
		if (y <= 0){
			throw new Exception("The map must be taller than 0 cells.");
		}
	}
	
	public void addTerrainData(TerrainData terrain) throws Exception{
		if (terrain.getLocation().getX() < 0 || terrain.getLocation().getX() > numXCells){
			throw new Exception("X location of terrain is not valid.");
		}
		if (terrain.getLocation().getY() < 0 || terrain.getLocation().getY() > numYCells){
			throw new Exception("Y location of terrain is not valid.");
		}
		terrainList.add(terrain);
	}
}
