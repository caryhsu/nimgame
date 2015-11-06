package caryhsu.nim.game;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class PositionStateManager<G extends Game<P>, P extends Position> {

	@Getter private Set<P> winningPositions = new HashSet<P>();
	@Getter private Set<P> dangerousPositions = new HashSet<P>();
	@Getter private Set<P> unknownStatePositions = new HashSet<P>();

	public PositionStateManager(G game) {
		this.unknownStatePositions = new HashSet<P>(game.getAllPositions());
	}
	
	public void addDangerousPosition(P position) {
		this.dangerousPositions.add(position);
		this.unknownStatePositions.remove(position);
	}
	
	public void addDangerousPositions(Set<P> positions) {
		this.dangerousPositions.addAll(positions);
		this.unknownStatePositions.removeAll(positions);
	}

	public void addWinningPositions(Set<P> positions) {
		this.winningPositions.addAll(positions);
		this.unknownStatePositions.removeAll(positions);
	}

	public void addWinningPosition(P position) {
		this.winningPositions.add(position);
		this.unknownStatePositions.remove(position);
	}
		
}
