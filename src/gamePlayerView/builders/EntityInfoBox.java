package gamePlayerView.builders;

import java.util.ArrayList;
import java.util.Collection;

import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeOrSell;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import gamePlayerView.interfaces.IGUIPiece;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class EntityInfoBox implements IGUIPiece {
	
	private final MachineInfo myMachineInfo;
	private final UpgradeOrSell myUpgradeOrSell;
	private final TargetingButtons myTargetingButtons;
	private HBox Hbox=new HBox();
	
	private EntityInfoBox(EntityInfoBoxBuilder builder){
		this.myMachineInfo=builder.myMachineInfo;
		this.myUpgradeOrSell=builder.myUpgradeOrSell;
		this.myTargetingButtons=builder.myTargetingButtons;
		Hbox.getChildren().addAll(myMachineInfo.getView(),myUpgradeOrSell.getView(),myTargetingButtons.getView());
	}
	
	@Override
	public Node getView() {
		return Hbox;
	}
	
	public static class EntityInfoBoxBuilder {
		//private EntityInfoBox myEntityInfoBox;
		private MachineInfo myMachineInfo;
		private UpgradeOrSell myUpgradeOrSell;
		private TargetingButtons myTargetingButtons;
		private HBox myEntityInfoBox;
		private  GamePlayerScene  myGamePlayerScene;
		//private final String temp;
		
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
			this.myMachineInfo=new MachineInfo();
			myEntityInfoBox.getChildren().add(myMachineInfo.getView());
			return this;
		}
		
		public EntityInfoBoxBuilder withUpgradeButton()
		{
			//null check before constructing
			//add upgrade button to  the info box
			this.myUpgradeOrSell=new UpgradeOrSell();
			myEntityInfoBox.getChildren().add(myUpgradeOrSell.getView());
			return this;
		}
		
		public EntityInfoBoxBuilder withTargetingMechanism()
		{
			//null check before constructing
			//add upgrade button to  the info box
			this.myTargetingButtons=new TargetingButtons();
			myEntityInfoBox.getChildren().add(myTargetingButtons.getView());
			return this;
		}
		
		public EntityInfoBox build()
		{
			return new EntityInfoBox(this);
			//myGamePlayerScene.addEntityInfoBox(myEntityInfoBox);
		}
	}
}

