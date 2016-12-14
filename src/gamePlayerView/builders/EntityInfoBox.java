package gamePlayerView.builders;

import java.util.ArrayList;
import java.util.Collection;

import gamePlayerView.GUIPieces.InfoBoxes.BountyBox;
import gamePlayerView.GUIPieces.InfoBoxes.DamageBox;
import gamePlayerView.GUIPieces.InfoBoxes.HealthBox;
import gamePlayerView.GUIPieces.MachineInfoView.MachineInfo;
import gamePlayerView.GUIPieces.MachineInfoView.SellUI;
import gamePlayerView.GUIPieces.MachineInfoView.TargetingButtons;
import gamePlayerView.GUIPieces.MachineInfoView.UpgradeUI;
import gamePlayerView.gamePlayerView.GamePlayerScene;
import gamePlayerView.interfaces.IGUIPiece;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class EntityInfoBox implements IGUIPiece {
	
	private final MachineInfo myMachineInfo;
	private final UpgradeUI myUpgradeUI;
	private final TargetingButtons myTargetingButtons;
	private final SellUI mySellUI;
	private final DamageBox myDamageBox;
	private final HealthBox myHealthBox;
	private final BountyBox myBountyBox;
	private HBox Hbox=new HBox();
	
	public EntityInfoBox(EntityInfoBoxBuilder builder){
		this.myMachineInfo=builder.getMyMachineInfo();
		this.myUpgradeUI=builder.getMyUpgradeUI();
		this.myTargetingButtons=builder.getMyTargetingButtons();
		this.mySellUI=builder.getMySellUI();
		this.myDamageBox=builder.getMyDamageBox();
		this.myHealthBox=builder.getMyHealthBox();
		this.myBountyBox=builder.getMyBountyBox();
		Hbox.setSpacing(10);
		Hbox.getChildren().addAll(myMachineInfo.getView(),myTargetingButtons.getView(),myUpgradeUI.getView(),mySellUI.getView(),
				myDamageBox.getView(),myHealthBox.getView(),myBountyBox.getView());
		//Hbox.getChildren().
	}
	
	@Override
	public Node getView() {
		return Hbox;
	}
}