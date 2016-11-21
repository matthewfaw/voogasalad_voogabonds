package authoring.model;

/**
 * Class representing the overall information for a player.
 * @author philipfoo
 */
public class PlayerData {
	private int startingLives;
	private int startingCash;
	private String winStrategyName;
	private String loseStrategyName;
	
	public int getStartingLives() {
		return startingLives;
	}
	public void setStartingLives(int startingLives) throws Exception{
		if (startingLives <= 0){
			throw new Exception("Starting lives must be a positive number.");
		}
		this.startingLives = startingLives;
	}
	
	public int getStartingCash() {
		return startingCash;
	}
	public void setStartingCash(int startingCash) throws Exception{
		if (startingCash <= 0){
			throw new Exception("Starting cash must be a positive number.");
		}
		this.startingCash = startingCash;
	}
	
	public String getWinStrategyName() {
		return winStrategyName;
	}
	public void setWinStrategyName(String winStrategyName) throws Exception{
		if (winStrategyName.equals("") || winStrategyName == null){
			throw new Exception("User must define a winning strategy.");
		}
		this.winStrategyName = winStrategyName;
	}
	
	public String getLoseStrategyName() {
		return loseStrategyName;
	}
	public void setLoseStrategyName(String loseStrategyName) throws Exception{
		if (loseStrategyName.equals("") || loseStrategyName == null){
			throw new Exception("User must define a losing strategy.");
		}
		this.loseStrategyName = loseStrategyName;
	}
	
	
}
