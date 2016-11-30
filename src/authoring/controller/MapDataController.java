package authoring.controller;
import authoring.model.map.*;
import authoring.model.IReadableData;
import engine.IObservable;
import engine.IObserver;
import engine.model.game_environment.terrain.Terrain;
import javafx.collections.SetChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import utility.Point;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

import com.sun.prism.paint.Color;

/**
 * MapDataController doesn't expect a FrontEndMap like the rest of the controller classes.
 * Instead set the x and y dimensions, and then add spawn/sink points through the below functions.
 * @author philipfoo
 *
 */
public class MapDataController extends Controller implements IReadableData, IObservable<Controller>{
	private String myName;
	private int numXCells;
	private int numYCells;
	private int cellSize;
	private ArrayList<IObserver<Controller>> myListeners = new ArrayList<IObserver<Controller>>();
	
	/**
	 * Each group of spawn points will have a name. A map is necessary because
	 * a wave must specify a spawn point which its enemies will start from.
	 */
	private HashMap<String, ArrayList<Point>> spawnPoints;
	
	/**
	 * Set of points that enemies will try to reach.
	 */
	private HashSet<Point> sinkPoints = new HashSet<Point>();
	
	/**
	 * Set defines the geography of the entire map. This set will contain a list of
	 * TerrainDatas, which can be used to populate the entire map. Should contain
	 * a minimum of numXCells x numYCells TerrainData elements.
	 */
	private HashSet<TerrainData> terrainList;
	
	/**
	 * Map of possible terrains that might exist, as created by the user.
	 * The key will be the name of the terrain, and the value will be how its displayed (currently color in HEX).
	 */
	private HashMap<String, String> validTerrain;
	
	public MapDataController(){
		this.spawnPoints = new HashMap<String, ArrayList<Point>>();
		this.sinkPoints = new HashSet<Point>();
		this.terrainList = new HashSet<TerrainData>();
		this.validTerrain = new HashMap<String, String>();
	}
	
	@Override
	public String getName()
	{
		return myName;
	}
	
	/**
	 * MAP DIMENSION FUNCTIONS
	 */
	public void setDimensions(int x, int y){
		try{
			setNumXCells(x);
			setNumYCells(y);
		}catch(Exception e){
			//Show error to front-end here
		}
	}
	
	private void setNumXCells(int x) throws Exception{
		if (x <= 0){
			throw new Exception("The map must be wider than 0 cells.");
		}
		this.numXCells = x;
		System.out.println("Total XCells: " + this.numXCells);
	}
	public int getNumXCells(){
		return numXCells;
	}
	
	private void cellSize(int cellSize) throws Exception{
		if (cellSize<=0){
			throw new Exception("The size of cells must be greater than 0 pixels.");
		}
		this.cellSize = cellSize;
	}
	
	public int getCellSize(){
		return cellSize;
	}
	
	private void setNumYCells(int y) throws Exception{
		if (y <= 0){
			throw new Exception("The map must be taller than 0 cells.");
		}
		this.numYCells = y;
		System.out.println("Total YCells: " + this.numYCells);
	}
	public int getNumYCells(){
		return numYCells;
	}
	
	/**
	 * SPAWN POINT FUNCTIONS
	 */
	public void addSpawnPoints(String name, ArrayList<Point> newSpawnPoints){
		try{
			for (Point p: newSpawnPoints){
				validatePoint(p, "spawn");
			}
		}catch(Exception e){
			//Show error to front-end here
		}
		spawnPoints.put(name, newSpawnPoints);
		notifyObservers();
		System.out.println("Added Spawn Point " + name);
	}
	
	
	public void removeSpawnPoints(String name){
		if (spawnPoints.containsKey(name)){
			spawnPoints.remove(name);
			notifyObservers();
			System.out.println("Removed Spawn Point " + name);
		}
	}
	
	public ArrayList<Point> getSpawnPoints(String name){
		return spawnPoints.get(name);
	}
	
	public HashMap<String, ArrayList<Point>> getSpawnPointMap(){
		return spawnPoints;
	}
	
	/**
	 * SINK POINT FUNCTIONS
	 */
	public void addSinkPoint(Point newSinkPoint){
		try{
			validatePoint(newSinkPoint, "sink");
		}catch(Exception e){
			//Show error to front-end here
		}
		sinkPoints.add(newSinkPoint);
		System.out.println("Added Sink Point " + newSinkPoint.toString());
	}
	
	public void removeSinkPoint(Point p){
		if (sinkPoints.contains(p)){
			sinkPoints.remove(p);
			System.out.println("Removed Sink Point " + p.toString());
		}
	}
	
	public Set<Point> getSinkPoints(){
		return sinkPoints;
	}
	
	/**
	 * TERRAIN DATA FUNCTIONS
	 */
	public void addTerrainData(TerrainData terrain) throws Exception{
		validatePoint(terrain.getLoc(), "terrain");
		terrainList.add(terrain);
	}

	public void removeTerrainData(TerrainData terrain){
		if (terrainList.contains(terrain)){
			terrainList.remove(terrain);
		}
	}
	
	public Set<TerrainData> getTerrainList(){
		return terrainList;
	}
	
	/**
	 * VALID TERRAIN FUNCTIONS
	 */
	public void addValidTerrain(String name, String color) throws Exception{
		if (name == null || name.length() == 0){
			throw new Exception("No terrain specified.");
		}
		validTerrain.put(name, color);
		notifyObservers();
		System.out.println("Added Terrain: " + name);
	}
	public void removeValidTerrain(String name) throws Exception{
		if (validTerrain.containsKey(name)){
			validTerrain.remove(name);
			notifyObservers();
		}
	}
	
	public HashMap<String, String> getValidTerrainMap(){
		return validTerrain;
	}
	

	/**
	 * IObservable functions
	 *
	 */
	public void attach(IObserver<Controller> listener){
		myListeners.add(listener);
	}
	
	public void detach(IObserver<Controller> listener){
		myListeners.remove(listener);
	}
	
	public void notifyObservers(){
		for (IObserver<Controller> listener: myListeners){
			listener.update(this);
		}
	}
	
//	// TODO: make the methods we need more available in the MapData object itself so we don't need this here.
//	public MapData getMapData() throws Exception{
//		myMapData.setNumXCells(numXCells);
//		myMapData.setNumYCells(numYCells);
//		for (String spawnName : spawnPoints.keySet()){
//			myMapData.addSpawnPoints(spawnName, spawnPoints.get(spawnName));
//		}
//		for (Point p : sinkPoints){
//			myMapData.addSinkPoint(p);
//		}
//		for (TerrainData td : terrainList){
//			myMapData.addTerrainData(td);
//		}
//		System.out.println("before terrain");
//		for (String vt: validTerrain.keySet()){
//			myMapData.addValidTerrain(vt, "color");
//		}
//		return myMapData;
//	}
	
	
	
	private void validatePoint(Point p, String type) throws Exception{
		if (p.getX() >= numXCells || p.getX() < 0){
			throw new Exception("X location of " + type + " point not valid.");
		}
		if (p.getY() >= numYCells || p.getY() < 0){
			throw new Exception("Y location of " + type + " point not valid.");
		}
	}
}
