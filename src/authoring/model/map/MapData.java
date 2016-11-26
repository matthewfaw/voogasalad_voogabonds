package authoring.model.map;
import utility.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.ObservableMap;
import javafx.collections.SetChangeListener;
import javafx.collections.MapChangeListener;


public class MapData {
	private int numXCells;
	private int numYCells;
	private ObservableMap<String, ArrayList<Point>> spawnPoints = FXCollections.observableHashMap();
	private HashSet<Point> sinkPoints = new HashSet<Point>();
	private HashSet<TerrainData> terrainList;
	private ObservableSet<String> validTerrain = FXCollections.observableSet();
	
	/**
	 * MAP DIMENSION FUNCTIONS
	 */
	public void setNumXCells(int x) throws Exception{
		if (x <= 0){
			throw new Exception("The map must be wider than 0 cells.");
		}
		this.numXCells = x;
		System.out.println("Total XCells: " + this.numXCells);
	}
	public int getNumXCells(){
		return numXCells;
	}
	
	public void setNumYCells(int y) throws Exception{
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
	public void addSpawnPoints(String name, ArrayList<Point> newSpawnPoints) throws Exception{
		for (Point p: newSpawnPoints){
			validatePoint(p, "spawn");
		}
		spawnPoints.put(name, newSpawnPoints);
		System.out.println("Added Spawn Point " + name);
	}
	
	public void addSpawnPointListener(MapChangeListener<String, ArrayList<Point>> listener){
		spawnPoints.addListener(listener);
	}
	
	public void removeSpawnPoints(String name){
		if (spawnPoints.containsKey(name)){
			spawnPoints.remove(name);
			System.out.println("Removed Spawn Point " + name);
		}
	}
	
	public ArrayList<Point> getSpawnPoints(String name){
		return spawnPoints.get(name);
	}
	
	/**
	 * SINK POINT FUNCTIONS
	 */
	public void addSinkPoint(Point newSinkPoint) throws Exception{
		validatePoint(newSinkPoint, "sink");
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
	public void addValidTerrain(String s) throws Exception{
		if (s == null || s.length() == 0){
			throw new Exception("No terrain specified.");
		}
		validTerrain.add(s);
		System.out.println("Added Terrain: " + s);
	}
	
	public void removeValidTerrain(String s) throws Exception{
		if (validTerrain.contains(s)){
			validTerrain.remove(s);
		}
	}
	
	public ObservableSet<String> getValidTerrain(){
		return validTerrain;
	}
	
	public void addValidTerrainListener(SetChangeListener<String> listener){
		validTerrain.addListener(listener);
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
