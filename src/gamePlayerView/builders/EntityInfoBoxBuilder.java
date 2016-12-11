package gamePlayerView.builders;

import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeOrSell;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import javafx.scene.layout.HBox;

public static class EntityInfoBoxBuilder {
	//private EntityInfoBox myEntityInfoBox;
	private final MachineInfo myMachineInfo;
	private final UpgradeOrSell myUpgradeOrSell;
	private final TargetingButtons myTargetingMechanism;
	private HBox myEntityInfoBox;
	private  GamePlayerScene  myGamePlayerScene;
	private final String temp;
	
	public EntityInfoBoxBuilder(GamePlayerScene aGamePlayerScene)
	{
		//myEntityInfoBox = new EntityInfoBox();
		myEntityInfoBox=new HBox();
		myGamePlayerScene = aGamePlayerScene;
	}
	
	public EntityInfoBoxBuilder withMachineInfo()
	{
		//null check before constructing
		//add upgrade button to  the info box
		MachineInfo myMachineInfo=new MachineInfo();
		myEntityInfoBox.getChildren().add(myMachineInfo.getView());
		return this;
	}
	
	public EntityInfoBoxBuilder withUpgradeButton()
	{
		//null check before constructing
		//add upgrade button to  the info box
		UpgradeOrSell myUpgradeOrSell=new UpgradeOrSell();
		myEntityInfoBox.getChildren().add(myUpgradeOrSell.getView());
		return this;
	}
	
	public EntityInfoBoxBuilder withTargetingMechanism()
	{
		//null check before constructing
		//add upgrade button to  the info box
		TargetingButtons myTargetingButtons=new TargetingButtons();
		myEntityInfoBox.getChildren().add(myTargetingButtons.getView());
		return this;
	}
	
	public void build()
	{
		//return new EntityInfoBox(this);
		//myGamePlayerScene.addEntityInfoBox(myEntityInfoBox);
	}
}
