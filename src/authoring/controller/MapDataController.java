package authoring.controller;
import authoring.model.map.*;
import javafx.collections.SetChangeListener;
import javafx.collections.MapChangeListener;

import utility.Point;
import java.util.Set;
import java.util.ArrayList;

/**
 * MapDataController doesn't expect a FrontEndMap like the rest of the controller classes.
 * Instead set the x and y dimensions, and then add spawn/sink points through the below functions.
 * @author philipfoo
 *
 */
public class MapDataController {
	private MapData myMapData = new MapData();
	
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
	public void addSpawnPoints(String name, ArrayList<Point> list){
		try{
			myMapData.addSpawnPoints(name, list);
		}catch(Exception e){
			///Call error handler
		}
	}
	public void removeSpawnPoints(String name){
		myMapData.removeSpawnPoints(name);
	}
	public void addSpawnPointListener(MapChangeListener<String, ArrayList<Point>> listener){
		myMapData.addSpawnPointListener(listener);
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
	public void addTerrainListener(SetChangeListener<String> listener){
		myMapData.addValidTerrainListener(listener);
	}
}
