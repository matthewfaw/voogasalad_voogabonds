package gamePlayerView.GUIPieces.MachineInfoView;

import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.GUIPieces.InfoBox;
import gamePlayerView.interfaces.IEnemiesKilledAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;

public class EnemiesKilledBox extends InfoBox implements IObserver<IViewablePlayer>, IEnemiesKilledAcceptor {

	@Override
	public void acceptEnemiesKilled(IObservable<IViewablePlayer> aPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		// TODO Auto-generated method stub
		
	}

}
