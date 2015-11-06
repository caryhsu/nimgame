package caryhsu.nim.game;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;


public class WinningPositionFinder<G extends Game<P>, P extends Position> {

	@Getter private G game;
	@Getter private GameCache<G,P> gameCache;
	@Getter private PositionStateManager<G,P> stateManager; 
	PositionDumper<P> dumper;
	
	public WinningPositionFinder(G game, PositionDumper<P> dumper) {
		this.game = game;
		this.gameCache = new GameCache<G,P>(game);
		this.dumper = dumper;
	}
	
	public Set<P> find() {
		this.stateManager = new PositionStateManager<G,P>(this.game);
		//dumper.print("unknownStatePositions:", unknownStatePositions);
		Set<P> newWinningPositions;
		if (this.game.isWinWhenGameOver()) {
			newWinningPositions = this.game.getOverPositions();
//			dumper.print("newWinningPositions:", newWinningPositions);
		}
		else {
			Set<P> dangerousPositions = this.game.getOverPositions();
			this.stateManager.addDangerousPositions(dangerousPositions);
			newWinningPositions = getNextNewWiningPositions();
		}
		while(!newWinningPositions.isEmpty()) {
//			dumper.print("newWinningPositions:", newWinningPositions);
			for(P newWinningPosition : newWinningPositions) {
				stateManager.addWinningPosition(newWinningPosition);
				Set<P> dangerousPositions = this.gameCache.getMoveFroms(newWinningPosition);
				this.stateManager.addDangerousPositions(dangerousPositions);
			}
//			dumper.print("dangerousPositions:", this.dangerousPositions);
//			dumper.print("unknownStatePositions:", this.unknownStatePositions);
			newWinningPositions = getNextNewWiningPositions();
		}
//		dumper.print("winningPositions:", this.winningPositions);
		return this.stateManager.getWinningPositions();
	}
	
	private Set<P> getNextNewWiningPositions() {
		Set<P> positions = new HashSet<P>();
		for(P position : this.stateManager.getUnknownStatePositions()) {
			if (canMoveToUnknownStatePosition(position)) {
				positions.add(position);
			}
		}
		return positions;
	}

	private boolean canMoveToUnknownStatePosition(P position) {
		Set<P> nexts = this.gameCache.getMoveNexts(position);
		for(P next : nexts) {
			if (this.stateManager.getUnknownStatePositions().contains(next)) {
				return false;
			}
		}
		return true;
	}
	
}
