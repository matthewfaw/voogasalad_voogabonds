package authoring.model.map;
import java.awt.Point;
import java.util.ArrayList;

public class MapData {
	private int numXCells;
	private int numYCells;
	private ArrayList<Point> spawnPoints;
	private ArrayList<Point> sinkPoints;
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
	
	public void addSpawnPoint(Point newSpawnPoint) throws Exception{
		validatePoint(newSpawnPoint, "spawn");
		spawnPoints.add(newSpawnPoint);
	}
	
	public void addSinkPoint(Point newSinkPoint) throws Exception{
		validatePoint(newSinkPoint, "sink");
		sinkPoints.add(newSinkPoint);
	}
	
	public void addTerrainData(TerrainData terrain) throws Exception{
		validatePoint(terrain.getLocation(), "terrain");
		terrainList.add(terrain);
	}
	
	private void validatePoint(Point p, String type) throws Exception{
		if (p.getX() >= numXCells || p.getX() < 0){
			throw new Exception("X location of " + type + " point not valid.");
		}
		if (p.getY() >= numYCells || p.getY() < 0){
			throw new Exception("Y location of " + type + " point not valid.");
		}
	}
}
