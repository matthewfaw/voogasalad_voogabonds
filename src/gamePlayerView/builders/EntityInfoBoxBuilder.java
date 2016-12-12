package gamePlayerView.builders;
import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeOrSell;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import javafx.scene.layout.HBox;

public class EntityInfoBoxBuilder {
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
			myEntityInfoBox.getChildren().add(getMyMachineInfo().getView());
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

		public MachineInfo getMyMachineInfo() {
			return myMachineInfo;
		}
		public TargetingButtons getMyTargetingButtons() {
			return myTargetingButtons;
		}
		public UpgradeOrSell getMyUpgradeOrSell() {
			return myUpgradeOrSell;
		}
	}

