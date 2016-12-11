package gamePlayerView.GUIPieces.InfoBoxes;

import java.util.ResourceBundle;

/**
 * 
 * @author Guhan Muruganandam
 *
 */

public class DisplayBoxFactory {
	
	//private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	
	public DisplayBoxFactory(){
		
	}
	public InfoBox createBox(String type){
		InfoBox box = null;
		if(type.equals("cash")){
			box=new CashBox();
		}
		else if(type.equals("lives")){
			box=new LivesBox();
		}
		else if(type.equals("enemieskilled")){
			box=new EnemiesKilledBox();
		}
		return box;
	}
	
}
