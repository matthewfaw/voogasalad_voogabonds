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
	
	public EntityInfoBox(EntityInfoBoxBuilder builder){
		this.myMachineInfo=builder.getMyMachineInfo();
		this.myUpgradeOrSell=builder.getMyUpgradeOrSell();
		this.myTargetingButtons=builder.getMyTargetingButtons();
		Hbox.setSpacing(10);
		Hbox.getChildren().addAll(myMachineInfo.getView(),myTargetingButtons.getView(),myUpgradeOrSell.getView());
	}
	
	@Override
	public Node getView() {
		return Hbox;
	}
}