package gamePlayerView.builders;

import gamePlayerView.gamePlayerView.GamePlayerScene;
import javafx.scene.layout.HBox;

public class EntityInfoBoxBuilder {
	//private EntityInfoBox myEntityInfoBox;
	private HBox myEntityInfoBox;
	private  GamePlayerScene  myGamePlayerScene;
	
	public EntityInfoBoxBuilder(GamePlayerScene aGamePlayerScene)
	{
		//myEntityInfoBox = new EntityInfoBox();
		myEntityInfoBox=new HBox();
		myGamePlayerScene = aGamePlayerScene;
	}
	
	public EntityInfoBoxBuilder withUpgradeButton()
	{
		//null check before constructing
		//add upgrade button to  the info box
		return this;
	}
	
	public void build()
	{
		//myGamePlayerScene.addEntityInfoBox(myEntityInfoBox);
	}
}
