package authoring.controller;
import authoring.model.map.*;
import java.awt.Point;
import java.util.Set;

public class MapDataController {
	private MapData myMapData;
	
	/**
	 * Functions for grid dimensions
	 */
	
	/**
	 * Method used to create the overall size of the grid. If x = 20, y = 20,
	 * then the grid will contain 400 'cells'.
	 * @param x - width of the grid
	 * @param y - height of the grid
	 */
	public void setDimensions(int x, int y){
		try{
			myMapData.setNumXCells(x);
			myMapData.setNumYCells(y);
		}catch(Exception e){
			//Show error to front-end here
		}
	}
	public int getX(){
		return myMapData.getNumXCells();
	}
	public int getY(){
		return myMapData.getNumYCells();
	}
	
	/**
	 * Functions for spawn points
	 */
	public void addSpawnPoint(Point p){
		try{
			myMapData.addSpawnPoint(p);
		}catch(Exception e){
			///Call error handler
		}
	}
	public void removeSpawnPoint(Point p){
		myMapData.removeSpawnPoint(p);
	}
	
	/**
	 * Functions for sink points
	 */
	public void addSinkPoint(Point p){
		try{
			myMapData.addSinkPoint(p);
		}catch(Exception e){
			//Call error handler
		}
	}
	public void removeSinkPoint(Point p){
		myMapData.removeSinkPoint(p);
	}
	
	/**
	 * Functions for the actual grids on the map (terrain)
	 */
	public void addTerrain(TerrainData t){
		try{
			myMapData.addTerrainData(t);
		}catch(Exception e){
			//Call error handler
		}
	}
	public void removeTerrain(TerrainData t){
		myMapData.removeTerrainData(t);
	}
	public Set<TerrainData> getTerrainList(){
		return myMapData.getTerrainList();
	}
}
