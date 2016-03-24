package caryhsu.nim.game;
import static caryhsu.nim.game.PositionStateManager.PositionState.DANGEROUS;
import static caryhsu.nim.game.PositionStateManager.PositionState.WINNING;

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
			stateManager.setState(newWinningPositions, WINNING);
		}
		else {
			Set<P> dangerousPositions = this.game.getOverPositions();
			this.stateManager.setState(dangerousPositions, DANGEROUS);
			newWinningPositions = getNextNewWiningPositions();
			stateManager.setState(newWinningPositions, WINNING);
		}
		while(!newWinningPositions.isEmpty()) {
			dumper.print("newWinningPositions:", newWinningPositions);
			for(P newWinningPosition : newWinningPositions) {
				Set<P> dangerousPositions = this.gameCache.getMoveFroms(newWinningPosition);
				this.stateManager.setState(dangerousPositions, DANGEROUS);
			}
			dumper.print("dangerousPositions:", this.stateManager.getDangerousPositions());
			dumper.print("unknownStatePositions:", this.stateManager.getUnknownStatePositions());
			newWinningPositions = getNextNewWiningPositions();
			stateManager.setState(newWinningPositions, WINNING);
		}
		dumper.print("winningPositions:", this.stateManager.getWinningPositions());
		return this.stateManager.getWinningPositions();
	}
	
	private Set<P> getNextNewWiningPositions() {
		Set<P> positions = new HashSet<P>();
		for(P position : this.stateManager.getUnknownStatePositions()) {
			Set<P> nexts = this.gameCache.getMoveNexts(position);
			if ((!nexts.isEmpty()) && this.stateManager.getDangerousPositions().containsAll((nexts))) {
				positions.add(position);
			}
		}
		return positions;
	}
	
}
